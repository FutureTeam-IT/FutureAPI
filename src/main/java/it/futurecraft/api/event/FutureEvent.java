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
}
