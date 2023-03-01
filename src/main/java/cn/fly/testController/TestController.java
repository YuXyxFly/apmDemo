package cn.fly.testController;

import cn.fly.logDemo.infoResolver.dao.MysqlColumnsDao;
import cn.fly.logDemo.infoResolver.dao.MysqlTableDao;
import cn.fly.logDemo.infoResolver.model.mysql.MysqlColumns;
import cn.fly.logDemo.infoResolver.model.mysql.MysqlTables;
import cn.fly.testController.testReq.zkrc_zddwb;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fly
 * @date 2023/2/21
 * @description
 */

@RestController
@RequestMapping("test")
public class TestController {

    @Resource
    MysqlColumnsDao mysqlColumnsDao;

    @Resource
    MysqlTableDao mysqlTableDao;

    @GetMapping("/columns/{tablename}")
    public List<MysqlColumns> columnsInfoGet(@PathVariable(value = "tablename") String tablename) {
        return this.mysqlColumnsDao.showTableInfo(tablename, null);
    }

    @GetMapping("/table/{tablename}")
    public List<MysqlTables> tableInfoGet(@PathVariable(value = "tablename") String tablename) {
        LambdaQueryChainWrapper<MysqlTables> mysqlTablesLambdaQueryChainWrapper = ChainWrappers.lambdaQueryChain(mysqlTableDao);
        mysqlTablesLambdaQueryChainWrapper.eq(MysqlTables::getTABLE_NAME, "1");
        return this.mysqlTableDao.selectList(new QueryWrapper<>(new MysqlTables().setTABLE_NAME(tablename)));
    }

    @PutMapping("/table/{tableId}")
    public MysqlColumns testXgXq(@RequestBody MysqlColumns columns, @PathVariable("tableId") String tableId) {
        return columns;
    }

    @PutMapping("/zddw/{zddwid}")
    public zkrc_zddwb testXgXq(@RequestBody zkrc_zddwb zddwb, @PathVariable("zddwid") Long zddwid) {
        return zddwb;
    }


}
