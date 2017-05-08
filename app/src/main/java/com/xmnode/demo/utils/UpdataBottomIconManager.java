package com.xmnode.demo.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/22.
 */
public class UpdataBottomIconManager {
    private Context mContext;
    private static UpdataBottomIconManager sUpdataBottomIconManager;
    public static boolean sIsUseNetIcon = false;

    public static UpdataBottomIconManager getInsance(Context context) {
        if (sUpdataBottomIconManager == null) {
            sUpdataBottomIconManager = new UpdataBottomIconManager(context);
        }
        return sUpdataBottomIconManager;
    }

    private UpdataBottomIconManager(Context context) {
        this.mContext = context;
        File file = null;
        try {
            file = new File(PATH_ICON);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUrl() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(5000);
        client.get("http://www.baidu.com", new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    sIsUseNetIcon = true;
                        loadImageFromNet(urlArray1, true);
                        loadImageFromNet(urlArray2, false);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                sIsUseNetIcon = false;
            }
        });
    }

    private ArrayList<BitmapDrawable> bitmapDrawables1 = new ArrayList<>();
    private ArrayList<BitmapDrawable> bitmapDrawables2 = new ArrayList<>();
private boolean mIsDefaultFinish=false;
private boolean mIsCheckFinish=false;
    public void loadImageFromNet(final String[] urlArray, final boolean isDefaultImage) {
        try {
            //创建一个子线程做网络请求
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        for (int i = 0; i < urlArray.length; i++) {
                            URL url = new URL(urlArray[i]);
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setRequestMethod("GET");//设置请求方式
                            connection.setConnectTimeout(1000 * 5);//设置超时时间
                            int code = connection.getResponseCode();
                            if (code == 200) {
                                InputStream inputStream = connection.getInputStream();
//							Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                if (isDefaultImage) {
                                    saveImageIcon(inputStream, "default" + i);
                                    mIsDefaultFinish=i==urlArray.length-1;
//                                    BitmapDrawable bitmapDrawable = new BitmapDrawable(inputStream);
//                                    bitmapDrawables1.add(bitmapDrawable);
                                } else {
                                    saveImageIcon(inputStream, "check" + i);
                                    mIsCheckFinish=i==urlArray.length-1;
//                                    BitmapDrawable bitmapDrawable = new BitmapDrawable(inputStream);
//                                    bitmapDrawables2.add(bitmapDrawable);
                                }
//                                if (i >= urlArray.length - 1) {
//                                    Message msg = Message.obtain();
//                                    msg.what = isDefaultImage ? 101 : 100;
//                                    handler.sendMessage(msg);
//                                }
                                if(mIsCheckFinish&&mIsDefaultFinish){
                                    Message msg = Message.obtain();
                                    msg.what = 100;
                                    handler.sendMessage(msg);
                                }
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private final static String PATH_ICON = Environment.getExternalStorageDirectory()
            + "/DEMO/icon/";

    private void saveImageIcon(InputStream inputStream, String name) throws IOException {
        File file = new File(PATH_ICON + name);
        file.deleteOnExit();
        file.createNewFile();
        FileOutputStream out = new FileOutputStream(file);
        int len = -1;
//        int readNum = 0;
        byte[] buffer = new byte[512];
        while ((len = inputStream.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
    }

    public final static String ACTION_REFRESH_BOTTM = "action_refresh_bottom";

    private void sendRefreshBottomBroad() {
        if (mContext != null) {
            mContext.sendBroadcast(new Intent(ACTION_REFRESH_BOTTM));
        }

    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100 ) {
                Toast.makeText(mContext, "sava finsh", Toast.LENGTH_LONG).show();
                sendRefreshBottomBroad();
            }
        }
    };
    private final static String url_str = "http://pic.sdodo.com/icon/1/png-1085.png";
    private final static String url_str2 = "http://img1.imgtn.bdimg.com/it/u=1061728136,2466064523&fm=21&gp=0.jpg";
    private final static String[] urlArray1 = new String[]{url_str, url_str, url_str, url_str, url_str};
    private final static String[] urlArray2 = new String[]{url_str2, url_str2, url_str2, url_str2, url_str2};
}
