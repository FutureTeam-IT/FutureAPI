package it.futurecraft.api.files;

import java.io.IOException;

/**
 * Represent a general file from the plugin.
 * @param <T> The class representing the file content.
 */
public interface PluginFile<T> {
    /**
     * Save the file.
     */
    void save() throws IOException;

    /**
     * Reload the file.
     * @return The new data.
     */
    T reload();

    /**
     * Get the data read from the file.
     * @return The data object
     */
    T getData();
}
