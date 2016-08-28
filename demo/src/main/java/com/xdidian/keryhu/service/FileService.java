package com.xdidian.keryhu.service;

import java.io.File;

import javax.activation.MimetypesFileTypeMap;

public class FileService {  /**
	   * 
	  * @Title: isImage
	  * @Description: TODO(支持文件格式“jpeg,jpg”，“png”，“tif”，“tiff”,"bmp","gif"
	  * 　主流的图片格式都已经支持了，且验证过了。)
	  * @param @param file
	  * @param @return    设定文件
	  * @return boolean    返回类型
	  * @throws
	   */
	  
	  
	  public boolean isImage(File file){
	    String mimetype=new MimetypesFileTypeMap().getContentType(file);
	    String type=mimetype.split("/")[0];
	    if(type.equals("image")){
	      return true;
	    }
	    else{
	      return false;
	    }
	  }
	  
	
	

}
