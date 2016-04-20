package com.xdidian.keryhu.domain;

import java.util.HashMap;
import java.util.Map;

import java.util.Map.Entry;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
* @Description: TODO(用户User具有的权限)
* @ClassName: Role
* @Description: TODO(这里用一句话描述这个类的作用)
* @author keryhu  keryhu@hotmail.com
* @date 2015年11月19日 下午3:09:52
*
 */
public enum Role {
	  
	
	  ROLE_SERVICE,        //客服
	  
	 
	  ROLE_ADMIN,          //admin
	  
	 
	  ROLE_PROPERTY,       //物业公司
	  
	  
	  ROLE_TENANT,         // 租户
	  
	  
	  ROLE_INTENTION    ;   //看房者，有意向租办公场地的人员
	
	
	/**
	 * 为了能过实现Enum role 对象 json 序列号和反序列化 进行的下列设置
	 */
	  
	  private static Map<String,Role> roleMap=new HashMap<String,Role>(5);
	  
	  static{
		  roleMap.put("ROLE_SERVICE", ROLE_SERVICE);
		  roleMap.put("ROLE_ADMIN", ROLE_ADMIN);
		  roleMap.put("ROLE_PROPERTY", ROLE_PROPERTY);
		  roleMap.put("ROLE_TENANT", ROLE_TENANT);
		  roleMap.put("ROLE_INTENTION", ROLE_INTENTION);
		  
		  
	  }
	  
	  @JsonCreator
	  public static Role forValue(String value){
		  return roleMap.get(value);
	  }
	  
	  @JsonValue
	  public String toValue(){
		  for(Entry<String,Role> role: roleMap.entrySet()){
			  if(role.getValue()==this)
				  return role.getKey();
		  }
		  return null;
	  }
	  
	  

	  
	  
	  
}

