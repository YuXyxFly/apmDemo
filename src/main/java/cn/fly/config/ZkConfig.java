package cn.fly.config;

import cn.fly.logDemo.config.SpringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fly
 * @date 2023/3/9
 * @description
 */


@Configuration
@ConfigurationProperties("log-demo.zookeeper")
public class ZkConfig {

    Logger logger = LoggerFactory.getLogger(ZkConfig.class);

    private String url;

    private int timeout;

    public int retry;

    private boolean test;


    @Bean
    public CuratorFramework initZkDataSource() {
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                SpringUtils.getBean(ZkConfig.class).getUrl(),
                new RetryNTimes(retry, timeout)
        );
        client.start();
        logger.info("Zookeeper Connect init Success! client: " + url);
        return client;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public int getRetry() {
        return retry;
    }

    public void setRetry(int retry) {
        this.retry = retry;
    }
}
