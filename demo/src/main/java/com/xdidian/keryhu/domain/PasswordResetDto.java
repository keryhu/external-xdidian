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
* @ClassName: PasswordResetDto
* @Description: TODO(忘记密码，重设密码过程中需要用到的，相互之间传递message的dto)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月17日 下午1:10:58
 */
@Data
public class PasswordResetDto implements Serializable  {

	
	private static final long serialVersionUID = 2456549940699014529L;
	
	private String email;
	private String passwordToken ;   // 验证重设密码url中的token
	private String resendToken ;     //“重新发送邮件”url中的token
	private String userId;           // 需要被重设密码的user的id
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime expireDate;   //重设密码链接有效期的截止时间

}
