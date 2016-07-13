

import java.io.*;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.concurrent.EventExecutor;


public class ServerHandler extends ChannelInboundHandlerAdapter {

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

        switch (messageType) {

            case ClientCommands.AUTH:
                this.auth(ctx, s);
                break;

            case ClientCommands.PING:
                System.out.println("ping");
                break;

            case ClientCommands.READY:
                if(setReadyOn(ctx) < 0) {
                    ctx.close();
                    System.out.println("user not defined");
                }
                break;

            case ClientCommands.CANCEL:
                if(setReadyOff(ctx) < 0) {
                    ctx.close();
                    System.out.println("user not defined");
                }
                break;

            default:
                System.out.println("echo");
                ctx.writeAndFlush(s);
                //ctx.close();
                break;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        if (cause instanceof ReadTimeoutException) {

        } else {

        }
    }

    public int setReadyOn(ChannelHandlerContext ctx)
    {
        if(Global.instance.players.containsKey(ctx))
        {
            Global.instance.players.get(ctx).ready = true;

            //TRACE:
            System.out.println("user with id : " +
                    Global.instance.players.get(ctx).id +
                    " selected - readyON");
            //END_TRACE;
            return 0;
        }
        else return -1;
    }
    public int setReadyOff(ChannelHandlerContext ctx)
    {
        if(Global.instance.players.containsKey(ctx))
        {
            Global.instance.players.get(ctx).ready = false;

            //TRACE:
            System.out.println("user with id : " +
                    Global.instance.players.get(ctx).id +
                    " selected - readyOFF");
            //END_TRACE;
            return 0;
        }
        else return -1;
    }

    public void auth(ChannelHandlerContext ctx, String s)
    {
        WrapperString ws = new WrapperString(s);
        int id = Base64Codec.DecodeFromString(ws);
        if(Global.instance.connect(id, ctx) == -1) {
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