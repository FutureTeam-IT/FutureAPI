package it.futurecraft.futureapi.command.arguments;

import it.futurecraft.futureapi.command.AbstractArgument;
import it.futurecraft.futureapi.command.Invoker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.mojang.brigadier.arguments.StringArgumentType.string;

public abstract class StringArgument<S extends Invoker> extends AbstractArgument<S, String> {
    public StringArgument(@NotNull String name) {
        super(name, null, string(), String.class);
    }

    public StringArgument(@NotNull String name, @Nullable String permission) {
        super(name, permission, string(), String.class);
    }
}