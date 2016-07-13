

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
        System.out.println("Message type : " + messageType + " message : " + s);
        switch (messageType) {

            case ClientCommands.AUTH:
                this.auth(ctx, s);
                break;

            case ClientCommands.PING:
                System.out.println("ping");
                break;

            case ClientCommands.READY:
                System.out.print("user ready : true");
                this.linkGlobal.players.get(ctx).ready = true;
                break;

            case ClientCommands.CANCEL:
                System.out.println("user ready = false");
                this.linkGlobal.players.get(ctx).ready = false;
                break;

            default:
                System.out.println("echo");
                ctx.writeAndFlush(s);
                //ctx.close();
                break;
        }
    }

    public void auth(ChannelHandlerContext ctx, String s)
    {
        WrapperString ws = new WrapperString(s);
        int id = Base64Codec.DecodeFromString(ws);
        if(this.linkGlobal.connect(id, ctx) == -1) {
            System.out.print("this id already of server id : " + id);
            ctx.writeAndFlush("yuor id already of list");
            ctx.close();
        }
        else {
            System.out.println("player connect accepted id : " + id);
            ctx.writeAndFlush("connect accepted");
        }
    }
}