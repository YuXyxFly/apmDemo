package cn.fly.logDemo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author fly
 * @date 2023/3/13
 * @description
 */

@Data
@Entity
@Table(name = "zkrc_d_dwlx")
public class Dmb {
    @Id
    @Column(name = "dmid")
    private String dmid;

    @Column(name = "dmmc")
    private String dmmc;

    @Column(name = "sfyx")
    private String sfky;

    @Column(name = "xh")
    private String xh;



}
