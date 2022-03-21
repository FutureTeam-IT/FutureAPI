package it.futurecraft.api;

import it.futurecraft.api.files.ConfigModel;
import it.futurecraft.api.files.PluginFile;

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
}
