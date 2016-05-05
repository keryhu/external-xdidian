package com.xdidian.keryhu.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 
* @ClassName: ChangeEmailDto
* @Description: TODO(用于更换email的时候，发送message的Dto)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月5日 下午7:39:19
 */
@Data
public class ChangeEmailDto implements Serializable{

	
	private static final long serialVersionUID = -5307199733290807126L;
	
	private String email;
	private String userId; // 从user mongo 数据库中拷贝来的user id
	
	public ChangeEmailDto(){
		this.email=null;
		this.userId=null;
	}
	

}
