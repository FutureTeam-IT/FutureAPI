
/*
 * futureapi Copyright (C) 2022 FutureTeam-IT
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package it.futurecraft.futureapi.files;

import com.moandjiezana.toml.Toml;
import it.futurecraft.futureapi.files.formats.TomlFile;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public final class FileManager {
    private static final Map<String, PluginFile<?>> files = new HashMap<>();

    /**
     * Load a new TOML file.
     *
     * @param path  The path to the file directory.
     * @param name  The file name.
     * @param model The class object of the file model.
     * @param <T>   The file model type.
     * @return The PluginFile instance of the file.
     */
    public static <T> PluginFile<T> toml(@NotNull Path path, @NotNull String name, @NotNull Class<T> model) {
        if (files.containsKey(name)) {
            return (PluginFile<T>) files.get(name);
        }

        T data = new Toml().read(toJavaFile(path, name, "toml")).to(model);

        PluginFile<T> file = new TomlFile<>(path, name, model, data);
        files.put(name, file);

        return file;
    }

    /**
     * Create a file object.
     *
     * @param path      The path to the file directory.
     * @param name      The file name.
     * @param extension The file extension.
     * @return The file object.
     */
    public static File toJavaFile(@NotNull Path path, @NotNull String name, @NotNull String extension) {
        return new File(path.toFile(), name + "." + extension);
    }
}
