package com.lihao.rxjavademo.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lihao.rxjavademo.Bean.CurUser;
import com.lihao.rxjavademo.Constant.Constants;
import com.lihao.rxjavademo.MyService;
import com.lihao.rxjavademo.Net.Request;
import com.lihao.rxjavademo.Net.SoapRequest;
import com.lihao.rxjavademo.R;
import com.oridway.oamessager.AESEncryption;

import org.ksoap2.serialization.SoapObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    protected EditText userName;

    @BindView(R.id.et_password)
    protected EditText password;

    @BindView(R.id.bt_login)
    protected Button login;

    @BindView(R.id.tv_result)
    protected TextView result;

    @BindView(R.id.bt_getlist)
    protected Button getList;

    @BindView(R.id.bt_startservice)
    protected Button startSErvice;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
    }

    @OnClick(R.id.bt_startservice)
    public void initService() {
        Intent intent = new Intent(mContext, MyService.class);
        startService(intent);
    }

    @OnClick(R.id.bt_login)
    protected void initView() {
        final Map<String, Object> params = new HashMap<>();
        String id = userName.getText().toString().trim();
        String pwd = AESEncryption.toHexString((AESEncryption.AESEncrypt(password.getText().toString().trim(), Constants.AES_KEY)));
        params.put("userName", id);
        params.put("passWord", pwd);
        params.put("myIP", "192.168.1.107");
        params.put("loginMode", "3");
        Log.d("Lihao", "ID:" + id + "; PSW:" + pwd);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
        Observable.create(
                new Observable.OnSubscribe<SoapObject>() {
                    @Override
                    public void call(Subscriber<? super SoapObject> subscriber) {
                        subscriber.onNext(Request.soapRequest(Constants.NAMESPACE, "login", Constants.LOGIN_URL, params));
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SoapObject>() {
                    @Override
                    public void call(SoapObject soapObject) {
                        SoapObject sub_a = (SoapObject) soapObject.getProperty(0);
                        CurUser.userCName = sub_a.getProperty("userCName").toString();
                        CurUser.userID = sub_a.getProperty("userID").toString();
                        CurUser.userIP = sub_a.getProperty("userIP").toString();
                        CurUser.userType = sub_a.getProperty("userType").toString();
                        CurUser.userName = sub_a.getProperty("userName").toString();
                        CurUser.sysUserDesc = sub_a.getProperty("sysUserDesc").toString();
                        CurUser.workNumber = sub_a.getProperty("workNumber").toString();
                        CurUser.passWord = sub_a.getProperty("passWord").toString();
                        CurUser.dynaPSW = sub_a.getProperty("dynaPSW").toString();
                        CurUser.loginMode = sub_a.getProperty("loginMode").toString();
                        CurUser.orgID = sub_a.getProperty("orgID").toString();
                        CurUser.orgName = sub_a.getProperty("orgName").toString();
                        CurUser.loginMessage = sub_a.getProperty("loginMessage").toString();
                        result.append(sub_a.getProperty("loginMessage") + "\n");
                    }
                });
    }

    @OnClick(R.id.bt_getlist)
    public void getCustomerList() {
        Observable.create(new Observable.OnSubscribe<SoapObject>() {
            @Override
            public void call(Subscriber<? super SoapObject> subscriber) {
                subscriber.onNext(Request.listRequest(Constants.NAMESPACE, "PageQueryCustomerList", Constants.CUSTOMER_MANAGE));
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SoapObject>() {
                    @Override
                    public void call(SoapObject soapObject) {
                        if (soapObject != null) {
                            result.append(soapObject.toString());
                        } else {
                            Toast.makeText(mContext, "请求失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
