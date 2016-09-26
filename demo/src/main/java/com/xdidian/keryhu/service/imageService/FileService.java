package com.xdidian.keryhu.service.imageService;


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

}
