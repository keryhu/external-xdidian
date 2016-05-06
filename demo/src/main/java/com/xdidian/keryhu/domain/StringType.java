package com.xdidian.keryhu.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
* @ClassName: StringType
* @Description: TODO(新地点制定的几种string 类型格式，)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月6日 下午12:02:47
 */
public enum StringType {
	
	EMAIL ,   //邮箱
	PHONE ,   //手机号
	PASSWORD,   //密码格式
	COMPANY_NAME,  // 公司名字
	PEOPLE_NAME,    // 人的姓名
	UUID;           //uuid
	
	/**
	 * 为了能过实现Enum 对象 json 序列号和反序列化 进行的下列设置
	 */
	  
	  private static Map<String,StringType> stringTypeMap=new HashMap<String,StringType>(6);
	

	  static {
		  stringTypeMap.put("EMAIL", EMAIL);
		  stringTypeMap.put("PHONE", PHONE);
		  stringTypeMap.put("PASSWORD", PASSWORD);
		  stringTypeMap.put("COMPANY_NAME", COMPANY_NAME);
		  stringTypeMap.put("PEOPLE_NAME", PEOPLE_NAME);
		  stringTypeMap.put("UUID", UUID);
	  }
	  
	  @JsonCreator
	  public static StringType forValue(String value){
		  return stringTypeMap.get(value);
	  }
	  
	  @JsonValue
	  public String toValue(){
		  for(Entry<String,StringType> stringType: stringTypeMap.entrySet()){
			  if(stringType.getValue()==this)
				  return stringType.getKey();
		  }
		  return null;
	  }
	  
}
