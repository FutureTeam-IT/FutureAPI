package it.futurecraft.api.event;

import it.futurecraft.api.FuturePlugin;

/**
 * Represent an event from the plugin.
 *
 * @since v0.2.0
 */
public interface FutureEvent {
    /**
     * Get the plugin instance.
     *
     * @return The plugin instance.
     */
    FuturePlugin<?> getPlugin();

    /**
     * The execution priority for the event handlers.
     * A handler with a lower priority will be executed before.
     *
     * @since v0.2.3
     */
    enum Priority {LOW, NORMAL, HIGH, MONITOR}
}
