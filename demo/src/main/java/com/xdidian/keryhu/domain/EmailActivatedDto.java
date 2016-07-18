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
* @Description: TODO(用于邮箱激活，从注册后台发送message出去的dto包含了：email，token 和 过期时间，)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月5日 下午7:34:55
 */
@Data
public class EmailActivatedDto implements Serializable {
 
	private static final long serialVersionUID = -1178102023977336785L;
	
	private String email;
	private String emailToken;   //邮箱激活的验证码
	
	private String resendToken;  // “重新发送激活邮件”Url中的token
	
	private String resignupToken;  // “重新注册”Url中的token
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime expireDate;   //用户email激活的截止时间
	
	
}
