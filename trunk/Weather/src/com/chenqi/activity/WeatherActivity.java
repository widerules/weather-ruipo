package com.chenqi.activity;

import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import com.chenqi.domain.WeatherCondition;
import com.chenqi.service.AsyncImageLoader;
import com.chenqi.service.CallbackImpl;
import com.chenqi.service.SAXWeatherService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherActivity extends Activity {
	private AsyncImageLoader loader = new AsyncImageLoader();
	public Handler h;
	public EditText cityTxt;
	public TextView cityLabel;
	public TextView dateLabel;
	public TextView condition1Label;
	public TextView highTmpLabel;
	public TextView lowTmpLabel;
	public TextView humidityLabel;
	public ProgressDialog dialog;
	public TextView week2Label;
	public TextView highTmp2Label;
	public TextView lowTmp2Label;
	public TextView condition2Labe;
	public ImageView imageView1;
	public ImageView imageView2;
	public ImageView imageView3;
	public ImageView imageView4;
	public TextView week3Label;
	public TextView highTmp3Label;
	public TextView lowTmp3Label;
	public TextView condition3Labe;
	
	public TextView week4Label;
	public TextView highTmp4Label;
	public TextView lowTmp4Label;
	public TextView condition4Labe;
	
	public TextView week5Label;
	public TextView highTmp5Label;
	public TextView lowTmp5Label;
	public TextView condition5Labe;
	public TextView tishiView;
	
	//public ImageView imageView;
	public TextView windconditionLabel;
	public Button findBtn;
    public	List<WeatherCondition>	weatherConditions;
    
	public void onCreate(Bundle savedInstanceState) {
		final String TAG="WeatherActivity";
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//dialog=new ProgressDialog(this);
		cityTxt=(EditText)this.findViewById(R.id.cityTxt);
		cityLabel=(TextView)this.findViewById(R.id.cityLabel);
		dateLabel=(TextView)this.findViewById(R.id.dateLabel);
		condition1Label=(TextView)this.findViewById(R.id.condition1);
		highTmpLabel=(TextView)this.findViewById(R.id.highTmp);
		lowTmpLabel=(TextView)this.findViewById(R.id.LowTmp);
		humidityLabel=(TextView)this.findViewById(R.id.humidity);
		windconditionLabel=(TextView)this.findViewById(R.id.wind_condition);
		imageView1=(ImageView)this.findViewById(R.id.fenge1);
		imageView2=(ImageView)this.findViewById(R.id.fenge2);
		imageView3=(ImageView)this.findViewById(R.id.fenge3);
		imageView4=(ImageView)this.findViewById(R.id.fenge4);
		 week2Label=(TextView)this.findViewById(R.id.week2);
		 highTmp2Label=(TextView)this.findViewById(R.id.HighTmp2);
		 lowTmp2Label=(TextView)this.findViewById(R.id.LowTmp2);
		 condition2Labe=(TextView)this.findViewById(R.id.condition2);
		 
		 week3Label=(TextView)this.findViewById(R.id.week3);
		 highTmp3Label=(TextView)this.findViewById(R.id.HighTmp3);
		 lowTmp3Label=(TextView)this.findViewById(R.id.LowTmp3);
		 condition3Labe=(TextView)this.findViewById(R.id.condition3);
		 
		 week4Label=(TextView)this.findViewById(R.id.week4);
		 highTmp4Label=(TextView)this.findViewById(R.id.HighTmp4);
		 lowTmp4Label=(TextView)this.findViewById(R.id.LowTmp4);
		 condition4Labe=(TextView)this.findViewById(R.id.condition4);
		 
		 week5Label=(TextView)this.findViewById(R.id.week5);
		 highTmp5Label=(TextView)this.findViewById(R.id.HighTmp5);
		 lowTmp5Label=(TextView)this.findViewById(R.id.LowTmp5);
		 condition5Labe=(TextView)this.findViewById(R.id.condition5);
		 tishiView=(TextView)this.findViewById(R.id.tishi);
		
		findBtn=(Button)this.findViewById(R.id.findBtn);
		findBtn.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				//点击查询按钮软键盘消失
				((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(WeatherActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				ConnectivityManager cwjManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); 
				if(cwjManager.getActiveNetworkInfo()==null||cwjManager.getActiveNetworkInfo().isAvailable()==false){
					Toast.makeText(WeatherActivity.this, getResources().getString(R.string.nonet), Toast.LENGTH_LONG).show();
					return;
				}
				dialog=ProgressDialog.show(WeatherActivity.this, getResources().getString(R.string.wait), getResources().getString(R.string.getting),false);
				new Thread() {
					public void run() {
						try {
							//String weather = "";
							String url = "http://www.google.com/ig/api?weather="+cityTxt.getText().toString().trim();
							// DefaultHttpClient - 默认实现HTTP客户端
							DefaultHttpClient client = new DefaultHttpClient();
							HttpUriRequest req = new HttpGet(url);
							// HttpResponse - HTTP响应
							HttpResponse resp = client.execute(req);	// 使用默认的上下文执行请求
							HttpEntity ent = resp.getEntity();		// 得到Entity的回应
							InputStream stream = ent.getContent();		// 创建一个InputStream对象的实体
							try
							{
							 	weatherConditions=new SAXWeatherService().getWeatherConditions(stream);
							} catch (Throwable e)
							{
								e.printStackTrace();
							}
							dialog.dismiss();
							Message msg = h.obtainMessage(0, "OK");
							h.sendMessage(msg);
							for(WeatherCondition weatherCondition:weatherConditions){
								Log.i(TAG, weatherCondition.toString());
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
		});
		h = new Handler() {
			public void handleMessage(Message msg) {
				if(weatherConditions.size()<=0){
					Toast.makeText(WeatherActivity.this, getResources().getString(R.string.notfind), Toast.LENGTH_LONG).show();
					return;
				}
				//weatherConditions=(List<WeatherCondition>)msg.obj;
				cityLabel.setText(getResources().getString(R.string.city)+weatherConditions.get(0).getCity());
				dateLabel.setText(getResources().getString(R.string.date)+weatherConditions.get(0).getDate());
				condition1Label.setText(getResources().getString(R.string.weather)+weatherConditions.get(1).getCondition());
				highTmpLabel.setText(getResources().getString(R.string.centigrade)+weatherConditions.get(1).getTemp_c());
				lowTmpLabel.setText(getResources().getString(R.string.fahrenheit)+weatherConditions.get(1).getTemp_f());
				humidityLabel.setText(getResources().getString(R.string.humidity)+weatherConditions.get(1).getHumidity());
				windconditionLabel.setText(getResources().getString(R.string.wind)+weatherConditions.get(1).getWind_condition());
				imageView1.setImageResource(R.drawable.line);
				loadImage("http://www.google.com"+weatherConditions.get(1).getIcon(), R.id.img1);
				tishiView.setText(getResources().getString(R.string.future));
				setValue(week2Label, highTmp2Label, lowTmp2Label, condition2Labe, weatherConditions.get(2));
				loadImage("http://www.google.com"+weatherConditions.get(2).getIcon(), R.id.img2);
				imageView2.setImageResource(R.drawable.line);
				setValue(week3Label, highTmp3Label, lowTmp3Label, condition3Labe, weatherConditions.get(3));
				loadImage("http://www.google.com"+weatherConditions.get(3).getIcon(), R.id.img3);
				imageView3.setImageResource(R.drawable.line);
				setValue(week4Label, highTmp4Label, lowTmp4Label, condition4Labe, weatherConditions.get(4));
				loadImage("http://www.google.com"+weatherConditions.get(4).getIcon(), R.id.img4);
				imageView4.setImageResource(R.drawable.line);
				setValue(week5Label, highTmp5Label, lowTmp5Label, condition5Labe, weatherConditions.get(5));
				loadImage("http://www.google.com"+weatherConditions.get(5).getIcon(), R.id.img5);
				
			}
		};

	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
           //弹出确定退出对话框
           new AlertDialog.Builder(this)
           .setTitle(getString(R.string.exit))		// 为对话框设置标题
           .setMessage(getString(R.string.isexit))		// 为对话框设置提示信息
           .setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   Intent exit = new Intent(Intent.ACTION_MAIN);
                   exit.addCategory(Intent.CATEGORY_HOME);
                   exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(exit);		// 运行一个新的Activity，将不会收到存在的Activity的任何信息 
                   System.exit(0);		// 正常退出系统
               }
           })
           .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                   dialog.cancel();
               }
           })
           .show();
           //这里不需要执行父类的点击事件，所以直接return
           return true;
		}
		//继续执行父类的其他点击事件
		return super.onKeyDown(keyCode, event);
	}
	public String getCtmp(String ftmp){
		String ctmp;
		ctmp=String.valueOf((Double.valueOf(ftmp)-32)/1.8);
		if(Integer.valueOf(ctmp.substring(ctmp.lastIndexOf(".")+1, ctmp.lastIndexOf(".")+2))>5) {
			ctmp=String.valueOf((Integer.valueOf(ctmp.substring(0,ctmp.lastIndexOf(".")))+1));
		} else {
			ctmp=ctmp.substring(0,ctmp.lastIndexOf("."));
		}
		return ctmp;
	}
	private void setValue(TextView week,TextView high,TextView low,TextView condition,WeatherCondition info){
		week.setText(getResources().getString(R.string.time)+info.getDay_of_week());
		high.setText(getResources().getString(R.string.highest)+getCtmp(info.getHighTmp()));
		low.setText(getResources().getString(R.string.lowest)+getCtmp(info.getLowTmp()));
		condition.setText(getResources().getString(R.string.weather)+info.getCondition());
	}
	//url：下载图片的URL
    //id：ImageView控件的ID
    private void loadImage(final String url, final int id) {
		// 如果缓存过就会从缓存中取出图像，ImageCallback接口中方法也不会被执行
    	ImageView imageView = (ImageView)findViewById(id);
    	CallbackImpl callbackImpl = new CallbackImpl(imageView);
    	Drawable cacheImage = loader.loadDrawable(url, callbackImpl);
		if (cacheImage != null) {
			imageView.setImageDrawable(cacheImage);
		}
	}
}