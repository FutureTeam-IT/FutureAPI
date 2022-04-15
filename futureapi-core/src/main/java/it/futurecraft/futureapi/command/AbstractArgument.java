package it.futurecraft.futureapi.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public abstract class AbstractArgument<S extends Invoker, T> implements Command<S, RequiredArgumentBuilder<S, T>> {
    private final String name;
    private final Permission<S> permission;
    private final List<Command<S, ?>> subcommands;
    private final ArgumentType<T> type;
    private final Class<T> clazz;

    protected AbstractArgument(@NotNull String name, @Nullable String permission, ArgumentType<T> type, Class<T> clazz) {
        this.name = name;
        this.permission = permission == null ? null : new Permission<>(permission);
        this.subcommands = new ArrayList<>();
        this.type = type;
        this.clazz = clazz;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public Optional<Permission<S>> getPermission() {
        return Optional.ofNullable(permission);
    }

    @Override
    public List<Command<S, ?>> getSubCommands() {
        return subcommands;
    }

    public abstract CompletableFuture<T> suggest();

    public abstract int run(S invoker, T argument);

    @Override
    public int run(S invoker) {
        return 0;
    }

    @Override
    public @NotNull RequiredArgumentBuilder<S, T> builder() {
        RequiredArgumentBuilder<S, T> builder = RequiredArgumentBuilder.argument(getName(), type);
        subcommands.forEach(command -> builder.then(command.builder()));
        return builder.executes(cmd -> run(cmd.getSource(), cmd.getArgument(name, clazz)));
    }
}
