import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.*;

/**
 * Created by UserName on 08.07.2016.
 */
public class Global {
    private static Global ourInstance = new Global();
    public static Global getInstance() {
        return ourInstance;
    }

    Set<Integer> playersId;
    Map<ChannelHandlerContext, Player> players;
    LinkedList<Player> hub;
    final int capacityHub = 5;

    private Global() {
        playersId = new HashSet<>();
        players = new HashMap<>();
        hub = new LinkedList<>();
    }

    public int connect(Player player)
    {
        if (playersId.contains(player.id)) return -1;
        players.put(player.ctx, player);
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
}
