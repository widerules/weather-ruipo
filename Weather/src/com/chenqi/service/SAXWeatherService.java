package com.chenqi.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import com.chenqi.domain.WeatherCondition;

/**
 * 采用SAX解析XML内容
 */
public class SAXWeatherService {
	
	public List<WeatherCondition> getWeatherConditions(InputStream inStream) throws Throwable{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();		// 使用当前的配置工厂参数创建一个新的SAXParser实例
		WeatherConditionParser WeatherConditionParser = new WeatherConditionParser();
		parser.parse(inStream, WeatherConditionParser);		// 解析流的内容
		inStream.close();		// 关闭流
		return WeatherConditionParser.getWeatherConditions();	// 返回一个新的SAXParser实例
	}

	private final class WeatherConditionParser extends DefaultHandler{		// Default base class for SAX2 event handlers
		private List<WeatherCondition> WeatherConditions = null;
		private String tag = null;
		private WeatherCondition WeatherCondition = null;

		public List<WeatherCondition> getWeatherConditions() {
			return WeatherConditions;
		}

		@Override
		public void startDocument() throws SAXException {
			WeatherConditions = new ArrayList<WeatherCondition>();
		}
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if("forecast_information".equals(localName)){
				WeatherCondition = new WeatherCondition();
			}
			if("current_conditions".equals(localName)){
				WeatherCondition = new WeatherCondition();
			}
			if("forecast_conditions".equals(localName)){
				WeatherCondition = new WeatherCondition();
			}
			if("city".equals(localName)){
				WeatherCondition.setCity(new String(attributes.getValue(0)));
			}
			if("forecast_date".equals(localName)){
				WeatherCondition.setDate(new String(attributes.getValue(0)));
			}
			if("condition".equals(localName)){
				WeatherCondition.setCondition(new String(attributes.getValue(0)));
			}
			if("temp_f".equals(localName)){
				WeatherCondition.setTemp_f(new String(attributes.getValue(0)));
			}
			if("temp_c".equals(localName)){
				WeatherCondition.setTemp_c(new String(attributes.getValue(0)));
			}
			if("humidity".equals(localName)){
				WeatherCondition.setHumidity(new String(attributes.getValue(0)));
			}
			if("icon".equals(localName)){
				WeatherCondition.setIcon(new String(attributes.getValue(0)));
			}
			if("wind_condition".equals(localName)){
				WeatherCondition.setWind_condition(new String(attributes.getValue(0)));
			}
			if("day_of_week".equals(localName)){
				WeatherCondition.setDay_of_week(new String(attributes.getValue(0)));
			}
			if("low".equals(localName)){
				WeatherCondition.setLowTmp(new String(attributes.getValue(0)));
			}
			if("high".equals(localName)){
				WeatherCondition.setHighTmp(new String(attributes.getValue(0)));
			}
			tag = localName;
}
		
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			if(tag!=null){
				//String data = new String(ch, start, length);//获取文本节点的数据
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			if("forecast_information".equals(localName)){
				WeatherConditions.add(WeatherCondition);
				WeatherCondition = null;
			}
			if("current_conditions".equals(localName)){
				WeatherConditions.add(WeatherCondition);
				WeatherCondition = null;
			}
			if("forecast_conditions".equals(localName)){
				WeatherConditions.add(WeatherCondition);
				WeatherCondition = null;
			}
			tag = null;
		}
	}
}
