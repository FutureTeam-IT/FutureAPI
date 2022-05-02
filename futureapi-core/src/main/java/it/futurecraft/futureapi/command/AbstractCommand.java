
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

package it.futurecraft.futureapi.command;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractCommand implements Command {
    private final String name;
    private final Set<Command> childrens;

    public AbstractCommand(String name) {
        this.name = name;
        this.childrens = new HashSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Command> getSubCommands() {
        return childrens.stream().toList();
    }

    @Override
    public boolean addSubCommand(Command command) {
        return childrens.add(command);
    }
}