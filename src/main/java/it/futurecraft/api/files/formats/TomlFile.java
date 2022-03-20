package it.futurecraft.api.files.formats;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import it.futurecraft.api.files.FileManager;
import it.futurecraft.api.files.PluginFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class TomlFile<T> implements PluginFile<T> {
    public static final TomlWriter WRITER = new TomlWriter();

    private T data;
    private final Class<T> model;
    private final Path path;
    private final String name;

    public TomlFile(Path path, String name, Class<T> model, T data) {
        this.data = data;
        this.model = model;
        this.path = path;
        this.name = name;
    }

    @Override
    public void save() throws IOException {
        WRITER.write(data, new File(path.toFile(), name + ".toml"));
    }

    @Override
    public T reload() {
        data = new Toml().read(FileManager.toJavaFile(path, name, "toml"))
                .to(model);

        return data;
    }

    @Override
    public T getData() {
        return data;
    }
}
