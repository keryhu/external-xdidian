package com.xdidian.keryhu.domain.message;

/**
 * 新地点的message，例如（未审核的公司，新同事加入，等）是一个主题，这个会根据业务不断跳转增加
 * 
 * @author hushuming
 *
 */

public enum Subject {

	NEW_COMPANY, // 这个是通知，新地点的客服人员和管理人员的message
	APPROVE_NEW_COMPANY, // 批准公司审核--这个主要是通知，申请人 的message
	REJECT_NEW_COMPANY, // 公司审核的材料被驳回--这个主要是通知，申请人 的message
	JOIN_COMPANY, // 会员申请加入公司完成，这个是通知公司的管理员
	APPROVE_JOIN_COMPANY, // 会员申请加入公司，公司管理员批准申请 的主题，这个是通知，申请人
	REJECT_JOIN_COMPANY; // 会员申请加入公司，公司管理员驳回他的申请 的主题，这个是通知，申请人

	public String getName() {
		Subject subject = this;
		String result = "";
		switch (subject) {
		case NEW_COMPANY:
			result = "待审核公司";
			break;
		case APPROVE_NEW_COMPANY:
			result = "公司注册成功";
			break;
		case REJECT_NEW_COMPANY:
			result = "公司注册失败";
			break;
		case JOIN_COMPANY:
			result = "待加入员工";
			break;
		case APPROVE_JOIN_COMPANY:
			result = "申请加入公司成功";
			break;
		case REJECT_JOIN_COMPANY:
			result = "申请加入公司失败";
			break;
		default:
			break;
		}
		return result;
	}
}
