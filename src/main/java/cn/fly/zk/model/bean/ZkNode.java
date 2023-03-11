package cn.fly.zk.model.bean;

import cn.fly.zk.model.constants.ZkPath;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.logging.log4j.util.Strings;
import org.apache.zookeeper.CreateMode;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author fly
 * @date 2023/3/10
 * @description
 */

@Data
@Accessors(chain = true)
public class ZkNode {

    public String path;

    private String value;

    private CreateMode type = CreateMode.EPHEMERAL;

    /**
     * zkPath should starts with "/"
     * shouldn't ends with "/"
     */
    public ZkNode resolvePath(){
        if (path.startsWith(ZkPath.prefix)) {
            path = ZkPath.prefix + path ;
        }
        if (path.endsWith(ZkPath.prefix)) {
            path = path.substring(0, path.length() - 1);
        }
        return this;
    }

    public byte[] getValueBytes() {
        return this.value.getBytes();
    }

    public static class ZkNodeBuilder {


        public static ZkNode builder( String path,  String value) {
            return new ZkNode().setPath(path).setValue(value).resolvePath();
        }


        public static ZkNode builder( String path,  String value,  CreateMode type) {
            return new ZkNode().setPath(path).setValue(value).setType(type).resolvePath();
        }

        public static ZkNode builder( String path) {
            return new ZkNode().setPath(path).resolvePath();
        }
    }


}
