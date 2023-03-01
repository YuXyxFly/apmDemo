package cn.fly.logDemo.infoResolver.model.logTable;

import cn.fly.logDemo.LogEvent;
import cn.fly.logDemo.infoResolver.model.mysql.MysqlColumns;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fly
 * @date 2023/2/27
 * @description
 */

@Data
@Accessors(chain = true)
public class LogCollectedInfo {

    TableConfig tableConfig;

    List<TableFieldConfig> tableFieldConfigs;

    List<TableFieldValue> tableFieldValues;

    public LogCollectedInfo generateInfo(Map<String, List<MysqlColumns>> fieldNotNUllTableInfoCollected, LogEvent event, Map<String, Object> allFieldNotNull) {
        this.tableConfig = new TableConfig().generateInfo(event);
        tableFieldConfigs = new ArrayList<>();
        tableFieldValues = new ArrayList<>();
        fieldNotNUllTableInfoCollected.forEach( (item, res) ->{
            TableFieldConfig tableFieldConfig = new TableFieldConfig().generateInfo(tableConfig, res.get(0));
            this.tableFieldConfigs.add(tableFieldConfig);
            this.tableFieldValues.add(new TableFieldValue().generateInfo(tableFieldConfig, res.get(0), allFieldNotNull.get(res.get(0).getField())));
        });
        return this;
    }
}
