package cn.fly.testController.test;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import static cn.fly.testController.test.ZookeeperTest.ZooKeeperConnectionBuilder.connectedSignal;

/**
 * @author fly
 * @date 2023/3/9
 * @description
 */

public class TestWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        Event.KeeperState state = watchedEvent.getState();
        // 连接成功
        if (state == Event.KeeperState.SyncConnected) {
            System.out.println("与ZooKeeper服务器连接成功");
        }
        // 断开连接
        else if (state == Event.KeeperState.Disconnected){
            System.out.println("与ZooKeeper服务器断开连接");
        }
    }
}
