
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

package it.futurecraft.futureapi.util.functions;

/**
 * Represents a supplier of results which throws errors.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a functional interface  * whose functional method is {@link #get()}.
 *
 * @param <R> the type of results supplied by this supplier
 */
@FunctionalInterface
public interface ThrowingSupplier<R> {
    R get() throws Exception;
}
