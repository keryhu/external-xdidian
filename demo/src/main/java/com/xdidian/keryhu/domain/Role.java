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
	  
	
	  ROLE_DEFAULT,        //默认类型，值可以读写，自身的数据
	 
	  ROLE_COMPANY_ADMIN,          // 客户公司的company admin 公司的管理员
	  
	  ROLE_SOME_DEPARTMENT    ;   //公司的拥有读取某些部门数据的roles
	
	
	/**
	 * 为了能过实现Enum role 对象 json 序列号和反序列化 进行的下列设置
	 */
	  
	  private static Map<String,Role> roleMap=new HashMap<String,Role>(5);
	  
	  static{
		  roleMap.put("ROLE_SERVICE", ROLE_DEFAULT);
		  roleMap.put("ROLE_COMPANY_ADMIN", ROLE_COMPANY_ADMIN);
		  roleMap.put("ROLE_SOME_DEPARTMENT", ROLE_SOME_DEPARTMENT);
		   
		  
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

