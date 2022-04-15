package it.futurecraft.futureapi.files;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Represent a general file from the plugin.
 *
 * @param <T> The model representing the file content.
 */
public abstract class PluginFile<T> {
    protected final Class<T> model;
    private final Path path;
    private final String name;
    private T data;

    public PluginFile(@NotNull Path path, @NotNull String name, @NotNull Class<T> model, @NotNull T data) {
        this.path = path;
        this.name = name;
        this.model = model;
        this.data = data;
    }

    /**
     * Save the file.
     *
     * @throws IOException If any operation file error.
     */
    public abstract void save() throws IOException;

    /**
     * Reload the file.
     *
     * @return The new data.
     */
    public abstract T reload();

    /**
     * Get the data read from the file.
     *
     * @return The data object
     */
    public T getData() {
        return this.data;
    }

    public void setData(@NotNull T data) {
        this.data = data;
    }

    /**
     * Get the path to the file directory.
     *
     * @return The file directory path.
     */
    public Path getPath() {
        return path;
    }

    /**
     * Get the file name.
     *
     * @return The file name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the file extension.
     *
     * @return The file extension.
     */
    abstract public String getExtension();
}
