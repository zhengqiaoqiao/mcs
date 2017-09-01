package com.yougou.mcs.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: FileUtil</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年11月22日
 */
public class FileUtil {

	/**
	 * 文件重命名 
	 * @param path 文件目录 
	 * @param oldname  原来的文件名 
	 * @param newname 新文件名 
	 */
    public static void renameFile(String path, String oldname, String newname){ 
        if(!oldname.equals(newname)){//新的文件名和以前文件名不同时,才有必要进行重命名 
            File oldfile=new File(path + File.separator + oldname); 
            File newfile=new File(path + File.separator + newname); 
            if(!oldfile.exists()){
                return;//重命名文件不存在
            }
            if(newfile.exists())//若在该目录下已经有一个文件和新文件名相同，则不允许重命名 
                System.out.println(newname+"已经存在！"); 
            else{ 
                oldfile.renameTo(newfile); 
            } 
        }else{
            System.out.println("新文件名和旧文件名相同...");
        }
    }
	/**
	 * 递归方法
	 * @param path 文件路径
	 */
	public static List<File> find(String path){
		List<File> fileList = new ArrayList<File>();
		File file=new File(path);
		if(!file.exists()){
			return fileList;
		}
		if(file.isDirectory()){
			File[] files = file.listFiles();
			//如果文件数组为null则返回
	        if (files != null){
	        	for (int i = 0; i < files.length; i++) { 
	                if (files[i].isDirectory()) { 
	                	//判断是不是文件夹，如果是文件夹则继续向下查找文件
	                	find(files[i].getAbsolutePath()); 
	                } else { 
	                    fileList.add(files[i]);
	                } 
	            } 
	        }
		}else{
			fileList.add(file);
		}
        return fileList;
	}
	/**
	 * 获取真实的文件路径（windows linux兼容）
	 * @param path
	 * @param os  为空时，根据运行机子自行判断
	 * @return
	 */
	public static String getRealFilePath(String path, String os) {
		if(os==null || os.equals("")){
			path = path.replace("/", File.separator).replace("\\", File.separator);  
		}else if(os.equals("windows")){
			path = path.replace("/", "\\");  
		}else if(os.equals("linux")){
			path = path.replace("\\", "/");  
		}
		return path;
    }  
	
	
}
