package cn.fly.logDemo.infoResolver.model.logTable;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fly
 * @date 2023/2/27
 * @description
 */

@Data
@Accessors(chain = true)
public class TranslatorCollect {

    private Object req;

    private Long infoId;

    public TranslatorCollect(Object req, Long infoId) {
        this.req = req;
        this.infoId = infoId;
    }
}
