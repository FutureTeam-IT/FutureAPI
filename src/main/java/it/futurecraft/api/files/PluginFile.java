package it.futurecraft.api.files;

/**
 * Represent a general file from the plugin.
 * @param <T> The class representing the file content.
 */
public interface PluginFile<T> {
    /**
     * Save the file.
     */
    void save();

    /**
     * Reload the file.
     */
    void reload();

    /**
     * Get the data read from the file.
     * @return The data object
     */
    T getData();
}
