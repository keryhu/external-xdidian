package com.xdidian.keryhu.domain;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author hushuming
 * 当密码重设完成后，从密码重设服务器发送出去的 消息主体，这个就是主体的内容class
 * 包含account和hashpassword
 *
 */

@Getter
@Setter
public class NewPasswordDto implements Serializable{


	private static final long serialVersionUID = 8322729059156722745L;
	
	private String account;
	private String hashPassword;

}
