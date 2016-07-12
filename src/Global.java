import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.io.Console;
import java.util.*;

public class Global {

    Server server;
    Set<Integer> playersId;
    Map<ChannelHandlerContext, Player> players;
    LinkedList<Player> hub;
    final int capacityHub = 5;

    private Global() throws Exception {
        server = new Server(this);
        playersId = new HashSet<>();
        players = new HashMap<>();
        hub = new LinkedList<>();
        server.configure();
    }

    public int connect(Player player)
    {
        if(playersId.contains(player.id)) return -1;
        players.put(player.ctx, player);
        playersId.add(player.id);
        return 0;
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
        Global global = new Global();
    }
}
