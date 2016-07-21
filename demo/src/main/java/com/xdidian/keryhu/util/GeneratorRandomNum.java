package com.xdidian.keryhu.util;

import java.util.Random;


/**
 * @Description : TODO(产生固定长度的数字验证码。)
 * @date : 2016年7月19日 下午8:41:26
 * @author : keryHu keryhu@hotmail.com
 */

public final class GeneratorRandomNum {

  public static String get(int x) {
    
    Random random = new Random();

    if (x < 0) {
      return null;
    }
    String result = "";

    for (int i = 0; i < x; i++) {
      result += random.nextInt(10);
    }
    return result;
  }

}
