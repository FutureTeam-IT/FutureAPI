package it.futurecraft.futureapi.command.arguments;

import it.futurecraft.futureapi.command.AbstractArgument;
import it.futurecraft.futureapi.command.Invoker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.mojang.brigadier.arguments.FloatArgumentType.floatArg;

public abstract class FloatArgument<S extends Invoker> extends AbstractArgument<S, Float> {
    public FloatArgument(@NotNull String name) {
        super(name, null, floatArg(), Float.class);
    }

    public FloatArgument(@NotNull String name, @Nullable String permission) {
        super(name, permission, floatArg(), Float.class);
    }

    public FloatArgument(@NotNull String name, @Nullable String permission, float min) {
        super(name, permission, floatArg(min), Float.class);
    }

    public FloatArgument(@NotNull String name, @Nullable String permission, float min, float max) {
        super(name, permission, floatArg(min, max), Float.class);
    }
}