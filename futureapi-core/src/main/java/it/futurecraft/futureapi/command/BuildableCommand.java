
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

import com.mojang.brigadier.builder.ArgumentBuilder;

/**
 * A command which is buildable to Mojang's Brigadier.
 *
 * @param <I> The command invoker.
 * @param <T> The command type.
 */
public interface BuildableCommand<I, T extends ArgumentBuilder<I, T>> {
    /**
     * Create the builder for the command.
     *
     * @return The command builder.
     */
    T toBuilder();
}
