package com.xdidian.keryhu.util;

import java.util.Arrays;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
* @ClassName: RestRandomCode
* @Description: TODO(不同服务器直接rest传输，如果需要增加随机验证码验证，这个就是)
* @author keryhu  keryhu@hotmail.com
* @date 2016年5月3日 下午2:39:52
 */

public final class RestRandomCode  {
	
	private RestRandomCode(){}
	
	private static final Logger logger=LoggerFactory.getLogger(RestRandomCode.class);
	
	private final static String code[]={"jsixm233ms899","sdmx34mi9xm23",
			"-0x=,.ws872kx","jxm23msx,923s","js99823mmx..sd9","jsixm23ls..mci",
			"msmdlx23,,xm323","sdmx23,id2e2s","msdkx932l9023s","sdmxiems,,soe2,",
			"sdmix,2,3isd2","msdk23,99203,sd"};
	
	
	
	
	public static String createRandomCode(){
		Random ra =new Random();
		
		String m=code[ra.nextInt(code.length)];
		logger.info("获取到的随机数组是： "+m);
		
		return m;
		
	}
	
	public static boolean containCode(String random){
		return Arrays.asList(code).contains(random);
	}
	
}
