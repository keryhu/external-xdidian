package com.xdidian.keryhu.domain.tokenConfirm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * 当密码重设的时候，选择何种方式，取得验证码，有两种
 * 1  手机号
 * 2 email
 *
 */


public enum RecoverMethod {
	

	  EMAIL,        //	 email
	  PHONE;        //   手机号 
	
	
	
	
	/**
	 * 为了能过实现Enum role 对象 json 序列号和反序列化 进行的下列设置
	 */
	  
	  private static Map<String,RecoverMethod> recoverMethodMap=new HashMap<String,RecoverMethod>(2);
	  
	  static{
		  recoverMethodMap.put("EMAIL", EMAIL);
		  recoverMethodMap.put("PHONE", PHONE);
		  
	  }
	  
	  @JsonCreator
	  public static RecoverMethod forValue(String value){
		  return recoverMethodMap.get(value);
	  }
	  
	  @JsonValue
	  public String toValue(){
		  for(Entry<String,RecoverMethod> recoverMethod: recoverMethodMap.entrySet()){
			  if(recoverMethod.getValue()==this)
				  return recoverMethod.getKey();
		  }
		  return null;
	  }

}
