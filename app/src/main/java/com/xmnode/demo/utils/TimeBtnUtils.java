package com.xmnode.demo.utils;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.Button;

import com.xmnode.demo.R;


/*
 * 发送验证码时的倒计时
 */
public class TimeBtnUtils extends CountDownTimer {
	private Button btn;

	/**
	 *
	 * @param millisInFuture
	 * @param countDownInterval 倒计时的时间
     * @param btn
     */
	public TimeBtnUtils(long millisInFuture, long countDownInterval, Button btn) {
		super(millisInFuture, countDownInterval);
		this.btn = btn;
	}

	/**
	 * 计时开始时调用
	 * @param millisUntilFinished 计时时间
     */
	@SuppressLint("NewApi")
	@Override
	public void onTick(long millisUntilFinished) {
		btn.setClickable(false);// 设置不能点击
		btn.setText("已发送("+millisUntilFinished / 1000 + "s)");// 设置倒计时时间
		btn.setBackgroundResource(R.drawable.bg_btn_send_code_sended);
		Spannable span = new SpannableString(btn.getText().toString());// 获取按钮的文字
		btn.setText(span);
	}


	/**
	 * 计时结束时调用
	 */
	@SuppressLint("NewApi")
	@Override
	public void onFinish() {
		btn.setText("重新获取");
		btn.setClickable(true);// 重新获得点击
		btn.setBackgroundResource(R.drawable.bg_btn_send_code_normal);
	}
}