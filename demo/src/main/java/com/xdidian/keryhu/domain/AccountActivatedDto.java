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
* @Description: TODO(用于邮箱激活或手机激活，从注册后台发送message出去的dto包含了：
*  id（email或phone），token 和 过期时间，)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月5日 下午7:34:55
 */
@Data
public class AccountActivatedDto implements Serializable {
 
	private static final long serialVersionUID = -1178102023977336785L;
	
	private String id;    //id 只能是email或phone格式，如果是email就是发送的email激活，否则是phone激活
	
	private String token;   //id对应的激活码
	
	private String resendToken;  // “重新发送激活邮件”Url中的token
	
	private String resignupToken;  // “重新注册”Url中的token
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime expireDate;   //id对应的激活的截止时间
	
	
}
