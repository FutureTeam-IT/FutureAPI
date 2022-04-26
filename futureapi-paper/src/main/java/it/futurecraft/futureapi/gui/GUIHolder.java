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

package it.futurecraft.futureapi.gui;

import org.bukkit.entity.Player;

/**
 * This interface is used to chain the difference minecraft UI
 */
public interface GUIHolder {
    /**
     * Open the Graphical User Interface to a player
     * @param player The player to open the current gui
     */
    void open(Player player);

    /**
     * Open the Graphical User Interface to differential players
     * @param players The players
     */
    default void open(Player... players) {
        for (Player p : players) open(p);
    }
}
