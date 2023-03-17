package cn.fly.testController;

import cn.fly.config.IdType;
import cn.fly.config.id.WebSocketId;
import cn.fly.etcd.EtcdUtils;
import cn.fly.etcd.impl.AdvancedEtcdServiceImpl;
import cn.fly.logDemo.infoResolver.dao.MysqlColumnsDao;
import cn.fly.logDemo.infoResolver.model.mysql.MysqlColumns;
import cn.fly.logDemo.infoResolver.model.mysql.MysqlTables;
import cn.fly.result.AjaxResult;
import cn.fly.testController.test.TestWatcher;
import cn.fly.testController.test.ZookeeperTest;
import cn.fly.testController.testReq.zkrc_zddwb;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.Watch;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;
import io.etcd.jetcd.watch.WatchEvent;
import kafka.server.KafkaConfig;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

@RestController
@RequestMapping("test")
public class TestController {
    
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    MysqlColumnsDao mysqlColumnsDao;

    @Resource
    CuratorFramework zkClient;

    @Resource
    Client etcdClient;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    MongoTemplate mongoTemplate;

    private Map<String, Watch.Watcher> watcherMap = new ConcurrentHashMap<>();

    @GetMapping("/columns/{tablename}")
    public AjaxResult<List<MysqlColumns>> columnsInfoGet(@PathVariable(value = "tablename") String tablename) {
        return AjaxResult.success(this.mysqlColumnsDao.showTableInfo(tablename, null));
    }

    @GetMapping("/table/{tablename}")
    public AjaxResult<List<MysqlTables>> tableInfoGet(@PathVariable(value = "tablename") String tablename) {
        return null;
    }

    @PutMapping("/table/{tableId}")
    public AjaxResult<MysqlColumns> testXgXq(@RequestBody MysqlColumns columns, @PathVariable("tableId") String tableId) {
        return AjaxResult.success(columns);
    }

    @PutMapping("/zddw/{zddwid}")
    public AjaxResult<zkrc_zddwb> testXgXq(@RequestBody zkrc_zddwb zddwb, @PathVariable("zddwid") Long zddwid) throws JsonProcessingException {
        return AjaxResult.success(zddwb);
    }

    @GetMapping("mysql/{key}/{value}")
    public void test(@PathVariable("key") String key, @PathVariable("value") String value) {
        redisTemplate.delete(key);
        mysqlColumnsDao.updateSfky(key, value);
        redisTemplate.delete(key);
    }

    @GetMapping("mysql/{key}")
    public AjaxResult<String> test(@PathVariable("key") String key) throws JsonProcessingException {
        if (redisTemplate.hasKey(key)) {
            return AjaxResult.success((String) redisTemplate.opsForValue().get(key) == null?"":(String) redisTemplate.opsForValue().get(key));
        } else {
            String value = mysqlColumnsDao.selectSfky(key);
            redisTemplate.opsForValue().set(key, value);
            return AjaxResult.success(value);
        }
    }

    @GetMapping("zk/connect")
    public AjaxResult<String> zkConnect() throws IOException, InterruptedException {
        ZookeeperTest build = ZookeeperTest.ZooKeeperConnectionBuilder.build(new TestWatcher());
        return AjaxResult.success(build.getZoo().getClientConfig().toString());
    }

    @GetMapping("zk/path")
    public AjaxResult<List<String>> zkpath() throws Exception {
        return AjaxResult.success(zkClient.getChildren().forPath("/ws-19834767323"));
    }

    @GetMapping("zk/path/{path}/{data}")
    public AjaxResult<List<String>> zkpath(@PathVariable("path") String path,@PathVariable("data") String data) throws Exception {
        zkClient.create().creatingParentsIfNeeded().forPath("/ws-19834767323/" + path, data.getBytes());
        return AjaxResult.success();
    }

    @GetMapping("etcd/{key}/{value}")
    public AjaxResult etcdAdd(@PathVariable("key") String key,@PathVariable("value") String value) {
        return AjaxResult.success(etcdClient.getKVClient().put(ByteSequence.from(key, StandardCharsets.UTF_8), ByteSequence.from(value,StandardCharsets.UTF_8)));
    }

