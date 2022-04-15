package it.futurecraft.futureapi.command.arguments;

import it.futurecraft.futureapi.command.AbstractArgument;
import it.futurecraft.futureapi.command.Invoker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;

public abstract class IntArgument<S extends Invoker> extends AbstractArgument<S, Integer> {
    public IntArgument(@NotNull String name) {
        super(name, null, integer(), Integer.class);
    }

    public IntArgument(@NotNull String name, @Nullable String permission) {
        super(name, permission, integer(), Integer.class);
    }

    public IntArgument(@NotNull String name, @Nullable String permission, int min) {
        super(name, permission, integer(min), Integer.class);
    }

    public IntArgument(@NotNull String name, @Nullable String permission, int min, int max) {
        super(name, permission, integer(min, max), Integer.class);
    }
}
