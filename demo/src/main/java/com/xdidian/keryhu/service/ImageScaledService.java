package com.xdidian.keryhu.service;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: ImageScaledService
 * @Description: TODO(image 缩小或扩大为 指定 宽度＊高度 像素，高质量和速度的 方法。)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年8月30日 下午9:42:42
 */


@Component("imageScaledService")
public class ImageScaledService {

 

  // 追求图像质量的设置方法。
  public BufferedImage resize(BufferedImage image, int width, int height) {
    
    int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
    BufferedImage resizedImage = new BufferedImage(width, height, type);
    Graphics2D g = resizedImage.createGraphics();
    g.setComposite(AlphaComposite.Src);
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.drawImage(image, 0, 0, width, height, null);
    g.dispose();
    return resizedImage;
  }

}

