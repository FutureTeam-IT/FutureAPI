
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

package it.futurecraft.futureapi.command.invoker;

import it.futurecraft.futureapi.FuturePlugin;
import it.futurecraft.futureapi.command.Permission;
import it.futurecraft.futureapi.util.TypeReference;
import net.kyori.adventure.text.Component;

import java.util.UUID;

/**
 * The wrapper class for the invoker interface.
 *
 * @param <T> The original type of the invoker.
 */
public abstract class InvokerWrapper<P extends FuturePlugin<?>, T> extends TypeReference<T> {
    private final P plugin;

    public InvokerWrapper(P plugin) {
        this.plugin = plugin;
    }

    /**
     * Wraps the plugin API invoker to FutureAPI invoker.
     *
     * @param invoker The plugin API invoker.
     * @return The FutureAPI invoker instance.
     */
    public Invoker wrap(T invoker) {
        return new InvokerImpl<>(plugin, this, invoker);
    }

    /**
     * Get the unique id of the command invoker.
     *
     * @param invoker The invoker.
     * @return The unique id.
     */
    public abstract UUID getUniqueId(T invoker);

    /**
     * Get the name of the command invoker.
     *
     * @param invoker The invoker.
     * @return The name.
     */
    public abstract String getName(T invoker);

    /**
     * Send a message to the invoker.
     *
     * @param invoker The invoker.
     * @param message The message to send.
     */
    public abstract void send(T invoker, String message);

    /**
     * Send a message to the invoker.
     *
     * @param invoker   The invoker.
     * @param component The component to send.
     * @see Component
     */
    public abstract void send(T invoker, Component component);

    /**
     * Check whether the invoker has a specific permission.
     *
     * @param invoker    The invoker.
     * @param permission The permission to check.
     * @return {@code true} if the invoker has the permission, {@code false} otherwise.
     */
    public abstract boolean hasPermission(T invoker, String permission);

    /**
     * Check whether the invoker has a specific permission.
     *
     * @param invoker    The invoker.
     * @param permission The permission to check.
     * @return {@code true} if the invoker has the permission, {@code false} otherwise.
     */
    public boolean hasPermission(T invoker, Permission permission) {
        return hasPermission(invoker, permission.getNode());
    }

    /**
     * Check whether the invoker is a console operator.
     *
     * @param invoker The invoker.
     * @return {@code true} if the invoker is a console operator, {@code false} otherwise.
     */
    public abstract boolean isConsole(T invoker);
}
