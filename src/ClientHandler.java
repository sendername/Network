/**
 * Created by UserName on 05.07.2016.
 */
  import io.netty.buffer.ByteBuf;
  import io.netty.buffer.Unpooled;
  import io.netty.channel.ChannelHandlerContext;
  import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf firstMessage;

    public ClientHandler()
    {
        String messageToServer = new String("From client");
        byte[] byteArray = messageToServer.getBytes();
        firstMessage = Unpooled.buffer(4);
        for (int i = 0; i < messageToServer.length(); i ++) {
            firstMessage.writeByte(byteArray[i]);
        }
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx)
    {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public  void channelRead(ChannelHandlerContext ctx, Object msg)
    {
        System.out.print(msg);
        ctx.flush(); // (2)
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
    {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
    {
        cause.printStackTrace();
        ctx.close();
    }

}
