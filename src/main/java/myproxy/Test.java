package myproxy;

import java.io.File;

public class Test {

	public static void main(String[] args) throws Throwable {
		Man man = new Ly();
		MyHandler myHandler =new MyHandler(man);
		Man proxyMan = (Man)MyProxy.newProxyInstance(new  MyClassLoader("D:\\workspace\\DistributedLock-master\\src\\main\\java\\myproxy", "myproxy"), Man.class, myHandler);
	System.out.println(proxyMan.getClass().getName());
	proxyMan.findObject();
	}

}
