package it.futurecraft.futureapi.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The abstract literal command.
 *
 * @param <S> The type of the command sender.
 * @since v0.3.0
 */
public abstract class AbstractCommand<S extends Invoker> implements Command<S, LiteralArgumentBuilder<S>> {
    private final String name;
    private final Permission<S> permission;
    private final List<Command<S, ?>> subcommands;

    private AbstractCommand(@NotNull String name, @Nullable String permission) {
        this.name = name;
        this.permission = permission == null ? null : new Permission<>(permission);
        this.subcommands = new ArrayList<>();
    }

    public AbstractCommand(@NotNull String name) {
        this(name, null);
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

    @Override
    public @NotNull LiteralArgumentBuilder<S> builder() {
        LiteralArgumentBuilder<S> builder = LiteralArgumentBuilder.literal(name);
        subcommands.forEach(command -> builder.then(command.builder()));
        return builder.executes(ctx -> run(ctx.getSource()));
    }
}