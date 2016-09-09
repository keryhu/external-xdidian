package com.xdidian.keryhu.domain.tokenConfirm;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 设定的，新的 注册时，密码重设时，个人资料修改时，公用的 消息之间传递的  dto
 * 
 *
 */


@Data
@NoArgsConstructor
public class CommonTokenDto implements Serializable{

	private static final long serialVersionUID = 3871960947959245021L;
	
    //可选，当编辑个人资料的时候，提交新的email或phone的时候，需要当前的userId
	private String userId;  
	
	private String token;   //      具体的验证码token
	
	
	private String account; //       token，对应的账号，email的值或phone的值
	
	private String resendToken; //   resend token
	
	private String resignupToken; // 可选，当注册时候，才会出现这个。
	
	//哪种应用在调用此方法，，注册／密码重设，还是 个人资料修改
	private ApplySituation applySituation;
	
	//可选，重设密码的时候，取得验证码的方法
	private RecoverMethod recoverMethod;   
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime expireDate;   //token有效期的截止时间
	

}
