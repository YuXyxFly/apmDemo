package cn.fly.logDemo.infoResolver.model.mysql;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

@Data
@Accessors(chain = true)
@TableName("MysqlColumnsForTableTest")
public class MysqlColumns {

    private String Field;

    private String Type;

    private String Collation;

    private String Null;

    private String Key;

    private String Default;

    private String Extra;

    private String privileges;

    private String Comment;

}
