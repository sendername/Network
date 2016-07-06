

import java.io.*;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 23   * Handler implementation for the echo server.
 24   */
@Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String str = new String("Answer from server");
        byte[] byteArray = str.getBytes();
        ByteBuf sendMessage = Unpooled.buffer(byteArray.length);
        for(int i = 0; i < byteArray.length; i ++)
            sendMessage.writeByte(byteArray[i]);
        ctx.write(sendMessage);

        for (int i = 0; i < getByte(msg).capacity(); i ++) {
            byte b = getByte(msg).getByte(i);
            System.out.print((char) b);
        }
    }

    public ByteBuf getByte(Object value)
    {
        return  (ByteBuf)(value);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}