

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
        String s = (String)msg;
        System.out.println(s);

        /*char[] ch = s.toCharArray();
        for (char c: ch) {
            System.out.print((int)c);
            System.out.print(" ");
        }
        System.out.println();*/
/*
        switch (ch[0]) {
            case 'A':
                System.out.println("A");
                break;
            case 'B':
                System.out.println("B");
                break;
            case ClientCommands.READY:

                break;
            default:
                //ctx.close();
                System.out.println("?");
                break;
        }
*/
        ctx.writeAndFlush(s);
    }
}