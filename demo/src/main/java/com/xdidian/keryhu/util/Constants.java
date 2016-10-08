package com.xdidian.keryhu.util;

/**
 * 
* @ClassName: Constants
* @Description: TODO(设置整个系统的常量 )
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月4日 上午9:18:07
 */
public  final class Constants {
	
	private Constants(){
		//不允许实例化
	}
	
	public static final String  READ_AND_WRITE_RESOURCE_ID="ReadAndWriteResource";   //可读写的resourceID
    
    //如果email激活码过期，则前台需要返回注册页面
    public static final String EMAIL_ACTIVATE_EXPIRED="emailActivateExpired";
    
    //定义一个使用新地点平台role，排序
    public static final String ROLE_HIERARCHY="ROLE_XDIDIAN_ADMIN>ROLE_XDIDIAN_SERVICE ROLE_XDIDIAN_SERVICE>ROLE_COMPANY_ADMIN ROLE_COMPANY_ADMIN>ROLE_SOME_DEPARTMENT  ROLE_SOME_DEPARTMENT>ROLE_DEFAULT";
	
    //定义用户名字，修改的间隔时间，至少60天，也就是限制了用户不能随意的修改名字。
    public static final int INTERVAL_DAYS_OF_NAME_MODIFICATION=60;
    

    //表示新的公司注册成功，发送消息出去的暗号。
    public static final String NEW_COMPANY="NEW_COMPANY";

}
