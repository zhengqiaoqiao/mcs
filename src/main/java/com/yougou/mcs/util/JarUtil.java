package com.yougou.mcs.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: UrlClassLoaderUtil</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2017年4月24日
 */
public class JarUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(JarUtil.class);
	public static List<Class> getClassList(String jarFile) throws ClassNotFoundException, IOException{
		LOGGER.info("开始解析Jar文件！");
		List<Class> result = new ArrayList<Class>();
		URL url = new URL("file:\\" + jarFile);
		URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {url});
		//通过jarFile和JarEntry得到所有的类 
        JarFile jar = new JarFile(jarFile); 
        //返回zip文件条目的枚举 
        Enumeration<JarEntry> enumFiles = jar.entries(); 
        JarEntry entry; 
        //测试此枚举是否包含更多的元素 
        while(enumFiles.hasMoreElements()){ 
            entry = enumFiles.nextElement(); 
            if(entry.getName().indexOf("META-INF") < 0){ 
                String classFullName = entry.getName(); 
                if(classFullName.endsWith(".class")){ 
                	 //去掉后缀.class 
                    String className = classFullName.substring(0,classFullName.length()-6).replace("/", "."); 
                    if(className.matches("^([a-z0-9]+[.])+[a-zA-Z0-9]+(Api|Service)+([a-zA-Z0-9])*$")){
                    	Class<?> myclass = classLoader.loadClass(className); 
                        result.add(myclass);     
                    }
                }
            } 
        }
		return result; 
    }  
	
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		String url = "E:\\cds-api-0.0.1-SNAPSHOT.jar";
		File f = new File(url);
		System.out.println(f.exists());
		List<Class> list = getClassList(url);
		for(Class c : list){
			System.out.println(c.getName());
		}
	}
}
