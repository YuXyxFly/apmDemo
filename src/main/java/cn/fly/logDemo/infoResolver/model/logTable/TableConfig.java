package cn.fly.logDemo.infoResolver.model.logTable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fly
 * @date 2023/2/23
 * @description
 */

@Data
@Accessors(chain = true)
public class TableConfig {

    private Long id;

    private String databaseName;

    private String tableName;

}
