package cn.fly.canal.model;

import com.alibaba.otter.canal.protocol.CanalEntry;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author fly
 * @date 2023/3/29
 * @description
 */

@Data
@Accessors(chain = true)
public class CanalEventData {

    private String tableSchema;

    private List<CanalFieldChange> fieldChanges;

    private String traceId;

    private CanalEntry.RowChange rowInfo;

    private Date timeStamp;

    public void generateFieldInfo(CanalTransformData rowChange) {
        System.out.println("处理中!");
        this.rowInfo = rowChange.getRowInfo();
        this.tableSchema = rowChange.getTableName();
        this.timeStamp = rowChange.getTimeStamp();
    }






}
