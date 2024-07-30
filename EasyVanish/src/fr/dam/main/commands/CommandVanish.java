package fr.dam.main.commands;

import fr.dam.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommandVanish implements CommandExecutor {

    private  Main main;

    public CommandVanish(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            UUID pUUID = p.getUniqueId();

            if(label.equalsIgnoreCase("vanish")){
                if(args.length == 0){
                    // Get vanished ourself


                    if(main.getVanishedPlayers().contains(pUUID)){
                        main.getVanishedPlayers().remove(pUUID);
                        p.sendMessage(ChatColor.GREEN + "You are no longer vanished");
                        for(Player pls : Bukkit.getOnlinePlayers()){
                            pls.showPlayer(p.getPlayer());
                            return true;
                        }
                    }

                    main.getVanishedPlayers().add(pUUID);
                    p.sendMessage(ChatColor.GREEN + "You are now vanished");
                    for(Player pls : Bukkit.getOnlinePlayers()){
                        pls.hidePlayer(p.getPlayer());
                    }
                    return true;
                }


                if(args.length >= 1){
                    // Get the player we want ourself

                    String targetName = args[0];
                    if(Bukkit.getPlayer(targetName) == null){
                        p.sendMessage(ChatColor.RED + "Invalid player");
                        return false;
                    }
                    Player targetPlayer = Bukkit.getPlayer(targetName);
                    UUID targetPlayerUUID = targetPlayer.getUniqueId();
                    if(main.getVanishedPlayers().contains(targetPlayerUUID)){
                        main.getVanishedPlayers().remove(targetPlayerUUID);
                        p.sendMessage(ChatColor.GREEN + "You unvanished " + targetPlayer.getName());
                        targetPlayer.sendMessage(ChatColor.GREEN + p.getName() + "unvanished you");
                        for(Player pls : Bukkit.getOnlinePlayers()){
                            pls.showPlayer(targetPlayer);
                        }
                        return true;
                    }
                    main.getVanishedPlayers().add(targetPlayerUUID);
                    p.sendMessage(ChatColor.GREEN + " You vanished " + targetPlayer.getName());
                    targetPlayer.sendMessage(ChatColor.GREEN + p.getName() + " vanished you");
                    for(Player pls : Bukkit.getOnlinePlayers()){
                        pls.hidePlayer(targetPlayer);
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
