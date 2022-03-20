package it.futurecraft.api;

/**
 * A plugin which uses FutureAPI.
 */
public interface FuturePlugin {
    /**
     * Initialize the plugin.
     */
    void init();

    /**
     * Destroy the plugin.
     */
    void destroy();
}
