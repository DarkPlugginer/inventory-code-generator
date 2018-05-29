package me.dark.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemCommand extends BukkitCommand {

    public ItemCommand() {
        super("item");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if(!(commandSender instanceof Player))
            return true;

        Player player = (Player) commandSender;

        if(strings.length == 5) {
            Material type = Material.getMaterial(strings[0]);
            String displayName = strings[1];
            String lore = strings[2];
            int id = Integer.valueOf(strings[3]);
            int amount = Integer.valueOf(strings[4]);

            player.getInventory().setItem(player.getInventory().firstEmpty(), createItem(type, displayName, lore, id, amount));
        } else {
            player.sendMessage("§c/item [type] [displayName] [lore] [id] [amount]");
        }

        return false;
    }

    public ItemStack createItem(Material material, String displayName, String lore, int id, int amount) {
        ItemStack itemStack = new ItemStack(material);
        itemStack.setAmount(amount);
        itemStack.setDurability((short)id);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(Arrays.asList("", lore, ""));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
