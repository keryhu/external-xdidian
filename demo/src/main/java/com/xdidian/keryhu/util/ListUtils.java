package com.xdidian.keryhu.util;

import java.util.HashSet;
import java.util.List;

// list util 
public final class ListUtils {
	
	// 判断list 对象中是否含有重复元素的方法。
	public static <T> boolean containsUnique(List<T> list){
		return list.stream().allMatch(new HashSet<>()::add);
	}

}
