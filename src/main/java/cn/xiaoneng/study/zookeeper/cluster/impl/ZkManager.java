package cn.xiaoneng.study.zookeeper.cluster.impl;


import cn.xiaoneng.cluster.server.IZKManager;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.EnsurePath;

/**
 * @author zhaoht
 * @date 2016/4/13 13:45
 */
public class ZkManager {

    private CuratorFramework curatorFramework;
    private LeaderSelector _leader;

    public ZkManager(){
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("120.27.127.2:2181")
                .connectionTimeoutMs(5000)
                .sessionTimeoutMs(3000)
                .retryPolicy(new ExponentialBackoffRetry(10,10,1000))
                .namespace("Xpush")
                .build();
        curatorFramework.start();
    }

    public void ensurePath(){
        try {
           // curatorFramework.create().forPath("/hello");
            EnsurePath ensurePath = curatorFramework.newNamespaceAwareEnsurePath("/hello");
           // ensurePath.ensure(curatorFramework.getZookeeperClient());
            curatorFramework.setData().compressed().forPath("/hello","hello".getBytes("UTF-8"));
            byte[] data = curatorFramework.getData().forPath("/hello");
            String str = new String(data,"UTF-8");
            str = String.copyValueOf(str.toCharArray(),0,data.length);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ZkManager zk = new ZkManager();
        zk.ensurePath();
    }

}
