package redisSc;

/**
 * Created by liuyang on 2017/4/20.
 */
public class ThreadB extends Thread {
    private ServiceB service;

    public ThreadB(ServiceB service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}
