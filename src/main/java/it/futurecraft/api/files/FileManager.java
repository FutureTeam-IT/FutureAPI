package it.futurecraft.api.files;

import com.moandjiezana.toml.Toml;
import it.futurecraft.api.files.formats.TomlFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class FileManager {
    private static Map<String, PluginFile<?>> files = new HashMap<>();

    public static <T> PluginFile<T> toml(@NotNull Path path, @NotNull String name, @NotNull Class<T> model) {
        if (files.containsKey(name)) {
            return (PluginFile<T>) files.get(name);
        }

        T data = new Toml().read(toJavaFile(path, name, "toml"))
                .to(model);

        PluginFile<T> file = new TomlFile<>(path, name, model, data);
        files.put(name, file);

        return file;
    }

    public static File toJavaFile(@NotNull Path path, @NotNull String name, @NotNull String extension) {
        return new File(path.toFile(), name + "." + extension);
    }
}
