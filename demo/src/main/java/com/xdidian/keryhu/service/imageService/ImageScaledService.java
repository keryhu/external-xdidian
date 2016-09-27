package com.xdidian.keryhu.service.imageService;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @ClassName: ImageScaledService
 * @Description: TODO(image 缩小或扩大为 指定 宽度＊高度 像素，高质量和速度的 方法。)
 * @author keryhu keryhu@hotmail.com
 * @date 2016年8月30日 下午9:42:42
 */

public class ImageScaledService {

	private FileService fileService = new FileService();

	// 追求图像质量的设置方法。
	public BufferedImage resize(BufferedImage image, int width, int height) {

		int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image.getType();
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.setComposite(AlphaComposite.Src);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		return resizedImage;
	}

	// resize 图片，到指定的 宽度和高度，并且保存图片到指定 文件位置。默认单个文件不能超过1Mb
	public void resizeImageAndSave(MultipartFile file, int width, int height, long minResizeSize, 
			long maxSize,String targetSavePath)throws IOException {

		// 首先验证 上传来的文件是否是 image文件
		Assert.isTrue(fileService.isImage(file), "上传的图片，必需是image格式");
		String m = "上传的文件不能超过 " + maxSize / 1024 + " kb";
		Assert.isTrue(file.getSize() < maxSize, m);

		BufferedImage inputImage = ImageIO.read(file.getInputStream());
		;// resize后的图片

		BufferedImage newImage = inputImage; // resize后的图片

		// 超过了默认的 图片的像素
		final Boolean exceedDefaultPixel = inputImage.getHeight() > height || inputImage.getWidth() > width;

		// ,如果根据文件大小，超了 最小的 resieze 文件大小，默认单位是 kb，且超了默认的像素，那么resize
		if (file.getSize() >= minResizeSize && exceedDefaultPixel) {
			newImage = resize(inputImage, width, height);
		}

		// save image
		saveImage(newImage, targetSavePath);

	}

	/**
	 * 将图片文件保存到 指定的目的地。 目的地一般是这样的： /a/bc/abc.png 就是abc.png 图片保存在 文件夹 /a/bc/ 下
	 * 
	 * @param file
	 * @param destFile
	 * @throws IOException
	 */

	public void saveImage(BufferedImage file, String destFile) throws IOException {
		// TODO Auto-generated method stub
		// writes to output file
		ImageIO.write(file, "png", new File(destFile));
	}

}
