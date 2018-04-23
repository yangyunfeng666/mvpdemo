package com.yunsoft.mvpdemo.activity;

import android.os.Bundle;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.kye.basemodule.log.KyeLogUtils;
import com.yunsoft.mvpdemo.R;
import com.yunsoft.mvpdemo.hybrid.JsCallJava;
import com.yunsoft.mvpdemo.mvp.BaseMvpActivity;

/**
 * Created by yyf on 2018-04-17 11:26.
 * Hybrid类的例子
 */

public class JsBridActivity extends BaseMvpActivity {

    WebView webView ;

    @Override
    protected void onCreateBefore() {

    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_hybrid);
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setAllowFileAccess(false);
        webSettings.setUseWideViewPort(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDatabaseEnabled(false);
        webSettings.setAppCacheEnabled(false);
        webSettings.setBlockNetworkImage(true);
//        webView.addJavascriptInterface(new JsInterface(),"control");
        webView.setWebChromeClient(new InjectedChromeClient(new JsCallJava()));
        webView.loadUrl("file:///android_asset/jsbrid.html");
    }

    @Override
    protected void initData() {

    }


    private class InjectedChromeClient extends WebChromeClient {

        private JsCallJava mJscallJava;

        public InjectedChromeClient(JsCallJava mJscallJava) {
            this.mJscallJava = mJscallJava;
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            KyeLogUtils.i("onJsPrompt"+message);
            result.confirm(mJscallJava.call(view,message));
            return true;
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            KyeLogUtils.i("console:message:"+consoleMessage.message()+"sourceid"+consoleMessage.sourceId()+"lineNumber:"+consoleMessage.lineNumber());
            return super.onConsoleMessage(consoleMessage);
        }
    }

    public class JsInterface{

        @JavascriptInterface
        public void showToast(String toast){
            Toast.makeText(JsBridActivity.this,toast,Toast.LENGTH_SHORT).show();
            log("show toast success");
        }

        public void log(String msg){
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript:log("+"'"+msg+"'"+")");
                }
            });
        }
    }


}
