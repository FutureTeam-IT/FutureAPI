
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

    public Invoker wrap(T invoker) {
        return new InvokerImpl<>(plugin, this, invoker);
    }

    public abstract UUID getUniqueId(T invoker);

    public abstract String getName(T invoker);

    public abstract void send(T invoker, String message);

    public abstract void send(T invoker, Component component);

    public abstract boolean hasPermission(T invoker, String permission);

    public abstract boolean isConsole(T invoker);
}
