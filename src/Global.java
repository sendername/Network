import com.sun.deploy.trace.Trace;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.io.Console;
import java.util.*;
import java.lang.*;

public class Global {
    public static Global instance;

    Set<Integer> playersId;
    Map<ChannelHandlerContext, Player> players;
    Map<Room, Player> rooms;

    LinkedList<Player> hub;
    final int capacityHub = 3;

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
            incToHub(p);
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

    public int incToHub(Player player)
    {
        hub.add(player);
        if(hub.size() == capacityHub) {
            for (Player p : hub) {
                p.sendMessage("battle ground active + your id : " + p.id);
                rooms.put(new Room(), p);
            }
            hub.clear();
            return 0;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception
    {
        instance = new Global();
        Server.configure();
    }
}
