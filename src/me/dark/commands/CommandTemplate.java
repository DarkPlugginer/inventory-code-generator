package me.dark.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;

public class CommandTemplate extends BukkitCommand {

    public CommandTemplate() {
        super("template");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        return false;
    }
}
