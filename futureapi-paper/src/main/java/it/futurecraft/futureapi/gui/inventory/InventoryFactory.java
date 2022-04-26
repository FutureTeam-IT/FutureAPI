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
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class InventoryFactory implements GUIHolder, InventoryHolder {

    private Inventory inventory;

    private String title;
    private Integer size = null;
    private InventoryType type = null;

    public InventoryFactory(String title, int size) {
        this.title = title;
        this.size = size;
    }

    public InventoryFactory(String title, InventoryType type) {
        this.title = title;
        this.type = type;
    }

    public abstract void registerItems(Player player);
    public abstract void onInventoryInteract(Player player, Action interactType, int slot, ItemStack item);

    public void setItem(int slot, Button button) {

    }

    public void addItem(Button button) {

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
}
