package com.xdidian.keryhu.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

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
	private String token;   //邮箱激活的验证码
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime expireDate;   //用户email激活的截止时间
	
	/**
	 * 
	* <p>Title: </p>
	* <p>Description: 初始化的时候，设置token的值,并将email和过期时间，传递给Dto对象</p>
	 */
	public EmailActivatedDto(String email,LocalDateTime expireDate){
		this.email=email;
		this.token=UUID.randomUUID().toString();
		this.expireDate=expireDate;
			
	}
}
