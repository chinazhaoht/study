package cn.xiaoneng.study.zookeeper;

import org.apache.commons.beanutils.converters.IntegerArrayConverter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author zhaoht
 * @date 2016/4/15 11:47
 */
public class CuratorWatchTest {

    private static final String ZK_ADDRESS = "120.27.127.2:2181";
    private static String path = "/Xpush/hello";


    public static void main(String[] args) {
        CuratorFramework client = CuratorFrameworkFactory.newClient(
                ZK_ADDRESS,
                new RetryNTimes(10, 5000)
        );

        Executor executor = Executors.newSingleThreadScheduledExecutor();

        client.start();
        System.out.println("zk client start successfully!");

        PathChildrenCache watcher = new PathChildrenCache(
                client,
                path,
                true
        );
        watcher.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                ChildData data = pathChildrenCacheEvent.getData();
                if (data == null) {
                    System.out.println("No data in event[" + pathChildrenCacheEvent + "]");
                } else {
                    System.out.println(pathChildrenCacheEvent.getType());
                    System.out.println(data.getPath());
                    System.out.printf(new String(data.getData()));
                    System.out.println(data.getStat());

                }
            }
        });

        try {
            watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
            System.out.println("Register zk watcher successfully");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
