package com.xdidian.keryhu.domain;

import java.io.Serializable;
import java.util.List;


import lombok.Data;


/**
 * 这个是提供给OAuth2 service 验证的DTO，
 * @author hushuming
 *
 */

@Data
public class AuthUser implements Serializable {
	
	
	private static final long serialVersionUID = -4128086432158731873L;
	
	private String id;
	private String password;
	private List<Role> roles;
	
}
