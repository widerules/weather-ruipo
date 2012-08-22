package com.chenqi.domain;

public class WeatherCondition
{
	public String city;		//城市
	 public String date;	//日期
	 public String condition;		//天气
	 public String temp_f;	//华氏度
	 public String temp_c;		//摄氏度
	 public String humidity;	//湿度
	 public String icon;	//图片
	 public String day_of_week;		
	 public String lowTmp;	//最低温度
	 public String wind_condition ;		//风力
	 public String getWind_condition()
	{
		return wind_condition;
	}
	public void setWind_condition(String windCondition)
	{
		wind_condition = windCondition;
	}
	
	@Override
	public String toString()
	{
		return "WeatherCondition [city=" + city + ", condition=" + condition + ", date=" + date + ", day_of_week=" + day_of_week + ", highTmp=" + highTmp + ", humidity=" + humidity + ", icon=" + icon + ", lowTmp=" + lowTmp + ", temp_c=" + temp_c + ", temp_f=" + temp_f + ", wind_condition=" + wind_condition + "]";
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city = city;
	}
	public String getDate()
	{
		return date;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	public String getCondition()
	{
		return condition;
	}
	public void setCondition(String condition)
	{
		this.condition = condition;
	}
	public String getTemp_f()
	{
		return temp_f;
	}
	public void setTemp_f(String tempF)
	{
		temp_f = tempF;
	}
	public String getTemp_c()
	{
		return temp_c;
	}
	public void setTemp_c(String tempC)
	{
		temp_c = tempC;
	}
	public String getHumidity()
	{
		return humidity;
	}
	public void setHumidity(String humidity)
	{
		this.humidity = humidity;
	}
	public String getIcon()
	{
		return icon;
	}
	public void setIcon(String icon)
	{
		this.icon = icon;
	}
	public String getDay_of_week()
	{
		return day_of_week;
	}
	public void setDay_of_week(String dayOfWeek)
	{
		day_of_week = dayOfWeek;
	}
	public String getLowTmp()
	{
		return lowTmp;
	}
	public void setLowTmp(String lowTmp)
	{
		this.lowTmp = lowTmp;
	}
	public String getHighTmp()
	{
		return highTmp;
	}
	public void setHighTmp(String highTmp)
	{
		this.highTmp = highTmp;
	}
	public String highTmp;
}
