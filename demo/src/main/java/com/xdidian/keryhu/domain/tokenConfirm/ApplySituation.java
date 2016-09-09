package com.xdidian.keryhu.domain.tokenConfirm;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author 账号激活中，存在的3种使用类型，分别为
 * 1 注册的时候，邮箱激活
 * 2 密码重设的时候，邮箱或手机号取验证码
 * 3 个人资料修改的时候，获取新设邮箱或手机号的验证码
 *
 */

public enum ApplySituation {
	

	  SIGNUP,        //	 注册
	  RECOVER,       //  密码重设
	  EDIT    ;      //  个人资料修改时，手机号或邮箱修改
	
	
	
	
	/**
	 * 为了能过实现Enum role 对象 json 序列号和反序列化 进行的下列设置
	 */
	  
	  private static Map<String,ApplySituation> typeMap=new HashMap<String,ApplySituation>(3);
	  
	  static{
		  typeMap.put("SIGNUP", SIGNUP);
		  typeMap.put("RECOVER", RECOVER);
		  typeMap.put("EDIT", EDIT);   
		  
	  }
	  
	  @JsonCreator
	  public static ApplySituation forValue(String value){
		  return typeMap.get(value);
	  }
	  
	  @JsonValue
	  public String toValue(){
		  for(Entry<String,ApplySituation> type: typeMap.entrySet()){
			  if(type.getValue()==this)
				  return type.getKey();
		  }
		  return null;
	  }
	  

}
