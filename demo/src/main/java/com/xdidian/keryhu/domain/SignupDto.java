package com.xdidian.keryhu.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
* @ClassName: PropertyRegisterDto
* @Description: TODO(此类是一个dto，用来将用户注册的数据远程提交给useraccount service，然后再转为user数据保存数据库
* 只单纯的提交email，phone和password，其他信息都没有)
* @author keryhu  keryhu@hotmail.com
* @date 2016年4月21日 下午4:51:51
 */
@Data
@AllArgsConstructor
public class SignupDto implements Serializable {

	private static final long serialVersionUID = 2857339497829739963L;
	
	private String email;
	private String phone;
	private String password;
	
	
	public SignupDto() {
		// TODO Auto-generated constructor stub
		this.email=null;
		this.phone=null;
		this.password=null;
		
		
	}

}
