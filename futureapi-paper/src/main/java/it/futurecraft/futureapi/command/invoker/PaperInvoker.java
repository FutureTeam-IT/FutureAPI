
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
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PaperInvoker<P extends FuturePlugin<?>> extends InvokerWrapper<P, CommandSender> {
    public PaperInvoker(P plugin) {
        super(plugin);
    }

    @Override
    public UUID getUniqueId(CommandSender invoker) {
        if (invoker instanceof Player p) {
            return p.getUniqueId();
        }

        return Invoker.CONSOLE_INVOKER_UUID;
    }

    @Override
    public String getName(CommandSender invoker) {
        if (invoker instanceof Player p) {
            return p.getName();
        }

        return Invoker.CONSOLE_INVOKER_NAME;
    }

    @Override
    public void send(CommandSender invoker, String message) {
        invoker.sendMessage(message);
    }

    @Override
    public void send(CommandSender invoker, Component component) {
        invoker.sendMessage(component);
    }

    @Override
    public boolean hasPermission(CommandSender invoker, String permission) {
        return invoker.hasPermission(permission);
    }

    @Override
    public boolean isConsole(CommandSender invoker) {
        return invoker instanceof ConsoleCommandSender;
    }
}
