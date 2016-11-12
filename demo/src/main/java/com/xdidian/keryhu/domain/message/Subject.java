package com.xdidian.keryhu.domain.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 新地点的message，例如（未审核的公司，新同事加入，等）是一个主题，这个会根据业务不断跳转增加
 * 
 * @author hushuming
 *
 */

public enum Subject {

	NEW_COMPANY("待审核公司"), // 这个是通知，新地点的客服人员和管理人员的message
	APPROVE_NEW_COMPANY("公司注册成功"), // 批准公司审核--这个主要是通知，申请人 的message
	REJECT_NEW_COMPANY("公司注册失败"), // 公司审核的材料被驳回--这个主要是通知，申请人 的message
	JOIN_COMPANY("待加入员工"), // 会员申请加入公司完成，这个是通知公司的管理员
	APPROVE_JOIN_COMPANY("申请加入公司成功"), // 会员申请加入公司，公司管理员批准申请 的主题，这个是通知，申请人
	REJECT_JOIN_COMPANY("申请加入公司失败"); // 会员申请加入公司，公司管理员驳回他的申请 的主题，这个是通知，申请人

	private final String value;

	Subject(String value) {
		this.value = value;
	}

	@JsonValue
	public String toValue() {
		return this.value;
	}

	@JsonCreator
	public static Subject forValue(String value) {
		for (Subject subject : Subject.values()) {
			if (value.equals(subject.value))
				return subject;
		}
		String err = new StringBuffer("您提供的值: ").append(value).append(" 不对！").toString();

		throw new IllegalArgumentException(err);
	}
}
