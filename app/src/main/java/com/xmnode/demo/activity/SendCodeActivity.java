package com.xmnode.demo.activity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xmnode.demo.R;
import com.xmnode.demo.base.BaseActivity;
import com.xmnode.demo.utils.TimeBtnUtils;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SendCodeActivity extends BaseActivity {

    @Bind(R.id.btn_send)
    Button mBtnSend;
    @Bind(R.id.tv_code)
    TextView mTvCode;
    @Bind(R.id.btn_confirm_code)
    Button mBtnConfirmCode;
    @Bind(R.id.etCode)
    EditText mEtCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_code);
        initBar(getResources().getString(R.string.main_sendcode));
        ButterKnife.bind(this);
        mEtCode.setInputType(InputType.TYPE_CLASS_NUMBER);
    }

    @OnClick(R.id.btn_send)
    public void onClick() {
        Random random = new Random();
        int i = random.nextInt(999999);
        mTvCode.setText(i + "");
        Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
        TimeBtnUtils timeCountUtils = new TimeBtnUtils(6000, 1000, mBtnSend);
        timeCountUtils.start();
    }


    @OnClick(R.id.btn_confirm_code)
    public void btn_confirm_code() {
        String codeEt = mEtCode.getText().toString();
        String code = mTvCode.getText().toString();
        if ("".equals(codeEt)) {
            Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (code.equals(codeEt)) {
            Toast.makeText(this, "验证成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "请输入正确验证码", Toast.LENGTH_SHORT).show();
        }
    }

}
