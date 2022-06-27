
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

/**
 * String utility class.
 */
public final class StringUtils {
    private StringUtils() {
    }

    /**
     * Convert a string into snake case.
     *
     * @param string The string to convert.
     * @return The converted string.
     */
    public static String toSnakeCase(String string) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (Character.isUpperCase(c) && i != 0) {
                stringBuilder.append('_');
            }

            stringBuilder.append(Character.toLowerCase(c));
        }

        return stringBuilder.toString();
    }

    public static String toPascalCase(String string) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (i == 0) {
                stringBuilder.append(Character.toUpperCase(c));
                continue;
            }

            if (c == '_') {
                c = string.charAt(++i);
                stringBuilder.append(Character.toUpperCase(c));
                continue;
            }

            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }
}
