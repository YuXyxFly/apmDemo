package cn.fly.zk.watcher;

import cn.fly.config.ZkConfig;
import cn.fly.logDemo.config.SpringUtils;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

import static cn.fly.testController.test.ZookeeperTest.ZooKeeperConnectionBuilder.connectedSignal;

/**
 * @author fly
 * @date 2023/3/9
 * @description
 */

public class ConnectWatcher implements Watcher {

    Logger logger = LoggerFactory.getLogger(ConnectWatcher.class);


    @Override
    public void process(WatchedEvent watchedEvent) {
        Event.KeeperState state = watchedEvent.getState();
        // 连接成功
        if (state == Event.KeeperState.SyncConnected) {
            logger.info("Zookeeper Connect init Success! client: " + SpringUtils.getBean(ZkConfig.class).getUrl());
        }
        // 断开连接
        else if (state == Event.KeeperState.Disconnected){
            logger.info("Zookeeper Connect init failed! client: " + SpringUtils.getBean(ZkConfig.class).getUrl());
        }
    }
}
