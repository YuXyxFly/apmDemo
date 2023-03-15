package cn.fly.IM.login.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author fly
 * @date 2023/3/15
 * @description
 */

@Table(name = "im_user")
public class UserInfo {

    @Id
    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "nickName")
    private String nickName;

    @Column(name = "headImage")
    private String headImage;


}
