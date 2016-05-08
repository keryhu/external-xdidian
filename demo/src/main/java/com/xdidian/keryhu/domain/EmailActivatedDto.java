package com.xdidian.keryhu.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime deadlineOfEmailActivated;   //用户email激活的截止时间
	
	public EmailActivatedDto(){
		this.email=null;
		this.emailActivatedCode=null;
		this.deadlineOfEmailActivated=null;
		
		
	}
	

}
