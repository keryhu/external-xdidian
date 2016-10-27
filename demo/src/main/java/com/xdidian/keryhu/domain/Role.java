package com.xdidian.keryhu.domain;


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
	  ROLE_XDIDIAN_ADMIN,         // 新地点公司的最高的管理员
	  ROLE_XDIDIAN_SERVICE,       // 新地点公司的客服人员权限
	  ROLE_SOME_DEPARTMENT    ;   //公司的拥有读取某些部门数据的roles，注意针对的是客户，所在的公司，用户能过读取某些部门权限
	
	
	  
  
}

