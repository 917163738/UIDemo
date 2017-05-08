package com.xmnode.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.Intents;
import com.google.zxing.client.android.encode.EncodeActivity;
import com.xmnode.demo.R;
import com.xmnode.demo.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QrcodeActivity extends BaseActivity {

    private static final String TAG = QrcodeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);
    }

    @Bind(R.id.tv_scan_result)
    TextView mTvScanResult;

    @Bind(R.id.et_qr_string)
    EditText mEtQrcodeString;

    @Bind(R.id.iv_qr_image)
    ImageView mIvQrcode;

    @OnClick(R.id.btn_scan_qr)
    void scanQrcode() {
        Intent openCameraIntent = new Intent(QrcodeActivity.this, CaptureActivity.class);
        openCameraIntent.setAction(Intents.Scan.ACTION);
        openCameraIntent.putExtra(Intents.Scan.RESULT_DISPLAY_DURATION_MS, 0L);
        startActivityForResult(openCameraIntent, 0);
    }

    @OnClick(R.id.btn_generate_qrcode)
    void generateQrcode() {
        String content = mEtQrcodeString.getText().toString().trim();
        if (content != null) {
            Intent intent = new Intent(QrcodeActivity.this, EncodeActivity.class);
            intent.setAction(Intents.Encode.ACTION);
            intent.putExtra(Intents.Encode.TYPE, Contents.Type.TEXT);
            intent.putExtra(Intents.Encode.DATA, content);
            startActivity(intent);
        }
    }

    @OnClick(R.id.ivBack)
    void back() {
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = data.getStringExtra(Intents.Scan.RESULT);
            if (scanResult != null) {
                mTvScanResult.setText(scanResult);
            }
        }
    }
}
