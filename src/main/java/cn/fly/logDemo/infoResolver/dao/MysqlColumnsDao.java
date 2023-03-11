package cn.fly.logDemo.infoResolver.dao;

import cn.fly.logDemo.infoResolver.model.mysql.MysqlColumns;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

@Mapper
public interface MysqlColumnsDao{

    List<MysqlColumns> showTableInfo(@Param("tableName") String tableName, @Param("Fields") List<String> Fields);

    void updateSfky(@Param("key") String key, @Param("sfky") String sfky);

    String selectSfky(@Param("key") String key);

}
