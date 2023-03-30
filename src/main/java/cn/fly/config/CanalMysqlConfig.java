package cn.fly.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author fly
 * @date 2023/3/27
 * @description
 */

@Component
@ConfigurationProperties(prefix = "log-demo.canal-monitor-mysql")
public class CanalMysqlConfig {

    Logger logger = LoggerFactory.getLogger(CanalMysqlConfig.class);

    private String host;

    private int port;

    private String tableName;



    public CanalMysqlConfig() {
    }



    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
