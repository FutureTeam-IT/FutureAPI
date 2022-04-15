package it.futurecraft.futureapi.command.arguments;

import it.futurecraft.futureapi.command.AbstractArgument;
import it.futurecraft.futureapi.command.Invoker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.mojang.brigadier.arguments.DoubleArgumentType.doubleArg;

public abstract class DoubleArgument<S extends Invoker> extends AbstractArgument<S, Double> {
    public DoubleArgument(@NotNull String name) {
        super(name, null, doubleArg(), Double.class);
    }

    public DoubleArgument(@NotNull String name, @Nullable String permission) {
        super(name, permission, doubleArg(), Double.class);
    }

    public DoubleArgument(@NotNull String name, @Nullable String permission, double min) {
        super(name, permission, doubleArg(min), Double.class);
    }

    public DoubleArgument(@NotNull String name, @Nullable String permission, double min, double max) {
        super(name, permission, doubleArg(min, max), Double.class);
    }
}