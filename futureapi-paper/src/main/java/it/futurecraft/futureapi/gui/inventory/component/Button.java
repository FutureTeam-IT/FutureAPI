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

package it.futurecraft.futureapi.gui.inventory.component;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public final class Button {
    private ItemStack itemStack;
    private int slot;
    private ClickAction action;

    public static Button createButton() {
        return new Button();
    }

    public Button setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }
    public ItemStack getItemStack() {
        return itemStack;
    }

    public Button setSlot(int slot) {
        this.slot = slot;
        return this;
    }


    public interface ClickAction {
        void action(Player player, Action interactType);
    }
}
