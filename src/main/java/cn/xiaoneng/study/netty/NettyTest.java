package cn.xiaoneng.study.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zhaoht
 * @date 2016/3/29 11:16
 */
public class NettyTest {
    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioSocketChannel nioSocketChannel = new NioSocketChannel();
        ChannelInboundHandler channelInboundHandler = new ChannelInboundHandlerAdapter();
        ChannelPipeline channelPipeline = null;
        EventLoop eventLoop ;
        EventLoopGroup eventLoopGroup ;

    }

}
