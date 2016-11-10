package com.xdidian.keryhu.domain.message;

import java.io.Serializable;


import lombok.Data;


/**
 * 
 * @author hushuming
 * 其他组件和message组件，相互message沟通的 dto
 * 包含了subject，哪个主题下
 * 
 * 一个
 *
 */

@Data
public class MessageCommunicateDto implements Serializable {

	private static final long serialVersionUID = -6079557186321689640L;
	
	private Subject subject;
	 // userId,不是必填项，因为如果ReadGroup，是xdidian，那么userId可以为null
	 //  否则必需填写userId
	private String userId;        
	private ReadGroup readGroup;
	private Operate operate;       
	

}
