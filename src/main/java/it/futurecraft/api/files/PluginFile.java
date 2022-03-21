package it.futurecraft.api.files;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Represent a general file from the plugin.
 *
 * @param <T> The model representing the file content.
 */
public interface PluginFile<T> {
    /**
     * Save the file.
     *
     * @throws IOException If any operation file error.
     */
    void save() throws IOException;

    /**
     * Reload the file.
     *
     * @return The new data.
     */
    T reload();

    /**
     * Get the data read from the file.
     *
     * @return The data object
     */
    T getData();

    /**
     * Get the path to the file directory.
     *
     * @return The file directory path.
     */
    Path getPath();

    /**
     * Get the file name.
     *
     * @return The file name.
     */
    String getName();

    /**
     * Get the file extension.
     *
     * @return The file extension.
     */
    String getExtension();
}
