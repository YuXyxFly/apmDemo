package cn.fly.testController;

import cn.fly.result.AjaxResult;
import cn.fly.testController.test.dao.YhbBaseMapper;
import cn.fly.testController.test.pojo.Zkrc_yhb;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fly
 * @date 2023/4/20
 * @description
 */

@RestController
@RequestMapping("test/mysql")
public class MysqlTestController {

    @Resource
    YhbBaseMapper yhbBaseMapper;

    @GetMapping("/yhb/{id}")
    public AjaxResult<String> yhbSelect(@PathVariable("id") String id) throws JsonProcessingException {
        return AjaxResult.success(new ObjectMapper().writeValueAsString(this.yhbBaseMapper.selectList(id, new LambdaQueryWrapper<Zkrc_yhb>()
                .eq(Zkrc_yhb::getYhid, id))));
    }



}
