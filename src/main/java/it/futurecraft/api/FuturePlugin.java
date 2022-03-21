package it.futurecraft.api;

/**
 * A plugin which uses FutureAPI.
 */
public interface FuturePlugin {
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
}
