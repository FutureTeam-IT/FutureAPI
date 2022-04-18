
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

package it.futurecraft.futureapi.files.formats;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import it.futurecraft.futureapi.files.FileManager;
import it.futurecraft.futureapi.files.PluginFile;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Represent a TOML file.
 *
 * @param <T> The model representing the file content.
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
