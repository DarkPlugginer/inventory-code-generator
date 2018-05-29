package me.dark;

import me.dark.commands.CommandTemplate;
import me.dark.commands.ItemCommand;
import me.dark.listener.Events;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main getInstance() { return getPlugin(Main.class); }

    @Override
    public void onLoad() {
        getDataFolder().mkdir();
    }

    @Override
    public void onDisable() {
    }

    @Override
    public void onEnable() {
        ((CraftServer) getServer()).getCommandMap().register("template", new CommandTemplate());
        ((CraftServer) getServer()).getCommandMap().register("item", new ItemCommand());
        getServer().getPluginManager().registerEvents(new Events(), this);
    }
}
