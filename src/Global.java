import com.sun.deploy.trace.Trace;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.io.Console;
import java.util.*;

public class Global {
    public static Global instance;

    Set<Integer> playersId;
    Map<ChannelHandlerContext, Player> players;
    Map<Room, Player> rooms;

    LinkedList<Player> hub;
    final int capacityHub = 5;

    private Global() throws Exception {
        playersId = new HashSet<>();
        players = new HashMap<>();
        hub = new LinkedList<>();
    }


    public int connect(int id, ChannelHandlerContext ctx)
    {
        if(playersId.contains(id))
            return -1;
        else
        {
            Player p = new Player(id, ctx);
            players.put(ctx, p);
            playersId.add(p.id);
            p.sendState();
            return 0;
        }
    }

    public void disconnect(Player player)
    {
        players.remove(player.ctx);
        playersId.remove(player.id);
        if(player.ready) hub.remove(player);
        player.ctx.close();
    }

    public int setReady(Player player, boolean ready)
    {
        if(player.ready == ready) return -1;
        hub.addLast(player);
        player.ready = ready;
        if(hub.size() == capacityHub)
            ;// if N players, start game;
        return 0;
    }

    public static void main(String[] args) throws Exception
    {
        instance = new Global();
        Server.configure();
    }
}
