
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

package it.futurecraft.futureapi.event;

import it.futurecraft.futureapi.FuturePlugin;

/**
 * Represent an event from the plugin.
 */
public interface FutureEvent {
    /**
     * Get the plugin instance.
     *
     * @return The plugin instance.
     */
    FuturePlugin<?> getPlugin();

    /**
     * The execution priority for the event handlers.
     * A handler with a lower priority will be executed before.
     */
    enum Priority {LOW, NORMAL, HIGH, MONITOR}
}
