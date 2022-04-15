package it.futurecraft.futureapi.command;

import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * Represent a plugin command.
 *
 * @param <S> The type of the command sender.
 * @param <T> The type of the command builder.
 * @since v0.3.0
 */
public interface Command<S extends Invoker, T extends ArgumentBuilder<S, T>> {
    /**
     * The name of the command.
     *
     * @return The command name.
     */
    @NotNull String getName();

    /**
     * The permission required to execute the command.
     *
     * @return The command permission.
     */
    Optional<Permission<S>> getPermission();

    /**
     * The description of the command.
     *
     * @return The command description.
     */
    @NotNull Component getUsage();

    /**
     * Get a list of the sub commands of the command.
     * <p>
     * If the command has no sub commands, return an empty list.
     *
     * @return The list of sub commands.
     */
    List<Command<S, ?>> getSubCommands();

    /**
     * The action to perform when the command is executed.
     *
     * @param sender The invoker of the command.
     * @return The result of the command execution.
     */
    int run(S sender);

    /**
     * Create an instance of the brigadier builder for the command.
     * <p>
     * It can be a {@code LiteralArgumentBuilder} or a {@code RequiredArgumentBuilder}.
     *
     * @return The builder instance.
     * @see ArgumentBuilder
     * @see LiteralArgumentBuilder
     * @see RequiredArgumentBuilder
     */
    @NotNull T builder();
}
