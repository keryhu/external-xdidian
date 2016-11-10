package com.xdidian.keryhu.domain.message;


/**
 * 是一个加减操作，就是add，minus（减少），
     目的： 让message统计每一个主题的计数，增1，或减1，当有新的主题出现，该主题就+1，如果该主题有一个被读
 * @author hushuming
 *  这个enum也可以服务于其他组件，暂时还未知
 */
public enum Operate {
	
	ADD,       //   增加1
	MINUS;     //   减少1

}
