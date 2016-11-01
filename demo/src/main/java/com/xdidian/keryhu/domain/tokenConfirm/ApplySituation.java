package com.xdidian.keryhu.domain.tokenConfirm;


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
	

}
