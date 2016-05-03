package com.xdidian.keryhu.util;

import java.util.Arrays;
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
		
	private static final List<String> code=Arrays.asList("8bJUz:hKh>Ng","Ylb(]VccGRw@",
			"IhN5rKT3*BfV","GZ?[0hivPkGu","9ofWSe9v}9*N","UdjJDW$@WECT","w7wJD0q7VhNQ",
			"$dZeW#)qxI3C","$CvGxJrOF$KA","^CB1jS7{A9@i","HF@YT4C]H{[6","xl{9?JdOVJkX",
			"Nru0VL3e?]Xz","i8G:Zxg@)NFY","HJRx$#yQaayu","J)#c$qv{rOD@","8&yM:?vg(Gp9",
			"MP#GyD%kP}iv","ou0G0&SoRmyY","&o^Q{due6QFz","l2EAb4ajofwf","Z4(P#Liq#D^^",
			"I5n?PFCjyo70","t0SBae{0$Uw{","5CLTJ{:@a%Uw","ZHP%fdKgO4>^","{jqXOGJg)5HQ",
			"?:fOLF[{9Dyu","zwbN[ypzFTLF",">qMs5okEsMwg","CfMDrm(LsU1q","qgb*HRjKL1]9",
			"el@GiU*H}B}l","qsYYeo@FByM<","IDPhlDtu0IeE","{*6V#NU}3cG[","xy]Hgcm1?StU",
			"UtSd4WepVp5E","VJ@DkeOItn:$","bD>NYrtj(tPr");

	
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
	
	
}
