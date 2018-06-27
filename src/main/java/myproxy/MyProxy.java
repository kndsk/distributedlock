package myproxy;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.springframework.util.FileCopyUtils;

public class MyProxy {

	public static final String rt = "\r";
	
	public static Object newProxyInstance(MyClassLoader loader,Class<?> interfaces,MyInvocationHandler h)throws IllegalArgumentException{
		if(h==null){
			throw new NullPointerException();
		}
		Method[] methods = interfaces.getMethods();
		StringBuffer proxyClassString = new StringBuffer();
		proxyClassString.append("package ")
		.append(loader.getProxyClassPackage()).append(";").append(rt)
		.append("import java.lang.reflect.Method;").append(rt)
		.append("public class $Myproxy0 implements ").append(interfaces.getSimpleName()).append("{").append(rt)
		.append("MyInvocationHandler h;").append(rt)
		.append("public $Myproxy0(MyInvocationHandler h){").append(rt).append("this.h = h;}").append(rt)
		.append(getMethodString(methods,interfaces)).append("}");
		//写入java文件进行编译
		String filename = loader.getDir()+File.separator+"$Myproxy0.java";
		File  myproxyFile = new File(filename);
		try{
			compile(proxyClassString,myproxyFile);
			
			//利用自定义的classloader加载
			Class $Myproxy0 = loader.findClass("$Myproxy0");
			Constructor constructor  = $Myproxy0.getConstructor(MyInvocationHandler.class);
			Object o =  constructor.newInstance(h);
			return o;
			}catch(Exception e){
				e.printStackTrace();
			}
		return null;
	}
	
	private static void compile(StringBuffer proxyClassString,File myproxyFile)throws IOException{
		FileCopyUtils.copy(proxyClassString.toString().getBytes(), myproxyFile);
		JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
		StandardJavaFileManager  standardJavaFileManager= javaCompiler.getStandardFileManager(null, null, null);
		Iterable javaFileObjects = standardJavaFileManager.getJavaFileObjects(myproxyFile);
		JavaCompiler.CompilationTask  task = javaCompiler.getTask(null, standardJavaFileManager, null, null, null, javaFileObjects);
	    task.call();
	    standardJavaFileManager.close();
	}
	
	
  private static String getMethodString(Method[] methods,Class interfaces){
	  StringBuffer methodStringBuffer = new StringBuffer();
	  
	  for (Method method : methods) {
		  methodStringBuffer.append("public void ").append(method.getName())
		  .append("()").append("throws Throwable{ ")
		  .append("Method method1=").append(interfaces.getName())
		  .append(".class.getMethod(\"").append(method.getName())
		  .append("\",new Class[]{});")
		  .append("this.h.invoke(this,method1,null);}").append(rt);
	}
	  return methodStringBuffer.toString();
	  
  }
	
}
