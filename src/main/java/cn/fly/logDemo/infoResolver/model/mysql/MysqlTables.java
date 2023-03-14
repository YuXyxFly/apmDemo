package cn.fly.logDemo.infoResolver.model.mysql;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Table;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

@Data
@Accessors(chain = true)
@Table(name = "information_schema.TABLES")
public class MysqlTables {

    private String Table_CataLog;

    private String TABLE_SCHEMA;

    private String TABLE_NAME;

    private String TABLE_TYPE;

    private String ENGINE;

    private String VERSION;

    private String ROW_FORMAT;

    private String CREATE_TIME;

    private String TABLE_COMMENT;



}
