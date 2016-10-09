package com.xdidian.keryhu.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author hushuming
 * 
 * 当对一件事情，审核的时候，有两种结果，同意和拒绝。
 *
 */


public enum CheckType {
	AGREE,      //  同意
	REJECT;     //   拒绝
	
	
	private static Map<String,CheckType> checkTypeMap=new HashMap<String,CheckType>(2);
	  
	  static{
		  checkTypeMap.put("AGREE", AGREE);
		  checkTypeMap.put("REJECT", REJECT);	  
	  }
	  
	  @JsonCreator
	  public static CheckType forValue(String value){
		  return checkTypeMap.get(value);
	  }
	  
	  @JsonValue
	  public String toValue(){
		  for(Entry<String,CheckType> checkType: checkTypeMap.entrySet()){
			  if(checkType.getValue()==this)
				  return checkType.getKey();
		  }
		  return null;
	  }
	

}
