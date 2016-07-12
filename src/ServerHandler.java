

import java.io.*;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutor;


public class ServerHandler extends ChannelInboundHandlerAdapter {
    //private ChannelGroup channel = new DefaultChannelGroup(EventExecutor);
    Global linkGlobal;
    public ServerHandler(Global linkGlobal)
    {
        this.linkGlobal = linkGlobal;
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
        String s = (String) msg;

        int messageType;
        if(s.length() == 0) {
            messageType = -1;
        }else{
            messageType = Base64Codec.Decode(s.charAt(0));
            s = s.substring(1);
        }
        //int id = Base64Codec.DecodeFromString(s);
        System.out.println(messageType);

        switch (messageType) {

            case ClientCommands.AUTH:
                WrapperString ws = new WrapperString(s);
                int id = Base64Codec.DecodeFromString(ws);
                System.out.println(id);
                System.out.println(ws.s);
                /*if(this.linkGlobal.connect(new Player(0, "name", null)) == 0)
                    System.out.println("Accepted");
                else
                System.out.println("Server contained this user");*/
                break;

            case ClientCommands.PING:
                System.out.println("ping");
                break;

            case ClientCommands.READY:
                System.out.println("ready");
                break;

            default:
                ctx.writeAndFlush("?");
                ctx.close();
                System.out.println("server info::default");
                break;
        }
    }
}