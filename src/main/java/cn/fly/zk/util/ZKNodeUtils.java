package cn.fly.zk.util;

import cn.fly.logDemo.config.SpringUtils;
import cn.fly.zk.model.bean.ZkNode;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

import java.util.List;

/**
 * @author fly
 * @date 2023/3/10
 * @description resolve the ZKNode
 */

public class ZKNodeUtils {

    /**
     * created ZKNode
     * @param path  ZKNode Path
     * @param value ZkNode Value
     */
    public void createNode(String path, String value) throws Exception {
        ZkNode zNode = ZkNode.ZkNodeBuilder.builder(path, value);
        SpringUtils.getBean(CuratorFramework.class).create()
                .creatingParentsIfNeeded()
                .withMode(zNode.getType())
                .forPath(zNode.getPath(), zNode.getValueBytes());
        return;
    }

    /**
     * created ZKNode
     * @param path  ZKNode Path
     * @param value ZkNode Value
     * @param type ZNode type
     */
    public void createNodeWithType(String path, String value, CreateMode type) throws Exception {
        ZkNode zNode = ZkNode.ZkNodeBuilder.builder(path, value, type);
        SpringUtils.getBean(CuratorFramework.class).create()
                .creatingParentsIfNeeded()
                .withMode(zNode.getType())
                .forPath(zNode.getPath(), zNode.getValueBytes());
        return;
    }

    public List<String> getNodeWithPath(String path) throws Exception {
        List<String> nodes = SpringUtils.getBean(CuratorFramework.class).getChildren().forPath(ZkNode.ZkNodeBuilder.builder(path).resolvePath().getPath());
        return nodes;
    }



}
