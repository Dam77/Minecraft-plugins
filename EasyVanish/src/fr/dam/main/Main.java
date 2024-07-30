package fr.dam.main;

import fr.dam.main.commands.CommandVanish;
import fr.dam.main.listeners.mainListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public class Main extends JavaPlugin {

    private ArrayList<UUID> vanishedPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        getCommand("vanish").setExecutor(new CommandVanish(this));

        getServer().getPluginManager().registerEvents(new mainListener(this), this);

        saveDefaultConfig();
    }

    public ArrayList<UUID> getVanishedPlayers() {
        return vanishedPlayers;
    }

}
