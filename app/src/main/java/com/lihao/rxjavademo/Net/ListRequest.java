package com.lihao.rxjavademo.Net;

import android.util.Log;

import com.lihao.rxjavademo.Constant.Constants;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.kxml2.kdom.Element;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by lihao on 2016/12/17.
 */

public class ListRequest {

    private SoapObject requestBody;
    private Element[] requestHeader;
    private PropertyInfo property;
    private String sortOrder = "";
    private String where = "";
    private String sortName = "";
    private int pageSize = 40;
    private int curPage = 1;
    private PageSet pageSet;
    private SoapSerializationEnvelope envelope;

    private ListRequest(){

    }

    public static ListRequest getInstance(){
        return new ListRequest();
    }

    public SoapObject setData(String nameSpace, String methodName, String url){
        requestBody = new SoapObject(nameSpace, methodName);
        if (!sortName.equals("")){
            sortOrder = "ORDER BY " + sortName + " " + sortOrder;
        }else {
            sortOrder = "";
        }
        pageSet = getPageSet(pageSize, curPage);
        requestHeader = NetUtil.setSoapHeader();
        property = new PropertyInfo();
        getPropertyInfo(property, requestBody, where);
        envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.addMapping(nameSpace, "pageSet", pageSet.getClass());
        envelope.dotNet = true;
        envelope.headerOut = requestHeader;
        Log.d("Lihao", requestBody.getPropertyCount() + "");
        envelope.bodyOut = requestBody;
        for (Element ele: requestHeader ) {
            for (int i = 0; i < ele.getChildCount(); i++) {
                Log.d("Lihao", ele.getChild(i).toString());
            }
        }
        Log.d("Lihao", nameSpace + methodName);
        Log.d("Lihao", url);
        HttpTransportSE ht = new HttpTransportSE(url);
        try {
            ht.call(nameSpace + methodName, envelope);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return (SoapObject) envelope.bodyIn;
    }

    private void getPropertyInfo(PropertyInfo property, SoapObject requestBody, String where) {
        property.setName("colList");
        property.setValue("*");
        requestBody.addProperty(property);
        property = new PropertyInfo();
        property.setName("strWhere");
        property.setValue(where);
        requestBody.addProperty(property);
        property = new PropertyInfo();
        property.setName("strOrder");
        property.setValue(sortOrder);
        requestBody.addProperty(property);
        property = new PropertyInfo();
        property.setName("myPageSet");
        property.setValue(pageSet);
        property.setType(pageSet.getClass());
        requestBody.addProperty(property);
    }

    private PageSet getPageSet(int pageSize, int curPage) {
        pageSet = new PageSet();
        pageSet.setProperty(0, pageSize);
        pageSet.setProperty(1, curPage);
        pageSet.setProperty(2, 0);
        pageSet.setProperty(3, 0);
        return pageSet;
    }
}
