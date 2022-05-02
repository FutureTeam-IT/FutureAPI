
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

import it.futurecraft.futureapi.command.types.LiteralCommand;

import java.util.List;

/**
 * The plugin command manager.
 *
 * @param <I> The command interface.
 */
public interface CommandManager {
    /**
     * Get the list of registered commands.
     *
     * @return The list of registered commands.
     */
    List<Command> getCommands();

    /**
     * Get the command with the given name.
     * <p>If not found by name, it will check aliases.</p>
     *
     * @param name The name of the command.
     * @return The command with the given name.
     */
    Command getCommand(String name);

    /**
     * Register a new command.
     * <p>Only literal commands can be registered as root nodes.</p>
     *
     * @param command The command to register.
     */
    void register(LiteralCommand command);

    /**
     * Unregister a command.
     *
     * @param command The command to unregister.
     */
    void unregister(LiteralCommand command);
}
