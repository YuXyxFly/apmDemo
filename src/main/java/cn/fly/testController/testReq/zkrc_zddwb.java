package cn.fly.testController.testReq;

import cn.fly.logDemo.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author fly
 * @date 2023/2/27
 * @description
 */

@Data
@Accessors(chain = true)
public class zkrc_zddwb {


    /** 重点单位id */
    private Long zddwid;

    /** 单位id */
    private Long dwid;

    /** 单位名称 */
    private String dwmc;

    /** 位置 */
    private String wz;

    /** web是否启用 */
    private String websfqy;

    /** 开始时间 */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_T_HH_MM_SS_Z)
    private Date kssj;

    /** 结束时间 */
    @JsonFormat(pattern = DateUtils.YYYY_MM_DD_T_HH_MM_SS_Z)
    private Date jssj;

    /** 广告宣传图 */
    private String ggxct;

    /** 操作时间 */
    private Date czsj;

    /** 操作员 */
    private Long czy;



}
