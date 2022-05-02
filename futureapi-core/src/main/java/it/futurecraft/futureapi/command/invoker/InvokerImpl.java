
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
import net.kyori.adventure.text.Component;

import java.util.Objects;
import java.util.UUID;

final class InvokerImpl<T> implements Invoker {
    private final FuturePlugin<?> plugin;

    private final InvokerWrapper<?, T> wrapper;
    private final T invoker;

    private final UUID uuid;
    private final String name;
    private final boolean isConsole;

    public InvokerImpl(FuturePlugin<?> plugin, InvokerWrapper<?, T> wrapper, T invoker) {
        this.plugin = plugin;
        this.wrapper = wrapper;
        this.invoker = invoker;

        this.uuid = wrapper.getUniqueId(invoker);
        this.name = wrapper.getName(invoker);
        this.isConsole = wrapper.isConsole(invoker);
    }

    @Override
    public FuturePlugin<?> getPlugin() {
        return plugin;
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void send(String message) {
        wrapper.send(invoker, message);
    }

    @Override
    public void send(Component component) {
        wrapper.send(invoker, component);
    }

    @Override
    public boolean hasPermission(String permission) {
        return wrapper.hasPermission(invoker, permission);
    }

    @Override
    public boolean isConsole() {
        return isConsole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvokerImpl<?> invoker1 = (InvokerImpl<?>) o;
        return isConsole == invoker1.isConsole && Objects.equals(invoker, invoker1.invoker) && Objects.equals(uuid, invoker1.uuid) && Objects.equals(name, invoker1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoker, uuid, name, isConsole);
    }
}
