import io.netty.channel.ChannelHandlerContext;

/**
 * Created by UserName on 06.07.2016.
 */
public class Player {
    int id;
    ChannelHandlerContext ctx;

    public Player(int id, ChannelHandlerContext ctx)
    {
        this.id = id;
        this.ctx = ctx;
    }

    public void sendMessage(String msg) {

    }
}
