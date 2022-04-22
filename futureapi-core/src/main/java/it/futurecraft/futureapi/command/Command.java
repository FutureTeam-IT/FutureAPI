
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

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.suggestion.Suggestion;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Represent a command.
 *
 * @param <S> The command sender type.
 */
public interface Command<S, T extends ArgumentBuilder<S, T>> {
    /**
     * Get the command name.
     *
     * @return The command name.
     */
    String getName();

    /**
     * Get the permission node required to run the command.
     *
     * @return The command permission.
     */
    Optional<String> getPermission();

    /**
     * Get the usage message to send to the command sender.
     *
     * @return The message component.
     * @see Component
     */
    Optional<Component> getUsage();

    /**
     * Get a list of the command subcommands.
     *
     * @return The subcommand list.
     */
    @Unmodifiable List<Command<S, ?>> getSubcommands();

    /**
     * Add a subcommand to the command.
     *
     * @param subcommand The subcommand to add.
     * @return The subcommand.
     * @throws IllegalArgumentException If the subcommand is already registered.
     */
    Command<S, ?> addSubcommand(@NotNull Command<S, ?> subcommand);

    /**
     * Create a list of suggestions for the command asynchronously.
     *
     * @param sender The command sender.
     * @return The suggestion list.
     */
    CompletableFuture<List<Suggestion>> suggest(S sender);

    /**
     * Builds the command.
     *
     * @return The argument builder.
     * @see ArgumentBuilder
     */
    T build();
}
