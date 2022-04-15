package it.futurecraft.futureapi.command;

import com.mojang.brigadier.builder.ArgumentBuilder;

/**
 * Represent a command buildable to brigadier literal.
 *
 * @param <S> The type of the command sender.
 * @param <T> The argument builder type.
 * @since v0.3.0
 */
public interface BrigadierBuildable<S, T extends ArgumentBuilder<S, T>> {
    /**
     * Builds the command to brigadier literal builder.
     *
     * @return The brigadier literal builder.
     */
    ArgumentBuilder<S, T> build();
}
