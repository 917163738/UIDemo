package com.xmnode.demo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.xmnode.demo.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GifActivity extends AppCompatActivity {
    private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例化WebView对象
//        webview = new WebView(this);
//        //设置WebView属性，能够执行Javascript脚本
//        webview.getSettings().setJavaScriptEnabled(true);
//        //加载需要显示的网页
//        webview.loadUrl("http://106.75.48.213:8080/upload/images/c3a860bb-365b-4f68-9538-4c61c503d983.gif");
//        //设置Web视图
//        setContentView(webview);
        setContentView(R.layout.activity_gif);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL("http://106.75.48.213:8080/upload/images/c3a860bb-365b-4f68-9538-4c61c503d983.gif");
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8*1000);
                    if(httpURLConnection.getResponseCode()==200){
                        httpURLConnection.getInputStream();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
