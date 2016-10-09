package com.xdidian.keryhu.domain.company;

import java.io.Serializable;

import com.xdidian.keryhu.domain.CheckType;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 当新公司注册后，新地点的工作人员，
 *    审核通过了公司的注册资料，由company_info，发出的，消息，
 * 1 给user——account，通知他更新 user 的权限为 ROLE_COMPANY_ADMIN，更新companyId为新的。（id或email或phone，和companyId）
 * 2 通知邮件服务器，发送审核成功的通知，(email-必需，companyId）。
 * 3 通知手机平台，发送审核成功的通知，（phone--必需，companyId）
 * 4 通知websocket，给对应的userId，发送通知（userId-必需，companyId）
 * 
 *    审核失败了：
 * 1 通知邮件服务器，发送审核失败的通知，(email-必需，companyId）。
 * 3 通知手机平台，发送审核失败的通知，（phone--必需，companyId）
 * 4 通知websocket，给对应的userId，发送失败通知（userId-必需，companyId）
 *
 */

@Getter
@Setter
public class CheckCompanyDto implements Serializable{

	private static final long serialVersionUID = -7239478614580211697L;
	
	private String userId;    //userId
	private String email;
	private String phone;
	private String companyId;  //companyId
	private CheckType checkType;  // 审核的结果 类型 

}
