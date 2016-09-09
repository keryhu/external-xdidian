package com.xdidian.keryhu.domain.tokenConfirm;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * 当用户的个人资料修改的时候，例如修改邮箱或者手机号的时候，修改成功后，
 * 从account－activated 服务器，发送包含userId和account的message出去，
 * 通知userService 更新此userId下的account为最新的 email或者phone，同时设置
 * 他们的status为true
 *
 */


@Data
@NoArgsConstructor
public class AccountEditDto implements Serializable {

	private static final long serialVersionUID = -544557594918922800L;
	
	private String userId; //user的 id
	
	private String account; //对应的账号，email的值或phone的值
	

}
