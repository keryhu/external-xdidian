package com.xdidian.keryhu.util;

public class T {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String m=RestRandomCode.createCode();
		
		System.out.println("产生的随机数是 ： "+m+" , 是否含有此 m ： "+RestRandomCode.containCode(m));

	}

}
