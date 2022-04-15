package it.futurecraft.futureapi.command.arguments;

import it.futurecraft.futureapi.command.AbstractArgument;
import it.futurecraft.futureapi.command.Invoker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.mojang.brigadier.arguments.LongArgumentType.longArg;

public abstract class LongArgument<S extends Invoker> extends AbstractArgument<S, Long> {
    public LongArgument(@NotNull String name) {
        super(name, null, longArg(), Long.class);
    }

    public LongArgument(@NotNull String name, @Nullable String permission) {
        super(name, permission, longArg(), Long.class);
    }

    public LongArgument(@NotNull String name, @Nullable String permission, long min) {
        super(name, permission, longArg(min), Long.class);
    }

    public LongArgument(@NotNull String name, @Nullable String permission, long min, long max) {
        super(name, permission, longArg(min, max), Long.class);
    }
}
