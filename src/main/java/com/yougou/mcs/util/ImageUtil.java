package com.yougou.mcs.util;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import javax.imageio.ImageIO;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.ExifImageDirectory;



/**
 * <p>Title: ImageUtils</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年7月15日
 */
public class ImageUtil {
	
	/**
	 * 强制压缩/放大图片到固定的大小
	 * @param w int 新宽度
	 * @param h int 新高度
	 */
	public static InputStream resize(InputStream stream, int w, int h) throws IOException {
		BufferedImage img = ImageIO.read(stream);      // 构造Image对象
		int width = img.getWidth();    // 得到源图宽
		int height = img.getHeight();  // 得到源图长
		if (width / height > w / h) {
			h = (int) (height * w / width);
		} else {
			w = (int) (width * h / height);
		}
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB); 
		image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
		ByteArrayOutputStream os = new ByteArrayOutputStream();  
		ImageIO.write(image, "jpg", os);  
		InputStream is = new ByteArrayInputStream(os.toByteArray());  
		return is;
	}
	
	/** 
	 * 图片翻转时，计算图片翻转到正常显示需旋转角度  
	 */  
	public static int getRotateAngleForPhoto(InputStream stream){   
	    int angel = 0;  
	    Metadata metadata;  
	    try{  
	        metadata = ImageMetadataReader.readMetadata(stream);  
	        Collection<ExifDirectoryBase> directorys = metadata.getDirectoriesOfType(ExifDirectoryBase.class); 
	        for(ExifDirectoryBase directory : directorys){
	        	if(directory.containsTag(ExifDirectoryBase.TAG_ORIENTATION)){   
		            // Exif信息中方向　　  
		            int orientation = directory.getInt(ExifDirectoryBase.TAG_ORIENTATION);   
		            // 原图片的方向信息  
		            if(6 == orientation ){  
		                //6旋转90  
		                angel = 90;  
		            }else if( 3 == orientation){  
		               //3旋转180  
		                angel = 180;  
		            }else if( 8 == orientation){  
		               //8旋转90  
		                angel = 270;  
		            } 
		            break;
		        }  
	        }
	        
	    } catch (ImageProcessingException e) {
			e.printStackTrace();
		} catch (MetadataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	    return angel;  
	}  
	
	/** 
	* 计算旋转参数 
	*/  
	public static Rectangle calcRotatedSize(Rectangle src,int angel){  
	    // if angel is greater than 90 degree,we need to do some conversion.  
	    if(angel > 90){  
	        if(angel / 9%2 ==1){  
	            int temp = src.height;  
	            src.height = src.width;  
	            src.width = temp;  
	        }  
	        angel = angel % 90;  
	    }  
	    double r = Math.sqrt(src.height * src.height + src.width * src.width ) / 2 ;  
	    double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;  
	    double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;    
	    double angel_dalta_width = Math.atan((double) src.height / src.width);    
	    double angel_dalta_height = Math.atan((double) src.width / src.height);    
	  
	    int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha    
	            - angel_dalta_width));    
	    int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha    
	            - angel_dalta_height));    
	    int des_width = src.width + len_dalta_width * 2;    
	    int des_height = src.height + len_dalta_height * 2;    
	    return new java.awt.Rectangle(new Dimension(des_width, des_height));    
	}  
	
	public static void main(String[] args) throws IOException{
		List<File> fileList = FileUtil.find("C:\\Users\\zheng.qq\\Desktop\\新建文件夹 (2)\\1");
		for(File file : fileList){
			String name = file.getName();
			File file2 = new File("C:\\Users\\zheng.qq\\Desktop\\新建文件夹 (2)\\2", name);//可以是任何图片格式.jpg,.png等
            FileOutputStream fos=new FileOutputStream(file2);
			FileInputStream fis = new FileInputStream(file);
			InputStream in = resize(fis, 200, 200);
			
		    byte[] b = new byte[1024];
            int nRead = 0;
            while ((nRead = in.read(b)) != -1) {
            	fos.write(b, 0, nRead);
            }
            fos.flush();
            fos.close();
            in.close();
		}
	}
	
	
}
