package com.xmnode.demo.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

import com.xmnode.demo.R;
import com.xmnode.demo.base.BaseActivity;

/**
 * 功能描述：webview与原生交互
 * 创建人:  zhangcl
 * 时间: 	2016/7/5 17:32
 */
public class WebViewActivity extends BaseActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        initBar(getResources().getString(R.string.main_js));
        mWebView = (WebView) findViewById(R.id.webView);
        Button webViewBtn = (Button) findViewById(R.id.btn_webview);
        Button send2JSBtn = (Button) findViewById(R.id.btn_send_to_js);
        final EditText et = (EditText) findViewById(R.id.et_send_to_js);

        mWebView.loadUrl("file:///android_asset/index.html");
        mWebView.getSettings().setJavaScriptEnabled(true);
        //js调用原生方法,先进行js对象和java对象绑定
        mWebView.addJavascriptInterface(new JavaScriptObject(this), "myObj");
        if (webViewBtn != null) {
            webViewBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //原生调用js方法,必须在子线程中执行
                    mWebView.loadUrl("javascript:funFromjs()");
                }
            });
        }
        if (send2JSBtn != null) {
            send2JSBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //原生调用js方法,必须在子线程中执行
                    String s = et.getText().toString();
                    mWebView.loadUrl("javascript:funFromjs2('"+ s+"')");
                }
            });
        }

    }

    /**
     * 暴露给js的类，要与js进行绑定
     */
    public class JavaScriptObject {
        Context context;

        public JavaScriptObject(Context context) {
            this.context = context;
        }

        /**
         * 暴露给js调用的方法
         */
        @JavascriptInterface
        public void fun() {
            new AlertDialog.Builder(WebViewActivity.this).setMessage("JS调用原生方法成功!")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            }).setCancelable(false).show();
            //Toast.makeText(context, "调用原生方法成功", Toast.LENGTH_SHORT).show();
        }
        @JavascriptInterface
        public void fun2(String text) {
            new AlertDialog.Builder(WebViewActivity.this).setMessage("接收到JS发来数据："+text)
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            }).setCancelable(false).show();
            //Toast.makeText(context, "调用原生方法成功", Toast.LENGTH_SHORT).show();
        }
    }
}
