package cn.xiaoneng.study.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

/**
 * @author zhaoht
 * @date 2016/3/29 11:52
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private final ByteBuf firstMessage;

    public TimeClientHandler(){
        byte[] req = "QUERY TIME ORDER ".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    public void channelActive(ChannelHandlerContext context){
        //与服务端建立连接后
        System.out.println("client channelActive");
        context.writeAndFlush(firstMessage);
    }

    public void channelRead(ChannelHandlerContext context,Object msg) throws UnsupportedEncodingException {
        System.out.println("client channelRead....");
        //服务端返回消息
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("Now is :" + body);
    }

    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        System.out.println("client exceptionCaught..");
        System.out.println("Unexpected exception from downstream:"
                + cause.getMessage());
        context.close();
    }
}
