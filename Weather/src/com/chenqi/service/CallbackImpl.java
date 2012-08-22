package com.chenqi.service;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class CallbackImpl implements ImageCallback{
	private ImageView imageView ;
	
	public CallbackImpl(ImageView imageView) {
		super();
		this.imageView = imageView;
	}

	@Override
	public void imageLoaded(Drawable imageDrawable) {
		imageView.setImageDrawable(imageDrawable);		// 设置一个图像作为ImageView控件的内容
	}

}
