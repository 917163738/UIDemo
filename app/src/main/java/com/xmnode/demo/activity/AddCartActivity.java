package com.xmnode.demo.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.Animator.AnimatorListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xmnode.demo.R;

@SuppressLint("NewApi")
public class AddCartActivity extends Activity implements OnClickListener {

	private static int HEIGHT = 100;// 抛物线高度

	private FrameLayout fl_addlove;
	private FrameLayout fl_love;
	private FrameLayout fl_addlocker;
	private FrameLayout fl_locker;

	private float a;
	private float b;
	private float c;

	private ImageView anim;

	private int addlove_width;
	private int addlove_height;
	private int addlocker_width;
	private int addlocker_height;

	private ImageView img_love;
	private ImageView img_cart;
	private TextView merchant_love;
	private TextView locker_add;

	private boolean isLoving;
	private boolean isLocking;
	private boolean isLoved;
	private boolean isLocked;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activtiy_addcart);

		fl_addlove = (FrameLayout) findViewById(R.id.fl_addlove);
		fl_love = (FrameLayout) findViewById(R.id.fl_love);
		fl_addlocker = (FrameLayout) findViewById(R.id.fl_addlocker);
		fl_locker = (FrameLayout) findViewById(R.id.fl_locker);
		img_love = (ImageView) findViewById(R.id.img_love);
		img_cart = (ImageView) findViewById(R.id.img_cart);
		merchant_love = (TextView) findViewById(R.id.merchant_love);
		locker_add = (TextView) findViewById(R.id.locker_add);

		fl_addlove.setOnClickListener(this);
		fl_addlocker.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.fl_addlove:
				if (!isLoving && !isLoved) {
					toLoveAnim();
					break;
				}
				if (isLoved) {
					isLoved = false;
					setData();
				}
				break;
			case R.id.fl_addlocker:
				if (!isLocking && !isLocked) {
					toLockerAnim();
					break;
				}
				if (isLocked) {
					isLocked = false;
					setData();
				}
				break;
			default:
				break;
		}
	}

	private void setData() {
		// TODO Auto-generated method stub
		if (isLoved) {
			merchant_love.setText("已收藏");
			img_love.setImageResource(R.drawable.anim_heart);
		} else {
			merchant_love.setText("收藏店铺");
			img_love.setImageResource(R.drawable.gd_heart);
		}

		if (isLocked) {
			locker_add.setText("已加入");
			img_cart.setImageResource(R.drawable.anim_cart);
		} else {
			locker_add.setText("加入储物柜");
			img_cart.setImageResource(R.drawable.gd_cart);
		}

	}

	private void toLockerAnim() {
		isLocking = true;
		setParamsLocker();
		anim = new ImageView(this);
		anim.setImageResource(R.drawable.gd_point);
		int[] start_anim1 = { startlocker[0] + addlocker_width / 2,
				startlocker[1] };
		startAnimation(anim, start_anim1, false);
	}

	private void toLoveAnim() {
		isLoving = true;
		setParamsLove();
		anim = new ImageView(this);
		anim.setImageResource(R.drawable.gd_point);
		int[] start_anim = { startlove[0] + addlove_width / 2, startlove[1] };
		startAnimation(anim, start_anim, true);
	}

	private void setParamsLocker() {
		// TODO Auto-generated method stub
		setCount(Math.abs(startlocker[0] + addlocker_width / 2 - endlocker[0]));
		float[][] points = {
				{ startlocker[0] + addlocker_width / 2, 0 - startlocker[1] },
				{ endlocker[0], 0 - endlocker[1] },
				{ (startlocker[0] + addlocker_width / 2 + endlocker[0]) / 2,
						0 - startlocker[1] + HEIGHT } };
		float[] results = calculate(points);
		a = results[0];
		b = results[1];
		c = results[2];
		System.err.println(a + "," + b + "," + c);
	}

	private void setParamsLove() {
		// TODO Auto-generated method stub
		setCount(Math.abs(startlove[0] + addlove_width / 2 - endlove[0]));
		float[][] points = {
				{ startlove[0] + addlove_width / 2, 0 - startlove[1] },
				{ endlove[0], 0 - endlove[1] },
				{ (startlove[0] + addlove_width / 2 + endlove[0]) / 2,
						0 - startlove[1] + HEIGHT } };
		float[] results = calculate(points);
		a = results[0];
		b = results[1];
		c = results[2];
		System.err.println(a + "," + b + "," + c);
	}

	private ViewGroup anim_mask_layout;

	int count = 300;

	private void setCount(int count) {
		this.count = count;
	}

	int[] startlove = new int[2];
	int[] endlove = new int[2];
	int[] startlocker = new int[2];
	int[] endlocker = new int[2];

	/**
	 * 要start 动画的那张图片的ImageView
	 *
	 * @param imageView
	 */
	private void startAnimation(final ImageView imageView,
								int[] start_location, final boolean forward) {
		anim_mask_layout = null;
		anim_mask_layout = createAnimLayout();
		anim_mask_layout.addView(imageView);// 把动画小球添加到动画层
		final View view = addViewToAnimLayout(anim_mask_layout, imageView,
				start_location);
		Keyframe[] keyframes = new Keyframe[count];
		final float keyStep = 1f / (float) count;
		float key = keyStep;
		for (int i = 0; i < count; ++i) {
			keyframes[i] = Keyframe.ofFloat(key, forward ? i + 1 : -(i + 1));
			key += keyStep;
		}

		PropertyValuesHolder pvhX = PropertyValuesHolder.ofKeyframe(
				"translationX", keyframes);
		key = keyStep;
		for (int i = 0; i < count; ++i) {
			keyframes[i] = Keyframe.ofFloat(key, forward ? -getY(i + 1
					+ start_location[0])
					+ getY(start_location[0]) : -getY(-i - 1
					+ start_location[0])
					+ getY(start_location[0]));
			// System.err.println(-getY(-i - 1 + start_location[0])+
			// getY(start_location[0])
			// + "----------"
			// + (-i - 1 + start_location[0]));
			key += keyStep;
		}

		PropertyValuesHolder pvhY = PropertyValuesHolder.ofKeyframe(
				"translationY", keyframes);
		ObjectAnimator yxBouncer = ObjectAnimator.ofPropertyValuesHolder(view,
				pvhY, pvhX).setDuration(800);
		yxBouncer.setInterpolator(new LinearInterpolator());

		Animation scaleAnimation = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f);
		AnimationSet set = new AnimationSet(false);
		set.addAnimation(scaleAnimation);
		AnimatorSet animatorSet = new AnimatorSet();// 组合动画
		ObjectAnimator scaleX = ObjectAnimator
				.ofFloat(anim, "scaleX", 1f, 0.5f);
		ObjectAnimator scaleY = ObjectAnimator
				.ofFloat(anim, "scaleY", 1f, 0.5f);
		animatorSet.setDuration(800);
		animatorSet.setInterpolator(new DecelerateInterpolator());
		animatorSet.play(yxBouncer);// .with(scaleY).with(scaleX);// 两个动画同时开始
		animatorSet.start();

		animatorSet.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				if (forward) {
					isLoving = false;
					isLoved = true;
				} else {
					isLocking = false;
					isLocked = true;
				}
				setData();
				anim_mask_layout.removeAllViews();
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * @param x
	 * @return
	 */
	private float getY(float x) {
		return a * x * x + b * x;
	}

	private float[] calculate(float[][] points) {
		float x1 = points[0][0];
		float y1 = points[0][1];
		float x2 = points[1][0];
		float y2 = points[1][1];
		float x3 = points[2][0];
		float y3 = points[2][1];

		final float a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
				/ (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3
				* (x1 - x2));
		final float b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
		final float c = y1 - (x1 * x1) * a - x1 * b;

		float[] results = { a, b, c };
		return results;
	}

	private ViewGroup createAnimLayout() {
		ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
		LinearLayout animLayout = new LinearLayout(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		animLayout.setLayoutParams(lp);
		animLayout.setId(Integer.MAX_VALUE);
		animLayout.setBackgroundResource(android.R.color.transparent);
		rootView.addView(animLayout);
		return animLayout;
	}

	private View addViewToAnimLayout(final ViewGroup vg, final View view,
									 int[] location) {
		int x = location[0];
		int y = location[1];
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		lp.leftMargin = x;
		lp.topMargin = y;
		view.setLayoutParams(lp);
		return view;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// TODO Auto-generated method stub
		addlove_width = fl_addlove.getWidth();
		addlove_height = fl_addlove.getHeight();
		addlocker_width = fl_addlocker.getWidth();
		addlocker_height = fl_addlocker.getHeight();

		fl_addlove.getLocationInWindow(startlove);
		img_love.getLocationInWindow(endlove);
		fl_addlocker.getLocationInWindow(startlocker);
		img_cart.getLocationInWindow(endlocker);
		super.onWindowFocusChanged(hasFocus);
	}
}