    @DeleteMapping("etcd/{key}")
    public AjaxResult etcdDelete(@PathVariable("key") String key) {
        return AjaxResult.success(etcdClient.getKVClient().delete(ByteSequence.from(key, StandardCharsets.UTF_8)));
    }

    @GetMapping("etcd/watcher/{key}")
    public AjaxResult etcdWatcherAdd(@PathVariable("key") String key) throws Exception {
        // 先检查指定的key在etcd中是否存在

        // 查询条件中指定只返回key
        GetOption getOption = GetOption.newBuilder().withCountOnly(true).build();
        // 如果数量小于1,表示指定的key在etcd中不存在
        if (etcdClient.getKVClient().get(ByteSequence.from(key, StandardCharsets.UTF_8), getOption).get().getCount()<1) {
            String errorDesc = String.format("[%s] not exists", key);
            logger.error(errorDesc);
            return AjaxResult.failed(errorDesc + " " + new Date());
        }

        final String watchKey = key;

        // 实例化一个监听对象，当监听的key发生变化时会被调用
        Watch.Listener listener = Watch.listener(watchResponse -> {
            logger.info("收到[{}]的事件", watchKey);

            // 被调用时传入的是事件集合，这里遍历每个事件
            watchResponse.getEvents().forEach(watchEvent -> {
                // 操作类型
                WatchEvent.EventType eventType = watchEvent.getEventType();

                // 操作的键值对
                KeyValue keyValue = watchEvent.getKeyValue();

                logger.info("type={}, key={}, value={}",
                        eventType,
                        keyValue.getKey().toString(StandardCharsets.UTF_8),
                        keyValue.getValue().toString(StandardCharsets.UTF_8));

                // 如果是删除操作，就把该key的Watcher找出来close掉
                if (WatchEvent.EventType.DELETE.equals(eventType)
                        && watcherMap.containsKey(watchKey)) {
                    Watch.Watcher watcher = watcherMap.remove(watchKey);
                    watcher.close();
                }
            });
        });

        // 添加监听
        Watch.Watcher watcher = new AdvancedEtcdServiceImpl().watch(watchKey, listener);

        // 将这个Watcher放入内存中保存，如果该key被删除就要将这个Watcher关闭
        watcherMap.put(key, watcher);

        return AjaxResult.success("watch success " + new Date());
    }

    @GetMapping("etcd/node")
    public AjaxResult<GetResponse> etcdNodes() throws Exception {
        // 带前缀的方式查询，注意要入参key和prefix是同一个值
        GetOption getOption = GetOption.newBuilder().withPrefix(EtcdUtils.bytesOf("/test")).build();
        CompletableFuture<GetResponse> getResponseCompletableFuture = etcdClient.getKVClient().get(EtcdUtils.bytesOf("/test"), getOption);
        return AjaxResult.success(getResponseCompletableFuture.get());
    }

    @GetMapping("ip/{ip}")
    public AjaxResult<InetAddress> ip() throws Exception {
        InetAddress localHost = InetAddress.getLocalHost();
        return AjaxResult.success(localHost);
    }

    @GetMapping("mongo/add")
    public AjaxResult<WebSocketId> mongoAdd() throws JsonProcessingException {
        WebSocketId testObject = (WebSocketId)IdType.WEBSOCKET.getter("testForever");
        mongoTemplate.save(testObject, "userDetails");
        return AjaxResult.success(mongoTemplate.insert(testObject, "userDetails"));
    }

    @GetMapping("mongo")
    public AjaxResult<WebSocketId> mongoGet() {
        AjaxResult<WebSocketId> success = AjaxResult.success(mongoTemplate.find(Query.query(Criteria.where("username").is("testForever"))
                .with(PageRequest.of(1, 1, Sort.by(Sort.Order.desc("date")))), WebSocketId.class, "userDetails").get(0));
        return success;
    }

    @GetMapping("mongo/all")
    public AjaxResult<List<WebSocketId>> mongoAll() throws JsonProcessingException {
        return AjaxResult.success(mongoTemplate.findAll(WebSocketId.class, "userDetails"));
    }

    @GetMapping("kafka/topic/{key}")
    public AjaxResult<String> kafkaTopicAddKey(@PathVariable("key") String key) {
        //return AjaxResult.success(KafkaConfig)
        return null;
    }




}
