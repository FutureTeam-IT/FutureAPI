package it.futurecraft.futureapi.command.arguments;

import it.futurecraft.futureapi.command.AbstractArgument;
import it.futurecraft.futureapi.command.Invoker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.mojang.brigadier.arguments.StringArgumentType.word;

public abstract class WordArgument<S extends Invoker> extends AbstractArgument<S, String> {
    public WordArgument(@NotNull String name) {
        super(name, null, word(), String.class);
    }

    public WordArgument(@NotNull String name, @Nullable String permission) {
        super(name, permission, word(), String.class);
    }
}
