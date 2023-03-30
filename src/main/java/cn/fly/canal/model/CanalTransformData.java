package cn.fly.canal.model;

import com.alibaba.otter.canal.protocol.CanalEntry;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author fly
 * @date 2023/3/29
 * @description
 */

@Data
@Accessors(chain = true)
public class CanalTransformData {

    private CanalEntry.RowChange rowInfo;

    private String tableName;

    private Date timeStamp;

    public CanalTransformData(CanalEntry.RowChange rowInfo, String tableName) {
        this.rowInfo = rowInfo;
        this.tableName = tableName;
        this.timeStamp = new Date();
    }
}
