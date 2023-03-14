package cn.fly.config;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author fly
 * @date 2023/3/13
 * @description
 */

@Getter
@Accessors(chain = true)
public class WebSocketId {

    @Value("spring.application.name")
    public String applicationName;

    private String host;

    @Value("log-demo.etcd.url")
    private String etcdAddr;

    private Date timeStamp;

    protected String username;

    private WebSocketId(String userName) {
        try {
            this.host = InetAddress.getLocalHost().getHostAddress();
            this.timeStamp = new Date();
            this.username = userName;
        } catch (UnknownHostException ignored) {
        }
    }

    public WebSocketId(String applicationName, String host, String etcdAddr, Date timeStamp, String username) {
        this.applicationName = applicationName;
        this.host = host;
        this.etcdAddr = etcdAddr;
        this.timeStamp = timeStamp;
        this.username = username;
    }

    public String getEtcdId() {
        return "/" + this.applicationName + "/" + this.host + "/" +this.etcdAddr + "/" +this.timeStamp + "/" +this.username;
    }


    public WebSocketId resovle(String value) {
        String[] resolve = value.split("/");
        if (resolve.length != 5){
            return null;
        }else return new WebSocketId(resolve[0],resolve[1],resolve[2],new Date(resolve[3]),resolve[4]);
    }

    public static class WebSocketIdBuilder{
        public static WebSocketId builder(String userName) {
            return new WebSocketId(userName);
        }
    }

}
