package it.futurecraft.api.command;

import it.futurecraft.api.FuturePlugin;

/**
 * This interface has a command handler function
 *
 * @since 0.3.1
 */
public interface CommandHandler {
    /**
     * This object contains all the main command
     * information, like the name, the main permission
     * or it the command are currently disabled.
     * @see Command
     * @return A record containing the command information
     */
    Command getCommand();

    /**
     * @return The future plugin instance
     */
    FuturePlugin<?> getPlugin();
}
