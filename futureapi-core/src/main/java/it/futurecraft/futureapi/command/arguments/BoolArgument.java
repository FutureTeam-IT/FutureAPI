package it.futurecraft.futureapi.command.arguments;

import it.futurecraft.futureapi.command.AbstractArgument;
import it.futurecraft.futureapi.command.Invoker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;

public abstract class BoolArgument<S extends Invoker> extends AbstractArgument<S, Boolean> {
    public BoolArgument(@NotNull String name) {
        super(name, null, bool(), Boolean.class);
    }

    public BoolArgument(@NotNull String name, @Nullable String permission) {
        super(name, permission, bool(), Boolean.class);
    }
}
