package me.dark.listener;

import me.dark.Main;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Events implements Listener {

    List<Inventory> inventories;

    public Events() {
        inventories = new ArrayList<>();
    }

    @EventHandler
    void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getClickedBlock().getType().name().contains("CHEST")) {
            Chest chest = ((Chest) event.getClickedBlock().getState());
            if(inventories.contains(chest.getInventory()))
                inventories.remove(chest.getInventory());

            inventories.add(chest.getInventory());
        }
    }

    @EventHandler
    void onInventoryClose(InventoryCloseEvent event) throws IOException {
        HumanEntity player = event.getPlayer();

        Inventory inventory = event.getInventory();
        if(inventories.contains(inventory)) {
            if(inventory.getContents().length > 0) {

                File file = new File(Main.getInstance().getDataFolder(), "inventories." + new Random().nextInt(20) + ".txt");
                if(!file.exists())
                    file.createNewFile();

                FileWriter fileWriter = new FileWriter(file);
                for (ItemStack itemStack : inventory.getContents()) {
                    if(itemStack != null && itemStack.getType() != Material.AIR) {
                        String displayName;
                        String lore;
                        int id = itemStack.getDurability();

                        if(itemStack.hasItemMeta()) {
                            displayName = itemStack.getItemMeta().hasDisplayName() ? itemStack.getItemMeta().getDisplayName() : "";
                            lore = itemStack.getItemMeta().hasLore() ? itemStack.getItemMeta().getLore().get(0) : "";
                        } else {
                            displayName = ".";
                            lore = ".";
                        }

                        fileWriter.write(System.getProperty("line.separator") + "createItem" +
                                "(Material." + itemStack.getType().name() +", " + displayName + ", " + lore + ", " + id + ", " + itemStack.getAmount() + ")");
                        fileWriter.flush();
                    }
                }

                fileWriter.close();
                inventories.remove(inventory);
            }
        }
    }
}
