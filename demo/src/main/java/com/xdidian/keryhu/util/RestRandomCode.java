package com.xdidian.keryhu.util;

import java.util.ArrayList;
import java.util.List;
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
	
	private RestRandomCode(){};
	
	
	private static final Logger logger=LoggerFactory.getLogger(RestRandomCode.class);
		
	private static final List<String> code=new ArrayList<String>();
	
	/**
	 * 初始化40个由12个字符串组成的数组。
	 */
    static {
    	for (int i=0;i<40;i++){
    		code.add(generateRandom(12));
    	}
		
	}
	
	
    /**
     * 
    * @Title: createCode
    * @Description: TODO(从随机数的池子里面，随机挑选一个字符串)
    * @param @return    设定文件
    * @return String    返回类型
    * @throws
     */
	public static String createCode(){
		Random ra =new Random();
		String m=code.get(ra.nextInt(code.size()));
		logger.info("获取到的随机数组是： "+m);
		return m;
		
	}
	
	/**
	 * 
	* @Title: containCode
	* @Description: TODO(看看此字符串是否存在于池子里)
	* @param @param random
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean containCode(String random){
		return code.contains(random);
	}
	
	/**
	 * 
	* @Title: generateRandom
	* @Description: TODO(生成固定长度的随机字符串)
	* @param @param length
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
	public static String generateRandom(int length){
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ<>?:{}[]#$@%^&*()"); 
		StringBuffer sb = new StringBuffer();   
        Random random = new Random();   
        int range = buffer.length();   
        for (int i = 0; i < length; i ++) {   
            sb.append(buffer.charAt(random.nextInt(range)));   
        }   
        return sb.toString();   
	}
	
}
