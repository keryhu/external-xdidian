package com.xdidian.keryhu.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 
* @ClassName: EmailActivatedDto
* @Description: TODO(用于邮箱激活，发送message的dto，)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月5日 下午7:34:55
 */
@Data
public class EmailActivatedDto implements Serializable {
 
	private static final long serialVersionUID = -1178102023977336785L;
	
	private String email;
	private String emailActivatedCode;   //邮箱激活的验证码
	
	public EmailActivatedDto(){
		this.email=null;
		this.emailActivatedCode=null;
	}
	

}
