package cn.xiaoneng.study.netty.ifeve;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author zhaoht
 * @date 2016/3/29 14:05
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {

    public void channelRead(ChannelHandlerContext context,Object msg){
        ((ByteBuf)msg).release();
    }

    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        cause.printStackTrace();
        context.close();
    }
}
