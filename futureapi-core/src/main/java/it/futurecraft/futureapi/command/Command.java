
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

import it.futurecraft.futureapi.command.types.ArgumentCommand;
import it.futurecraft.futureapi.command.types.LiteralCommand;
import net.kyori.adventure.text.Component;

import java.util.List;
import java.util.Optional;

/**
 * A plugin command.
 *
 * @param <I> The command invoker type.
 */
public interface Command {
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
    default Optional<Permission> getPermission() {
        return Optional.empty();
    }

    /**
     * Get the component for the usage command.
     * <p>It is used to describe the command usage and sent whenever the user has executed with invalid arguments.</p>
     *
     * @return The usage component.
     * @see Component
     */
    default Optional<Component> getUsage() {
        return Optional.empty();
    }

    /**
     * Get a list of the subcommands of the current command.
     *
     * @return The list of subcommands.
     */
    List<Command> getSubCommands();

    /**
     * Add a subcommand to the current command.
     *
     * @param command The subcommand to add.
     * @return {@code true} if the subcommand was added, {@code false} otherwise.
     */
    boolean addSubCommand(Command command);

    /**
     * Check if the command is a literal command.
     *
     * @return {@code true} if the command is a literal command, {@code false} otherwise.
     */
    default boolean isLiteral() {
        return this instanceof LiteralCommand;
    }

    /**
     * Check if the command is an argument command.
     *
     * @return {@code true} if the command is an argument command, {@code false} otherwise.
     */
    default boolean isArgument() {
        return this instanceof ArgumentCommand;
    }
}
