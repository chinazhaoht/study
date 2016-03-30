package cn.xiaoneng.study.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author zhaoht
 * @date 2016/3/29 11:39
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext context, Object msg) throws UnsupportedEncodingException {
        System.out.println("server channel Read...");
        ByteBuf buf = (ByteBuf)msg;
        byte[] req = new byte[buf.readableBytes()];
        String body = new String(req,"UTF-8");
        System.out.println("The time server receive order:"+body);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? new Date(
                System.currentTimeMillis()
        ).toString():"BAD ORDER";

        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        context.write(resp);
    }

    public void channelReadComplete(ChannelHandlerContext context){
        System.out.println("server channelReadComplete..");
        context.flush();
    }

    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        System.out.println("server exceptionCaught..");
        context.close();
    }
}
