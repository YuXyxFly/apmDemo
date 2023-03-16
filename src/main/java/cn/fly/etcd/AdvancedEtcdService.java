package cn.fly.etcd;

import io.etcd.jetcd.Watch;

/**
 * @author fly
 * @date 2023/3/16
 * @description
 */

public interface AdvancedEtcdService {

    boolean cas(String key, String expectValue, String updateValue) throws Exception;

    Watch.Watcher watch(String key, Watch.Listener listener) throws Exception;

    void putWithLease(String key, String value) throws Exception;




}
