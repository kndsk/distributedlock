package myproxy;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;

public class MyClassLoader extends ClassLoader{
//生成的代理类加载路径
	private File dir;
	
	private String proxyClassPackage;

	public File getDir() {
		return dir;
	}

	public void setDir(File dir) {
		this.dir = dir;
	}

	public String getProxyClassPackage() {
		return proxyClassPackage;
	}

	public void setProxyClassPackage(String proxyClassPackage) {
		this.proxyClassPackage = proxyClassPackage;
	}

	public MyClassLoader(String path, String proxyClassPackage) {
		this.dir = new File(path);
		this.proxyClassPackage = proxyClassPackage;
	}
	
	 protected Class<?> findClass(String name) throws ClassNotFoundException {
		 
		 if(dir!=null){
			File  classFile =  new File(dir,name+".class"); 
			if(classFile.exists()){
				try{
				byte[]	classBytes = FileCopyUtils.copyToByteArray(classFile);
				
			    return defineClass(proxyClassPackage+"."+name,classBytes,0,classBytes.length);
				}catch(IOException e){
					e.printStackTrace();
				}
			}
			
			 
		 }
		 
	        return super.findClass(name);
	    }


	 
}
