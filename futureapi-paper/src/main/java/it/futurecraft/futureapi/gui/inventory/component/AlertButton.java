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

import it.futurecraft.futureapi.gui.inventory.InventoryFactory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class AlertButton extends Button {
    private int showingTicks = 20;
    private Runnable callback;
    private boolean asynch;

    public static AlertButton createAlertButton() {
        return new AlertButton();
    }

    public AlertButton setShowingTick(int showingTicks) {
        this.showingTicks = showingTicks;
        return this;
    }

    public AlertButton callback(Runnable callback, boolean asynch) {
        this.callback = callback;
        this.asynch = asynch;
        return this;
    }

    public void start(int slot, InventoryFactory factory) {
        Plugin plugin = factory.getPlugin();
        Button oldButton = factory.getButton(slot);
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
            if (asynch) this.callback.run();
            else Bukkit.getScheduler().runTask(plugin, callback);
            factory.setItem(slot, this);
        }, showingTicks);
    }
}
