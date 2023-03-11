package cn.fly.config;

import cn.fly.logDemo.config.SpringUtils;
import io.etcd.jetcd.Client;
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
 * @date 2023/3/11
 * @description
 */

@Configuration
@ConfigurationProperties("log-demo.etcd")
public class EtcdConfig {


    Logger logger = LoggerFactory.getLogger(EtcdConfig.class);

    private final String urlPrefix = "http://";

    private String url;

    @Bean
    public Client initEtcdDatasource() {
        resolveEtcdUrl();
        Client client = Client.builder().endpoints(url).build();
        logger.info("Etcd Connect init Success! client: " + url);
        return client;
    }

    private void resolveEtcdUrl() {
        if (!url.startsWith(urlPrefix)){
            url = urlPrefix + url;
        }
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
