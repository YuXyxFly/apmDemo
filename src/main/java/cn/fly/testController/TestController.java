package cn.fly.testController;

import cn.fly.config.IdType;
import cn.fly.config.WebSocketId;
import cn.fly.etcd.EtcdUtils;
import cn.fly.logDemo.infoResolver.dao.MysqlColumnsDao;
import cn.fly.logDemo.infoResolver.model.mysql.MysqlColumns;
import cn.fly.logDemo.infoResolver.model.mysql.MysqlTables;
import cn.fly.result.AjaxResult;
import cn.fly.testController.test.TestWatcher;
import cn.fly.testController.test.ZookeeperTest;
import cn.fly.testController.testReq.zkrc_zddwb;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;
import org.apache.curator.framework.CuratorFramework;
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
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

@RestController
@RequestMapping("test")
public class TestController {

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



}
