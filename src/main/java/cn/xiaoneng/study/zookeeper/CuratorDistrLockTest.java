package cn.xiaoneng.study.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.TimeUnit;

/**
 * @author zhaoht
 * @date 2016/4/15 13:50
 */
public class CuratorDistrLockTest {

    private static  String ZkAddress = "120.27.127.2:2181";
    private static String ZkLockPath = "/Xpush/lock";

    public static void main(String[] args) {
        final CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZkAddress,
                new RetryNTimes(10,5000)
        );
        client.start();
        System.out.println("zk client start successfully");
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                doWithLock(client);
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                doWithLock(client);
            }
        },"t2");

        t1.start();
        t2.start();

    }

    private static  void doWithLock(CuratorFramework client){
        InterProcessMutex lock = new InterProcessMutex(client,ZkLockPath);
        try{
            if(lock.acquire(10 * 1000, TimeUnit.SECONDS)){
                System.out.println(Thread.currentThread().getName() + "hold lock");
                Thread.sleep(500000L);
                System.out.println(Thread.currentThread().getName() + "release lock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                lock.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
