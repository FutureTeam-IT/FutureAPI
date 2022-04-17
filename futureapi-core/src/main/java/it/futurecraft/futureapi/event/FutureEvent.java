package it.futurecraft.futureapi.event;

import it.futurecraft.futureapi.FuturePlugin;

/**
 * Represent an event from the plugin.
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
     */
    enum Priority {LOW, NORMAL, HIGH, MONITOR}
}
