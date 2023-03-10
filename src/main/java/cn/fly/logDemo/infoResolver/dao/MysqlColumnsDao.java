package cn.fly.logDemo.infoResolver.dao;

import cn.fly.logDemo.infoResolver.model.mysql.MysqlColumns;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */


@Mapper
public interface MysqlColumnsDao{

    List<MysqlColumns> showTableInfo(String tableName, List<String> Fields);

    void updateSfky(String key, String sfky);

    String selectSfky(String key);

}
