package it.futurecraft.api.files.formats;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import it.futurecraft.api.files.FileManager;
import it.futurecraft.api.files.PluginFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Represent a TOML file.
 *
 * @param <T> The model representing the file content.
 * @since v0.1.1
 */
public final class TomlFile<T> extends PluginFile<T> {
    /**
     * The file write for toml format.
     */
    public static final TomlWriter WRITER = new TomlWriter();

    public TomlFile(Path path, String name, Class<T> model, T data) {
        super(path, name, model, data);
    }

    @Override
    public void save() throws IOException {
        WRITER.write(getData(), FileManager.toJavaFile(getPath(), getName(), "toml"));
    }

    @Override
    public T reload() {
        T data = new Toml().read(FileManager.toJavaFile(getPath(), getName(), "toml"))
                .to(model);

        setData(data);

        return data;
    }

    @Override
    public String getExtension() {
        return "toml";
    }
}
