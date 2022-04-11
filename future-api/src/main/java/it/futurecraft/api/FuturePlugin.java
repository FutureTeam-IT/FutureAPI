package it.futurecraft.api;

import it.futurecraft.api.event.EventBus;
import it.futurecraft.api.files.ConfigModel;
import it.futurecraft.api.files.PluginFile;

import java.util.Optional;

/**
 * A plugin which uses FutureAPI.
 */
public interface FuturePlugin<C extends ConfigModel> {
    /**
     * Initialize the plugin.
     * <p>
     * It's the first method called on server startup.
     */
    void init();

    /**
     * Destroy the plugin.
     * <p>
     * It's the first method called on server shutdown.
     */
    void destroy();

    /**
     * Get the plugin configuration file.
     *
     * @return The configuration file.
     */
    PluginFile<C> getConfig();

    /**
     * Get the plugin event bus, if it has any.
     *
     * @return The event bus.
     */
    default Optional<EventBus> eventBus() {
        return Optional.empty();
    }
}
