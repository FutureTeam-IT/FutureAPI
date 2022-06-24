
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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Iterator;


/**
 * Utility class for schema management.
 * <p>You can create, delete and update schemas through the methods of this class.</p>
 * <p>You can also get the current schema version of a table.</p>
 */
public final class SchemaUtils {
    // TODO: Implements versioning logic.

    private SchemaUtils() {
        throw new IllegalAccessError("Utility class should not be instantiated.");
    }

    private static <T extends Table> T initTable(Class<T> clazz) throws Exception {
        return clazz.getConstructor().newInstance();
    }

    public static <T extends Table> void create(Database db, Class<T> clazz) throws Throwable {
        StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS " + db.getPrefix().orElse(""));
        T instance = initTable(clazz);

        // Set the table name
        query.append(instance.getName());

        // Initialize every column
        for (Column<?> column : instance.getColumns()) {
            query.append(column.getName()); // Set column name

            // Checks whether the column should never be null
            if (!column.isNullable()) {
                query.append(" NOT NULL");
            }

            // Checks whether the column value's unique
            if (column.isUnique()) {
                query.append(" UNIQUE");
            }

            query.append(", ");
        }

        query.append("PRIMARY KEY(");

        int columnIndex = 0;
        for (String column : instance.getPrimaryKeys()) {
            query.append(column);

            if (columnIndex < instance.getPrimaryKeys().size() - 1) {
                query.append(", ");
            }
        }

        query.append("));");


        db.withTransaction(t -> {
            try (Statement stmt = t.getConnection().createStatement()) {
                stmt.execute(query.toString());
            } catch (SQLException ignored) {
            }

            return null;
        });
    }

    public static void create(Database db, Class<? extends Table>... classes) throws Throwable {
        for (Class<? extends Table> clazz : classes) {
            create(db, clazz);
        }
    }

    public static void update(Class<? extends Table> table) {
    }

    public static void delete(Class<? extends Table> table) {
    }

    public static String getSchemaVersion(Class<? extends Table> table) {
        return "";
    }
}
