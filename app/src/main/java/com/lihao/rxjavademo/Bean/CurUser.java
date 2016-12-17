package com.lihao.rxjavademo.Bean;

/**
 * Created by lihao on 2016/12/17.
 */

public class CurUser {

    public static String userID = "";
    public static String workNumber = "";
    public static String userType = "";
    public static String userName = "";
    public static String sysUserDesc = "";
    public static String passWord = "";
    public static String userCName = "";
    public static String orgID = "";
    public static String orgName = "";
    public static String loginMode = "";
    public static String userIP = "";
    public static String dynaPSW = "";
    public static String loginMessage = "";

    public static String toStaticString() {
        return "userID:" + userID + ", userName:" + userCName + ", userNum:" + userName +", orgName:" + orgName +", userIP:" + userIP;
    }
}
