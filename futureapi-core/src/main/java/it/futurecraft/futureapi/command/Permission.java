package it.futurecraft.futureapi.command;

import java.util.function.Predicate;

/**
 * The permission of a command.
 * <p>
 * Is used to check if a player has the permission to execute the command.
 *
 * @param <S> The type of the sender.
 * @since v0.3.0
 */
public final class Permission<S extends Invoker> implements Predicate<S> {
    private final String permission;

    public Permission(final String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public boolean test(S s) {
        return s.hasPermission(permission);
    }
}
