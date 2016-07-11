import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.io.Console;
import java.util.*;

/**
 * Created by UserName on 08.07.2016.
 */
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
        boolean b = false;
        for(Player p : hub)
            if(p.name.length() == player.name.length())
                b = true;
        if(b == false) {
            hub.add(player);
            System.out.println("addition");
        }
        System.out.println("dont addition");
        return 0;

       /* if (playersId.contains(player.id)) return -1;
        players.put(player.ctx, player);
        return 0;*/
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
