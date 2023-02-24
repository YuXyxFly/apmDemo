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
public class TableFieldConfig {

    private Long tableConfigId;

    private String field;

    private String fieldName;

    private String enumFlag;

    private String relevenceFlag;

    private String sort;

}
