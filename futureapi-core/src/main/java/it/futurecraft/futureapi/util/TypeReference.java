
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

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * Base class used to obtain full generic type information by subclassing.
 *
 * @param <T> The type of the subclass.
 */
public abstract class TypeReference<T> {
    private final Class<T> type;

    protected TypeReference() {
        if (getClass().getGenericSuperclass() instanceof Class<?>) {
            throw new IllegalArgumentException("Internal error: TypeReference constructed without actual type information.");
        }

        ParameterizedType generic = (ParameterizedType) getClass().getGenericSuperclass();
        this.type = (Class<T>) generic.getActualTypeArguments()[0];
    }

    /**
     * Get the default value of the specified type.
     *
     * @param type The type.
     * @return The default value.
     * @throws Exception If an error occurs.
     */
    public static Object getDefaultValue(Class<?> type) throws Exception {
        if (type == Boolean.class) {
            return false;
        }
        if (type == Character.class) {
            return (char) 0;
        }
        if (type == Byte.class) {
            return (byte) 0;
        }
        if (type == Short.class) {
            return (short) 0;
        }
        if (type == Integer.class) {
            return 0;
        }
        if (type == Long.class) {
            return 0L;
        }
        if (type == Float.class) {
            return 0F;
        }
        if (type == Double.class) {
            return 0D;
        }
        if (type == String.class) {
            return "";
        }
        if (type == Set.class) {
            return new HashSet<>();
        }
        if (type == List.class) {
            return new ArrayList<>();
        }
        if (type == Map.class) {
            return new LinkedHashMap<>();
        }
        if (type == Queue.class || type == Deque.class) {
            return new ArrayDeque<>();
        }
        if (type == SortedSet.class || type == NavigableSet.class) {
            return new TreeSet<>();
        }
        if (type == SortedMap.class || type == NavigableMap.class) {
            return new TreeMap<>();
        }
        if (type.isEnum()) {
            return type.getEnumConstants()[0];
        }
        if (type.isArray()) {
            return Array.newInstance(type.getComponentType(), 0);
        }
        return type.getConstructor().newInstance();
    }

    /**
     * Get the type of the generic class.
     *
     * @return The type.
     */
    public Class<T> getType() {
        return type;
    }

    /**
     * Get the default value of the referred type.
     *
     * @return The default value.
     * @throws Exception If an error occurs.
     */
    public T getDefaultValue() throws Exception {
        return (T) getDefaultValue(type);
    }
}
