package cn.fly.logDemo.infoResolver.model.logTable;

import cn.fly.logDemo.LogEvent;
import cn.hutool.core.util.IdUtil;
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

    private Long infoId;

    private String databaseName = "default";

    private String tableName;

    public TableConfig generateInfo(LogEvent event) {
        this.id = IdUtil.getSnowflakeNextId();
        this.infoId = event.getInfoId();
        this.tableName = event.getTableName();
        return this;
    }
}
