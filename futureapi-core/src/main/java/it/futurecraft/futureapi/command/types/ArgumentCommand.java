
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

package it.futurecraft.futureapi.command.types;
import it.futurecraft.futureapi.command.AbstractCommand;

import java.util.concurrent.CompletableFuture;

public abstract class ArgumentCommand<I, T> extends AbstractCommand<I> {
    public ArgumentCommand(String name) {
        super(name);
    }

    /**
     * Get the suggestions list for this argument.
     *
     * @param invoker  The invoker of the command.
     * @param argument The argument represented by this command.
     * @return The suggestions list.
     */
    public abstract CompletableFuture<T> suggest(I invoker, String argument);
}
