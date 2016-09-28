package com.xdidian.keryhu.service.imageService;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.core.io.FileSystemResource;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

public class FileService {
	/**
	 * 
	 * @Title: isImage @Description:
	 * TODO(支持文件格式“jpeg,jpg”，“png”，“tif”，“tiff”,"bmp","gif"
	 * 主流的图片格式都已经支持了，且验证过了。) @param @param file @param @return 设定文件 @return
	 * boolean 返回类型 @throws
	 */

	public boolean isImage(MultipartFile file) {
		Assert.isTrue(!(file == null || file.isEmpty()), "上传文件不能为空");
		String type = file.getContentType().split("/")[0];
		return type.equals("image");
	}
	
	// 将filePaht 图片，转为png base64 string
	public  byte[] filePathToPngByte(String path) {
	    FileSystemResource resource = new FileSystemResource(path);
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    if (resource.exists()) {
	      BufferedImage img = null;
	      try {
	        img = ImageIO.read(resource.getFile());
	        ImageIO.write(img, "png", bos);       
	        return bos.toByteArray();
	      } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	      }
	    }
	    return null;
	  }
	
	// 将filePaht 图片，转为 原来图片格式的 base64 string（即原来是 jpeg，转后还是jpeg，原来是png，转后还是png）
		public  byte[] filePathToOriginalByte(String path) {
		    FileSystemResource resource = new FileSystemResource(path);
		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
		   
		    int index=path.indexOf(".");
	        String type=path.substring(index+1,path.length());
		   
		    if (resource.exists()) {
		      BufferedImage img = null;
		      try {
		        img = ImageIO.read(resource.getFile());
		        ImageIO.write(img, type, bos);       
		        return bos.toByteArray();
		      } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		      }
		    }
		    return null;
		  }
		

}
