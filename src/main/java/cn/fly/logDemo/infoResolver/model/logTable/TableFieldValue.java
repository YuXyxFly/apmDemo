package cn.fly.logDemo.infoResolver.model.logTable;

import cn.fly.logDemo.infoResolver.model.mysql.MysqlColumns;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fly
 * @date 2023/2/23
 * @description
 */

@Data
@Accessors(chain = true)
public class TableFieldValue {

    private Long fieldConfigId;

    private String fieldKey;

    private String fieldValue;

    public TableFieldValue generateInfo(TableFieldConfig tableFieldConfig, MysqlColumns columns, Object o) {
        this.fieldConfigId = tableFieldConfig.getId();
        this.fieldKey = columns.getField();
        try {
            this.fieldValue = new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
        }
        return this;
    }
}
