package com.hbf.shiro;

import com.hbf.utils.T;
import org.apache.shiro.authc.UsernamePasswordToken;


public class ShiroUsernamePasswordToken extends UsernamePasswordToken {

    private static final long serialVersionUID = -3506369427856389419L;
    private String checkword;
    public String getCheckword() {
        return checkword;
    }


    public enum UserType{
        admin,
        user,
        bank,
        mobile_admin,
        open
    }
    private UserType usertype;
    public UserType getUsertype() {
        return usertype;
    }


    public ShiroUsernamePasswordToken(String username, String password,
                                      boolean rememberMe, String host, UserType type, String checkword) {
        super(username, password, rememberMe, host);
        if(type==UserType.admin){

        }
        this.usertype=type;
        this.checkword=checkword;
    }



    public void CheckCaptcha() {
//        if(!T.isDebug())
//            ShiroUtils.CheckCaptcha(this.checkword);
    }

    /**
     * 密码预处理,默认不需要处理
     * @param spassword
     * @return
     */

    public String prePassword(String spassword) {
        return spassword;
    }
}
