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
public class TableFieldValue {

    private String fieldConfigId;

    private String fieldKey;

    private String fieldValue;

}
