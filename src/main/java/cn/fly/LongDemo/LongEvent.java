package cn.fly.LongDemo;

import lombok.Data;

/**
 * @author fly
 * @date 2022/12/29
 * @description
 */

@Data
public class LongEvent {

    private long value;

    public void set(long value)
    {
        this.value = value;
    }

}
