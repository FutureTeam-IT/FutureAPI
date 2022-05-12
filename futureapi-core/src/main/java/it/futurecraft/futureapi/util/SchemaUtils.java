
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

import it.futurecraft.futureapi.database.Database;
import it.futurecraft.futureapi.database.schema.Column;
import it.futurecraft.futureapi.database.schema.Table;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class for schema management.
 * <p>You can create, delete and update schemas through the methods of this class.</p>
 * <p>You can also get the current schema version of a table.</p>
 */
public final class SchemaUtils {
    // TODO: Implements versioning logic.

    private static Database db;

    private SchemaUtils() {
        throw new IllegalAccessError("Utility class should not be instantiated.");
    }

    private static Table instantiate(Class<? extends Table> table) throws Throwable {
        Constructor<? extends Table> constructor = table.getConstructor();
        return constructor.newInstance();
    }

    @SafeVarargs
    public static void create(Class<? extends Table>... tables) throws Throwable {
    }

    public static void update(Class<? extends Table> table) {
    }

    public static void delete(Class<? extends Table> table) {
    }

    public static String getSchemaVersion(Class<? extends Table> table) {
        return "";
    }
}
