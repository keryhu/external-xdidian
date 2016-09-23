package com.xdidian.keryhu.util;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xdidian.keryhu.domain.StringType;

/**
* @ClassName: StringValidate
* @Description: TODO(对于系统中的String，基于表达式的判断，判断其符合某种标准，如password，email等)
* @author keryhu  keryhu@hotmail.com
* @date 2016年3月15日 下午3:28:38
*
 */
public final class StringValidate {
	//防止其他用户调用其私有对象
	private StringValidate(){}
	
	
	/**
	 * 验证传入的参数，是否是email格式
	 * @param email
	 * @return 如果是email，则返回true
	 */
	public static boolean isEmail (String email){
		
	return	Optional.ofNullable(email).map(e->{
			String username = "^\\w[-.\\w]*\\@"; // 邮箱的用户名部分和@
			String hostname = "[-a-zA-Z0-9]+(\\.[-a-zA-Z0-9]+)*\\."; // 注解名，例如163，sina等
			String domain = "(com|cn|net|edu|info|xyz|wang|org|top|ren|club|pub|rocks|band|market|software|social|lawyer|engineer|me|tv|cc|co|biz|mobi|name|asia)$";
			String regex = username + hostname + domain;
			// Pattern.CASE_INSENSITIVE意思是不区分大小写匹配
			Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
			// m.matches() 表示是否匹配，返回boolean
			return p.matcher(e).matches();
			
			//如果e是null，那么直接返回false
		}).orElse(false);
		
	}
	
	/**
	 * 验证传入的参数，是否是phone格式,正确格式是：13，14，15，17，18开头的11位数字
	 * @param phone
	 * @return 如果是phone，则返回true
	 */
	public static boolean isPhone (String phone){
		
		return Optional.ofNullable(phone).map(e->{		
			//目前的手机号均为13，14，15，17，18开头的11位数字
			//严格格式为11位数字
					String regex="^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
					Pattern p=Pattern.compile(regex);
					Matcher m=p.matcher(e);	
					return m.matches();  //m.matches() 表示是否匹配，返回boolean
			
			//如果e是null，那么直接返回false
		}).orElse(false);
	}
	
	/**
	 * 验证传入的参数，是否是password格式,正确格式是：6-20位字符组成，必须包含数字，字母，特殊字符，2种以上随意组合，特殊字符为-`=;',.~!@#$%^&*()_+\{}:<>?
	 * @param password
	 * @return 如果是password，则返回true
	 */
	public static boolean isPassword (String password){
		return Optional.ofNullable(password).map(e->{		
			// 表达式的意思是：6-20位字符组成，必须包含数字，字母，特殊字符，2种以上随意组合，特殊字符为-`=;',.~!@#$%^&*()_+\{}:<>?
						// (?=.*\\d)表示包含数字
						// (?=.*[A-Za-z]) 表示包含字母
						// (?=.*[-`=;',.~!@#$%^&*()_+\\{}:<>?]) 表示包含特殊字符
						// (?=.*\\d)(?=.*[A-Za-z]).{6,20} 表示6-20个字符，里面必须含有数字和字母
						// 由4个组合在一起，分别为： 数字＋字母 or 数字＋字符 or 字母＋字符 or 数字＋字母＋字符
						String regex = "^(?=.*\\d)(?=.*[A-Za-z]).{6,20}|(?=.*\\d)(?=.*[-`=;',.~!@#$%^&*()_+\\{}:<>?]).{6,20}|(?=.*[A-Za-z])(?=.*[-`=;',.~!@#$%^&*()_+\\{}:<>?]).{6,20}|(?=.*\\d)(?=.*[A-Za-z])(?=.*[-`=;',.~!@#$%^&*()_+\\{}:<>?]).{6,20}$";
						Pattern pattern = Pattern.compile(regex);
						Matcher m = pattern.matcher(e);
						return m.matches(); // m.matches() 表示是否匹配，返回boolean
			
			//如果e是null，那么直接返回false
		}).orElse(false);
	}
	
	/**
	 * 验证传入的参数，是否是公司名字格式,正确格式是：2-2-个中文或字母组成，可以包含括号
	 * @param companyName
	 * @return 如果是companyName，则返回true
	 */
	public static boolean isCompanyName (String companyName){
	return	Optional.ofNullable(companyName).map(e->{
			String regex="^([-a-zA-Z\\u4e00-\\u9fa5\\(\\)（）_\\.\\s]{4,40})$";
			Pattern p=Pattern.compile(regex);
			Matcher m=p.matcher(e);	
			return m.matches();  //m.matches() 表示是否匹配，返回boolean
			//如果e是null，那么直接返回false
					}).orElse(false);
	}
	
	
	/**
	 * 验证传入的参数，是否是人名,正确格式是：2-2-个中文或字母组成，不可以包含括号
	 * @param peopleName
	 * @return 如果是peopleName，则返回true
	 */
	public static boolean isPeopleName (String peopleName){
		return Optional.ofNullable(peopleName).map(e->{
			String regex="^([\\u4e00-\\u9fa5]{2,20}|[a-zA-Z\\.\\s]{2,20})$";
			Pattern p=Pattern.compile(regex);
			Matcher m=p.matcher(e);	
			return m.matches();  //m.matches() 表示是否匹配，返回boolean
			//如果e是null，那么直接返回false
		}).orElse(false);
	}
	
	
	/**
	 * 验证传入的参数，是否是uuid,
	 * @param uuid
	 * @return 如果是uuid，则返回true
	 */
	public static boolean isUuid (String uuid){
		return Optional.ofNullable(uuid).map(e->{
			String regex="^[0-9a-f]{8}(-[0-9a-f]{4}){3}-[0-9a-f]{12}$";
			Pattern p=Pattern.compile(regex);
			Matcher m=p.matcher(e);	
			return m.matches();  //m.matches() 表示是否匹配，返回boolean
			//如果e是null，那么直接返回false
					}).orElse(false);
	}
	
	
	/**
	 * 
	* @Title: checkType
	* @Description: TODO(检测字符串是属于StringType的哪种类型，如果都不是，就返回null)
	* @param @param s
	* @param @return    设定文件
	* @return StringType    返回类型
	* @throws
	 */
	public static StringType checkType(String s){
		
		if(StringValidate.isEmail(s)){
			return StringType.EMAIL;
		} else if(StringValidate.isPhone(s)){
			return StringType.PHONE;
		} else if (StringValidate.isPassword(s)){
			return StringType.PASSWORD;
		} else if(StringValidate.isCompanyName(s)){
			return StringType.COMPANY_NAME;
		} else if(StringValidate.isPeopleName(s)){
			return StringType.PEOPLE_NAME;
		} else if(StringValidate.isUuid(s)){
			return StringType.UUID;
		}
		
		   return StringType.ALLNOT;
	}
		
}

