package com.xdidian.keryhu.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
	
	public boolean hasRole(Role role) {
        return (this.roles.contains(role));
    }
	 public void removeRole(Role role) {
		 this.roles=this.roles.stream()
				 //将符合条件的相同role去掉
		           .filter(e->!e.equals(role))
		           .collect(Collectors.toList());		
		}
	
}
