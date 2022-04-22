
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
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The abstract command.
 *
 * @param <S> The type of the command sender.
 * @param <T> THe type of the command.
 */
public abstract class AbstractCommand<S, T extends ArgumentBuilder<S, T>> implements Command<S, T> {
    private final String name;
    private final String permission;
    private final Component usage;
    private final List<Command<S, ?>> subcommands;

    public AbstractCommand(@NotNull String name) {
        this(name, null, null);
    }

    public AbstractCommand(@NotNull String name, @NotNull String permission) {
        this(name, permission, null);
    }

    public AbstractCommand(@NotNull String name, @NotNull Component usage) {
        this(name, null, usage);
    }

    public AbstractCommand(@NotNull String name, @Nullable String permission, @Nullable Component usage) {
        this.name = name;
        this.permission = permission;
        this.usage = usage;
        this.subcommands = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Optional<String> getPermission() {
        return Optional.ofNullable(permission);
    }

    @Override
    public Optional<Component> getUsage() {
        return Optional.ofNullable(usage);
    }

    @Override
    @Unmodifiable
    public List<Command<S, ?>> getSubcommands() {
        return this.subcommands.stream().toList();
    }

    public Command<S, ?> addSubcommand(@NotNull Command<S, ?> subcommand) {
        if (subcommands.contains(subcommand)) {
            throw new IllegalArgumentException("The subcommand is already registered: " + subcommand.getName());
        }

        subcommands.add(subcommand);
        return subcommand;
    }
}
