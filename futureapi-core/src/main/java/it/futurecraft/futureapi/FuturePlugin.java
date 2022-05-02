
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

package it.futurecraft.futureapi;

import it.futurecraft.futureapi.command.CommandManager;
import it.futurecraft.futureapi.event.EventBus;
import it.futurecraft.futureapi.files.ConfigModel;
import it.futurecraft.futureapi.files.PluginFile;

import java.util.Optional;

/**
 * A plugin which uses FutureAPI.
 *
 * @param <C> The type of the configuration file model.
 */
public interface FuturePlugin<C extends ConfigModel> {
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

    /**
     * Get the plugin configuration file.
     *
     * @return The configuration file.
     */
    PluginFile<C> getConfig();

    /**
     * Get the plugin event bus, if it has any.
     *
     * @return The event bus.
     */
    default Optional<EventBus> eventBus() {
        return Optional.empty();
    }

    CommandManager<?> getCommandManager();
}
