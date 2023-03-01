package cn.fly.logDemo.infoResolver.model.logTable;

import cn.fly.logDemo.infoResolver.model.mysql.MysqlColumns;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author fly
 * @date 2023/2/23
 * @description
 */

@Data
@Accessors(chain = true)
public class TableFieldConfig {

    private Long id;

    private Long tableConfigId;

    private String field;

    private String fieldName;

    private String enumFlag;

    private String relevanceFlag;

    private String sort;

    public TableFieldConfig generateInfo(TableConfig tableConfig, MysqlColumns columns) {
        this.tableConfigId = tableConfig.getId();
        this.field = columns.getField();
        this.fieldName = columns.getComment();
        return this;
    }
}
