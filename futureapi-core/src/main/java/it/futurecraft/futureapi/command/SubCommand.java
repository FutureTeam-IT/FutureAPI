package it.futurecraft.futureapi.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Represent a sub command.
 *
 * @param <S> The type of the command sender.
 * @since v0.3.0
 */
public interface SubCommand<S, T> extends BrigadierBuildable<S, LiteralArgumentBuilder<S>> {
    ArgumentType<T> getArgumentType();

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
     * Get a list of sub commands.
     *
     * @return The sub commands list.
     */
    List<SubCommand<S, ?>> getSubCommands();

    /**
     * The action the command has to perform.
     *
     * @param sender   The sender of the command.
     * @param argument The arguments of the command.
     * @return {@code true} if the command was executed successfully, {@code false} otherwise.
     */
    boolean run(S sender, T argument);
}
