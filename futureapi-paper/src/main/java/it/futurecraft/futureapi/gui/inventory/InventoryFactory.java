/*
 * futureapi Copyright (C) 2022 FutureTeam-IT
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.futurecraft.futureapi.gui.inventory;

import it.futurecraft.futureapi.gui.GUIHolder;
import it.futurecraft.futureapi.gui.inventory.component.Button;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public abstract class InventoryFactory implements GUIHolder, Listener, InventoryHolder {
    // Check if the listener has been already registered one time
    private static final boolean registeredListener = false;

    private Inventory inventory;
    private final Button[] buttons;

    private final String title;
    private Integer size = null;
    private InventoryType type = null;

    protected boolean preventPickup = true;

    public InventoryFactory(Plugin plugin, String title, int size) {
        this.title = title;
        this.size = size;
        this.buttons = new Button[size];
        if (!registeredListener)
            Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public InventoryFactory(Plugin plugin, String title, InventoryType type) {
        this.title = title;
        this.type = type;
        this.buttons = new Button[type.getDefaultSize()];
        if (!registeredListener)
            Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public abstract void registerItems(Player player);
    public abstract void onInventoryInteract(Player player, ClickType clickType, int slot, ItemStack item);

    protected void handleClick(InventoryClickEvent e) {
        if (preventPickup) e.setCancelled(true);
        if (buttons[e.getSlot()-1]!=null)
            this.onInventoryInteract(((Player) e.getWhoClicked()), e.getClick(), e.getSlot(), e.getCurrentItem());
    }


    /**
     * Set a new button into the inventory
     * @param slot The slot
     * @param button The button instance, null for remove the item
     */
    public void setItem(int slot, @Nullable Button button) {
        if (button==null) {
            buttons[slot]=null;
            inventory.setItem(slot, null);
        }else {
            if (slot>=size) return;
            buttons[slot]=button;
            inventory.setItem(slot, button.getItemStack());
        }
    }

    /**
     * Add a new button into the first inventory empty slot
     * @param button The button instance
     */
    public void addItem(@NotNull Button button) {
        int slot = inventory.firstEmpty();
        buttons[slot] = button;
        inventory.addItem(button.getItemStack());
    }

    /**
     * Get the Button instance from the inventory slot
     * @param slot The inventory slot
     * @return The Button instance or null if the current inventory slot doesn't contains any ItemStack
     */
    public Button getButton(int slot) {
        if (slot>=size) return null;
        return buttons[slot];
    }

    /**
     * @param player The player to open the current inventory
     * @apiNote If a second player open this inventory, all the item preferences will be associated with the first player
     */
    @Override
    public void open(Player player) {
        if (inventory==null) {
            if (type==null) inventory = Bukkit.createInventory(this, size, title);
            else inventory = Bukkit.createInventory(this, type, title);

            // Register the items under the first player
            registerItems(player);
        }
        player.openInventory(inventory);
    }


    /*
        LISTENER MANAGER
     */
    @EventHandler
    private void onClick(InventoryClickEvent e) {
        if (e.getInventory().getHolder() instanceof InventoryFactory)
            ((InventoryFactory) e.getInventory().getHolder()).handleClick(e);
    }
}
