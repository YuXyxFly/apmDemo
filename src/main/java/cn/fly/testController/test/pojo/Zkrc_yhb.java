package cn.fly.testController.test.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author fly
 * @date 2023/4/20
 * @description
 */

@Data
@Table(schema = "zkrc_yhb")
public class Zkrc_yhb {


    @Id
    @TableId
    @Column(name = "yhid")
    private Long yhid;


    @Column(name = "nick_name")
    private String nickName;


    @Column(name = "avatar")
    private String avatar;


    @Column(name = "sjh")
    private String sjh;


    @Column(name = "yx")
    private String yx;


    @Column(name = "mm")
    private String mm;


    @Column(name = "yhlx")
    private int yhlx;


    @Column(name = "wxid")
    private String wxid;


    @Column(name = "sfyx")
    private int sfyx;


    @Column(name = "zt")
    private String zt;


    @Column(name = "bynf")
    private Date bynf;


    @Column(name = "zw")
    private String zw;


    @Column(name = "cjsj")
    private Date cjsj;


    @Column(name = "xgsj")
    private Date xgsj;


}
