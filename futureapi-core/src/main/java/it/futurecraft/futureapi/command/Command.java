
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

package it.futurecraft.futureapi.command;

import net.kyori.adventure.text.Component;

import java.util.Optional;

/**
 * A plugin command.
 *
 * @param <I> The command invoker type.
 */
public interface Command<I> {
    /**
     * Get the command name.
     *
     * @return The command name.
     */
    String getName();

    /**
     * Get the permission node required for the sender to execute the command.
     *
     * @return The permission node.
     */
    Optional<String> getPermission();

    /**
     * Get the component for the usage command.
     *
     * @return The usage component.
     * @see Component
     */
    Optional<Component> getUsage();
}
