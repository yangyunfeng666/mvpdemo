package com.kye.basemodule.hybrid;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.WebView;

import com.kye.basemodule.log.KyeLogUtils;

import org.json.JSONObject;

import java.lang.ref.SoftReference;

/**
 * Created by yyf on 2018-04-17 16:48.
 */

public class JsCallback {

    private SoftReference<WebView> mWebViewRef;

    private String mSid;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    public JsCallback(WebView mWebViewRef, String mSid) {
        this.mSid = mSid;
        this.mWebViewRef = new SoftReference<WebView>(mWebViewRef) ;
    }


    private static final String CALLBACK_JS_FORMAT = "javascript:JsBridge.onComplete('%s', %s);";

    public void apply(boolean isSuccess, String message, JSONObject object) throws JsCallbackException {
        if (mWebViewRef.get() == null) {
            throw new JsCallbackException("the WebView related to the JsCallback has been recycled");
        }
//        if (!mCouldGoOn) {
//            throw new JsCallbackException("the JsCallback isn't permanent,cannot be called more than once");
//        }
        JSONObject result = new JSONObject();

        try {
            JSONObject code=new JSONObject();
            code.put("code", isSuccess ? 0 : 1);
            if(!isSuccess && !TextUtils.isEmpty(message)){
                code.putOpt("msg",message);
            }
            if(isSuccess){
                code.putOpt("msg", TextUtils.isEmpty(message)?"SUCCESS":message);
            }
            result.putOpt("status", code);
            if(null!=object){
                result.putOpt("data",object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String jsFunc = String.format(CALLBACK_JS_FORMAT, mSid, String.valueOf(result));

        if (mWebViewRef != null && mWebViewRef.get() != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    KyeLogUtils.i(jsFunc);
                    mWebViewRef.get().loadUrl(jsFunc);
                }
            });

        }
    }
    public class JsCallbackException extends IllegalArgumentException{
        public JsCallbackException(String s) {
            super(s);
        }
    }
}
