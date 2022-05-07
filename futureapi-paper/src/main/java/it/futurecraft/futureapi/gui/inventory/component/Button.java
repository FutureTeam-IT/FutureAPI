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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class Button {
    private ItemStack itemStack = null;
    private ClickAction clickAction = null;

    public static Button createButton() {
        AlertButton b = new AlertButton();

        return new Button();
    }

    /**
     * Associate an ItemStack to this button object
     * @param itemStack The ItemStack instance
     * @return This button object
     */
    public Button setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    /**
     * Get the ItemStack associated with this button object
     * @return The ItemStack instance
     */
    public ItemStack getItemStack() {
        return itemStack;
    }


    /*
        CLICK ACTION MANAGEMENT
     */

    public ClickAction getClickAction() {
        return clickAction;
    }

    public Button setClickAction(ClickAction clickAction) {
        this.clickAction = clickAction;
        return this;
    }

    public interface ClickAction {
        void action(Player player, ClickType interactType);
    }
}
