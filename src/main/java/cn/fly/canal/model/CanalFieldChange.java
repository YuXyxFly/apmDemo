package cn.fly.canal.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fly
 * @date 2023/3/29
 * @description
 */

@Data
@Accessors(chain = true)
public class CanalFieldChange {

    private String field;

    private String comment;

    private String beforeValue;

    private String afterValue;


}
