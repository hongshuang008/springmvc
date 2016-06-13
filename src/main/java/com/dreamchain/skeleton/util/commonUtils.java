package com.dreamchain.skeleton.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class commonUtils {
	
	//定义一个数组，用于保存可上传的文件类型  
	public List fileTypes = new ArrayList();
	
	public commonUtils(){
		fileTypes.add("jpg");  
        fileTypes.add("jpeg");  
        fileTypes.add("bmp");  
        fileTypes.add("gif"); 
        fileTypes.add("png"); 
	}
	
	/**
     * 功能描述   保存图片
	 * @param newFileName 上传照片文件名
	 * @param extensionName 后缀名
	 * @param filedata 文件数据
	 * @param savePicUrl 保存位置
	 * @throws Exception 
	 */
    public void saveFile(String newFileName,String extensionName, MultipartFile filedata,String savePicUrl) throws Exception {
        // TODO Auto-generated method stub
    	if(!fileTypes.contains(extensionName)) {
    		throw new Exception("图片不符合规格");
    	}
        /* 构建文件目录 */
        File fileDir = new File(savePicUrl);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {
            FileOutputStream out = new FileOutputStream(savePicUrl + "\\"
                    + newFileName);
            // 写入文件
            out.write(filedata.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 
     * 功能描述：删除图片
     * 
     * @param oldPicName
     *           修改之前的文件名
     */
    public void deleteFile(String oldPicName,String savePicUrl) {
        /* 构建文件目录 */
        File fileDir = new File(savePicUrl+"/"+oldPicName);
        if (fileDir.exists()) {
            //把修改之前的图片删除 以免太多没用的图片占据空间
            fileDir.delete();
        }

    }
}
