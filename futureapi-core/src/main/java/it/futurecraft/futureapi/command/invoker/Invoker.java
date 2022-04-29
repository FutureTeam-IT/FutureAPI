
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

package it.futurecraft.futureapi.command.invoker;

import it.futurecraft.futureapi.FuturePlugin;
import it.futurecraft.futureapi.command.Permission;
import net.kyori.adventure.text.Component;

import java.util.UUID;

/**
 * The command invoker interface.
 */
public interface Invoker {
    UUID CONSOLE_INVOKER_UUID = new UUID(0, 0);
    String CONSOLE_INVOKER_NAME = "Console";

    /**
     * Get the plugin instance.
     *
     * @return The plugin instance.
     */
    FuturePlugin<?> getPlugin();

    /**
     * Get the player UUID.
     *
     * @return The player UUID.
     */
    UUID getUniqueId();

    /**
     * Get the player name.
     *
     * @return The player name.
     */
    String getName();

    /**
     * Send a message to the player.
     *
     * @param message The message to send.
     */
    void send(String message);

    /**
     * Send a component to the player.
     *
     * @param component The component to send.
     * @see Component
     */
    void send(Component component);

    /**
     * Check whether the invoker has a specific permission.
     *
     * @param permission The permission to check.
     * @return {@code true} if the invoker has the permission, {@code false} otherwise.
     */
    boolean hasPermission(String permission);

    /**
     * Check whether the invoker has a specific permission.
     *
     * @param permission The permission to check.
     * @return {@code true} if the invoker has the permission, {@code false} otherwise.
     */
    boolean hasPermission(Permission permission);

    /**
     * Check whether the invoker is a console operator.
     *
     * @return {@code true} if the invoker is a console operator, {@code false} otherwise.
     */
    boolean isConsole();
}
