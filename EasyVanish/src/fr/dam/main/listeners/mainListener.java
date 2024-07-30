package fr.dam.main.listeners;

import fr.dam.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class mainListener implements Listener {

    private Main main;

    public mainListener(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        UUID pUUID = p.getUniqueId();

        if(main.getVanishedPlayers().contains(pUUID)){
            p.sendMessage(ChatColor.GREEN + "You are still vanished, type : /vanish to get unvanished");
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        UUID pUUID = p.getUniqueId();

        if(main.getConfig().getBoolean("onLeave")){
            if (main.getVanishedPlayers().contains(pUUID)) {
                main.getVanishedPlayers().remove(pUUID);
                for(Player pls : Bukkit.getOnlinePlayers()){
                    pls.showPlayer(p);
                }
                System.out.println(p.getName() + "left the server and is now unvanished");
            }
        }
    }
}
