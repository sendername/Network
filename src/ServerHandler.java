

import java.io.*;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutor;


public class ServerHandler extends ChannelInboundHandlerAdapter {
    //private ChannelGroup channel = new DefaultChannelGroup(EventExecutor);
    public ServerHandler()
    {

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //channel.add(ctx.channel()); // Клиент пришел
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //channel.remove(ctx.channel());//Клиент ушел
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String s = (String)msg+"\n";
        System.out.print(s);

        char[] ch = s.toCharArray();
        switch (ch[0]) {
            case ClientCommands.AUTH:

                break;
            case ClientCommands.PING:

                break;
            case ClientCommands.READY:

                break;
            default:
                ctx.close();
                break;
        }

        ctx.writeAndFlush(s);
    }
}