package com.yunsoft.mvpdemo.hybrid;

import android.webkit.WebView;
import android.widget.Toast;

import com.kye.basemodule.hybrid.JsCallback;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yyf on 2018-04-17 16:47.
 */

public class JSLogical implements IInject {

    /**
     * 加一
     *
     * @param webView
     * @param param
     * @param callback
     */
    public static void plus(WebView webView, final JSONObject param, final JsCallback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    int original = param.optInt("data");
                    original = original + 1;
                    if (null != callback) {
                        JSONObject object = new JSONObject();
                        object.put("after plussing", original);
                        invokeJSCallback(callback, object);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void toast(WebView webView, JSONObject object,JsCallback jsCallback){

        String message = object.optString("message");
        int isShowLong = object.optInt("isShowLong");
        Toast.makeText(webView.getContext(),message,isShowLong==0?Toast.LENGTH_SHORT:Toast.LENGTH_LONG).show();
        if(jsCallback!=null){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("result",true);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            invokeJSCallback(jsCallback,jsonObject);
        }
    }

    private static void invokeJSCallback(JsCallback callback, JSONObject objects) {
        invokeJSCallback(callback, true, null, objects);
    }

    public static void invokeJSCallback(JsCallback callback, boolean isSuccess, String message, JSONObject objects) {
        try {
            callback.apply(isSuccess, message, objects);
        } catch (JsCallback.JsCallbackException e) {
            e.printStackTrace();
        }
    }
}
