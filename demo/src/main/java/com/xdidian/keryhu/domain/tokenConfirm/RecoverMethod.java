package com.xdidian.keryhu.domain.tokenConfirm;


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
	
}
