package it.futurecraft.futureapi.command.arguments;

import it.futurecraft.futureapi.command.AbstractArgument;
import it.futurecraft.futureapi.command.Invoker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;

public abstract class GreedyStringArgument<S extends Invoker> extends AbstractArgument<S, String> {
    public GreedyStringArgument(@NotNull String name) {
        super(name, null, greedyString(), String.class);
    }

    public GreedyStringArgument(@NotNull String name, @Nullable String permission) {
        super(name, permission, greedyString(), String.class);
    }
}