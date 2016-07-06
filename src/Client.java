/**
 * Created by UserName on 05.07.2016.
 */
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;


public class Client {

    static final String HOST = System.getProperty("host", "0.0.0.0");
    static final int PORT = Integer.parseInt(System.getProperty("port", "8080"));
    static final int SIZE = Integer.parseInt(System.getProperty("size", "4"));

    public static void main(String[] args) throws Exception
    {

    EventLoopGroup group = new NioEventLoopGroup();
    try{
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    public void  initChannel(SocketChannel ch) throws Exception
                    {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new ClientHandler());
                    }
        });
        ChannelFuture f = b.connect(HOST, PORT).sync();
        f.channel().closeFuture().sync();
    }
    finally {
        group.shutdownGracefully();
    }
    }
}
