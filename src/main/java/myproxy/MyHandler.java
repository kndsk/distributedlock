package myproxy;

import java.lang.reflect.Method;

public class MyHandler implements MyInvocationHandler {
	private Man man;
	
	public MyHandler(Man man) {
		this.man = man;
	}
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object invoke = method.invoke(man, args);
		after();
		return invoke;
	}
	public void before(){
		System.out.println("before-------------------------------before");
	}
	
	public void after(){
		System.out.println("after-------------------------------after");
	}

}
