package cn.fly.testController.test;

import cn.fly.config.ZkConfig;
import cn.fly.logDemo.config.SpringUtils;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author fly
 * @date 2023/3/9
 * @description
 */

public class ZookeeperTest {

    private ZooKeeper zoo;

    public ZooKeeper getZoo() {
        return zoo;
    }

    public ZookeeperTest setZoo(ZooKeeper zoo) {
        this.zoo = zoo;
        return this;
    }

    public static class ZooKeeperConnectionBuilder {

        public static final CountDownLatch connectedSignal = new CountDownLatch(1);

        public static ZookeeperTest build(Watcher watcher) throws InterruptedException, IOException {
            ZkConfig config = SpringUtils.getBean(ZkConfig.class);
            ZookeeperTest zooKeeper = new ZookeeperTest().setZoo(new ZooKeeper(config.getUrl(), config.getTimeout(), watcher));
            return zooKeeper;
        }

    }
}
