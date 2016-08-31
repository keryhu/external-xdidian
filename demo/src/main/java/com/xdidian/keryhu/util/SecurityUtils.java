package com.xdidian.keryhu.util;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * 获取当前用户，关于spring security 的一些操作
 * 
 * @ClassName: SecurityUtils
 * @author kery Hu
 * @date 2015年10月3日 上午10:25:32
 */
public final class SecurityUtils {
	// 防止其他用户调用其私有对象
	private SecurityUtils() {
	}

	/**
	 * @Title: getCurrentLogin
	 * @Description: TODO(获取当前的登录用户)
	 * @return String 当前用户userId
	 * 
	 */

	public static String getCurrentLogin() {

		return Optional.ofNullable(getAuthentication())
			       .map(Authentication::getPrincipal)
			       .map(e -> {		    
		if (e instanceof UserDetails) {
			return ((UserDetails) e).getUsername();
		} 
			return e.toString();
		//如果为空，则返回null
	}).orElse(null);

	}

	

	/**
	 * @Title: isAuthenticated @Description: 查看当前用户是否已经登录
	 * TODO(检查当前用户是否是有权限的用户) 
	 */
	public static boolean isAuthenticated() {
               //如果是"ROLE_ANONYMOUS"，说明未登录，所以需要加上“！”，否定
		return	!getAuthentication().getAuthorities().stream()
                                    .filter(e->Optional.ofNullable(e).isPresent())
                //如果用户的authorities中只要有一个是 ROLE_ANONYMOUS，就说明用户没有登录
                                    .anyMatch(e->e.getAuthority().equals("ROLE_ANONYMOUS"));

	}
	
	
	

	/**
	 * @Title: isUserInRole @Description: TODO(当前用户是否具有指定的权限) @param 传入的权限参数字符串
	 * authority @param @return @param 传递authority role权限，返回是否具有权限 @return
	 * boolean 返回当前用户是否指定的权限 @throws
	 */
	public static boolean isUserInRole(String authority) {

		//如果用户未登录，那么它的权限也不是空，而是[ROLE_ANONYMOUS]
				return getAuthorities().contains(authority);
	}
	

	/**
	 * @Title: getCurrentUserIp @Description: TODO(获取当前用户的Ip地址,不管对方是否登录，不能查看本机的地址，只是远程用户的ip) @return String
	 * 用户的ip地址，String @throws
	 */
	public static String getCurrentUserIp() {
		return	Optional.ofNullable(getAuthentication())
		        .map(e->e.getDetails())
		        .map(e->{
		        	if(e instanceof OAuth2AuthenticationDetails){     		
		        		return ((OAuth2AuthenticationDetails)e).getRemoteAddress();
		        	}
		        	else if(e instanceof WebAuthenticationDetails){
		        	return	((WebAuthenticationDetails)e).getRemoteAddress();
		        	}
					return "";       	
		        }).orElse("");
	}

	
	
	
	
	/**
	 * Get Authentication
	 * @return authentication
	 */
	private static Authentication getAuthentication() {
		
	return	Optional.ofNullable(SecurityContextHolder.getContext())
			        .map(e->e.getAuthentication())
		            .orElse(null);		
	}

	/**
	 * @return 返回当前用户拥有的权限，用户的权限不可能为空，如果未登录，权限是：[ROLE_ANONYMOUS]
	 */
	public static Collection<String> getAuthorities() {
        //用户的权限不可能为空，如果未登录，权限是：[ROLE_ANONYMOUS] 
		return getAuthentication().getAuthorities().stream().map(e -> e.getAuthority()).collect(Collectors.toList());
	}
}
