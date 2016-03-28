package cn.xiaoneng.study.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:zhaoht
 * @date:2016/3/23 14:48
 */

public class ExampleClient extends LeaderSelectorListenerAdapter implements Closeable {


    private final String name;

    private final LeaderSelector leaderSelector;

    private final AtomicInteger leaderCount = new AtomicInteger();

    public ExampleClient( CuratorFramework client,String path, String name) {
        this.name = name;
        this.leaderSelector = new LeaderSelector(client,path,this);
        leaderSelector.autoRequeue();
    }

    public void start(){
        leaderSelector.start();
    }


    @Override
    public void close() throws IOException {

        leaderSelector.close();
    }

    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
        final int waitSeconds = (int) (5 * Math.random()) + 1;

        System.out.println(name+ "is now the leader. Waiting "+waitSeconds + "seconds");

        System.out.println(name + "has been leader " + leaderCount.getAndIncrement());

        try{
            Thread.sleep(TimeUnit.SECONDS.toMillis(waitSeconds));
        }catch (InterruptedException e){
            System.err.println(name + "was interrupted");
            Thread.currentThread().interrupt();
        }finally {
            System.out.printf(name + "relinquishiing leadership.\n");
        }
    }
}
