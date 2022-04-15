package it.futurecraft.futureapi.command;

/**
 * Represents a command invoker.
 *
 * @since v0.3.0
 */
public interface Invoker {
    /**
     * Checks whether the invoker has the specified permission.
     *
     * @param permission The permission to check.
     * @return {@code true} if the invoker has the specified permission, {@code false} otherwise.
     */
    boolean hasPermission(String permission);
}
