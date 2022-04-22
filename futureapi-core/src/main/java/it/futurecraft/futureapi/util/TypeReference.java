
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

package it.futurecraft.futureapi.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Base class used to obtain full generic type information by subclassing.
 *
 * @param <T> The type of the subclass.
 */
public abstract class TypeReference<T> {
    private final Type type;

    protected TypeReference() {
        Type superClass = getClass().getGenericSuperclass();

        if (superClass instanceof Class<?>) {
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information.");
        }

        this.type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    /**
     * Get the type of the generic class.
     *
     * @return The type.
     */
    public Type getType() {
        return type;
    }
}
