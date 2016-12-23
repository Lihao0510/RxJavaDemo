package com.lihao.rxjavademo.Net;

import android.util.Log;

import com.lihao.rxjavademo.Bean.CurUser;
import com.lihao.rxjavademo.Constant.Constants;

import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;

/**
 * Created by lihao on 2016/12/16.
 */

public class NetUtil {

    public static Element[] setSoapHeader(){

        Element[] header = new Element[1];
        header[0] = new Element().createElement(Constants.NAMESPACE, "pageHeader");
        Element curUser = new Element().createElement(Constants.NAMESPACE, "curUser");

        Element userID = new Element().createElement(Constants.NAMESPACE, "userID");
        userID.addChild(Node.TEXT, CurUser.userID);
        curUser.addChild(Node.ELEMENT, userID);

        Element workNumber = new Element().createElement(Constants.NAMESPACE, "workNumber");
        workNumber.addChild(Node.TEXT, CurUser.workNumber);
        curUser.addChild(Node.ELEMENT, workNumber);

        Element userType = new Element().createElement(Constants.NAMESPACE, "userType");
        userType.addChild(Node.TEXT, CurUser.userType);
        curUser.addChild(Node.ELEMENT, userType);

        Element userName = new Element().createElement(Constants.NAMESPACE, "userName");
        userName.addChild(Node.TEXT, CurUser.userName);
        curUser.addChild(Node.ELEMENT, userName);

        Element sysUserDesc = new Element().createElement(Constants.NAMESPACE, "sysUserDesc");
        sysUserDesc.addChild(Node.TEXT, CurUser.sysUserDesc);
        curUser.addChild(Node.ELEMENT, sysUserDesc);

        Element passWord = new Element().createElement(Constants.NAMESPACE, "passWord");
        passWord.addChild(Node.TEXT, CurUser.passWord);
        curUser.addChild(Node.ELEMENT, passWord);

        Element userCName = new Element().createElement(Constants.NAMESPACE, "userCName");
        userCName.addChild(Node.TEXT, CurUser.userCName);
        curUser.addChild(Node.ELEMENT, userCName);

        Element loginMode = new Element().createElement(Constants.NAMESPACE, "loginMode");
        loginMode.addChild(Node.TEXT, CurUser.loginMode);
        curUser.addChild(Node.ELEMENT, loginMode);

        Element orgID = new Element().createElement(Constants.NAMESPACE, "orgID");
        orgID.addChild(Node.TEXT, CurUser.orgID);
        curUser.addChild(Node.ELEMENT, orgID);

        Element orgName = new Element().createElement(Constants.NAMESPACE, "orgName");
        orgName.addChild(Node.TEXT, CurUser.orgName);
        curUser.addChild(Node.ELEMENT, orgName);

        Element userIP = new Element().createElement(Constants.NAMESPACE, "userIP");
        userIP.addChild(Node.TEXT, CurUser.userIP);
        curUser.addChild(Node.ELEMENT, userIP);

        Element dynaPSW = new Element().createElement(Constants.NAMESPACE, "dynaPSW");
        dynaPSW.addChild(Node.TEXT, CurUser.dynaPSW);
        curUser.addChild(Node.ELEMENT, dynaPSW);

        Element loginMessage = new Element().createElement(Constants.NAMESPACE, "loginMessage");
        loginMessage.addChild(Node.TEXT, CurUser.loginMessage);
        curUser.addChild(Node.ELEMENT, loginMessage);

        Log.d("Header数：", curUser.getChildCount() + "");
        header[0].addChild(Node.ELEMENT, curUser);
        Log.d("Header数：", header[0].getChildCount() + "");
        Log.d("Lihao", CurUser.toStaticString());

        return header;
    }

}
