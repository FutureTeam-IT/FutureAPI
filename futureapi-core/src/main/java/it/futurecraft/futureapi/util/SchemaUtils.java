
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
import it.futurecraft.futureapi.database.schema.Table;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Utility class for schema management.
 * <p>You can create, delete and update schemas through the methods of this class.</p>
 * <p>You can also get the current schema version of a table.</p>
 */
public final class SchemaUtils {
    private SchemaUtils() {
        throw new IllegalAccessError("Utility class should not be instantiated.");
    }

    public static <T extends Table> T initTable(Class<T> clazz) throws Exception {
        return clazz.getConstructor().newInstance();
    }

    public static <T extends Table> void create(@NotNull Database db, @NotNull Class<T> schema) throws Throwable {
        StringBuilder query = new StringBuilder("CREATE TABLE IF NOT EXISTS " + db.getPrefix().orElse(""));
        T instance = initTable(schema);

        query.append(instance.getName()).append("(");

        AtomicInteger index = new AtomicInteger();
        instance.getColumns().forEach(column -> {
            query.append(column.getName()).append(" ").append(column.getType());

            if (!column.isNullable()) {
                query.append(" NOT NULL");
            }

            if (column.isUnique()) {
                query.append(" UNIQUE");
            }

            if (index.get() < instance.getColumns().size() - 1) {
                query.append(", ");
            }

            index.getAndIncrement();
        });

        if (instance.getPrimaryKeys().size() > 0) {
            index.set(0);

            query.append(", PRIMARY KEY(");
            instance.getPrimaryKeys().forEach(key -> {
                query.append(key);

                if (index.get() < instance.getPrimaryKeys().size() - 1) {
                    query.append(", ");
                }

                index.getAndIncrement();
            });

            query.append(")");
        }

        if (instance.getForeignKeys().size() > 0) {
            index.set(0);

            query.append(", ");
            instance.getForeignKeys().forEach(entry -> {
                query.append("FOREIGN KEY(")
                        .append(entry.getKey())
                        .append(") ")
                        .append(entry.getValue());

                if (index.get() < instance.getForeignKeys().size() - 1) {
                    query.append(", ");
                }

                index.getAndIncrement();
            });
        }

        query.append(");");

        db.withTransaction(t -> {
            try (Statement stmt = t.getConnection().createStatement()) {
                stmt.execute(query.toString());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        });
    }

    @SafeVarargs
    public static void create(@NotNull Database db, Class<? extends Table>... schemas) throws Throwable {
        for (Class<? extends Table> clazz : schemas) create(db, clazz);
    }

    public static void update(Class<? extends Table> schema) {
        // TODO: Implement
    }

    public static void delete(Class<? extends Table> schema) {
        // TODO: Implement
    }

    public static String getSchemaVersion(Class<? extends Table> schema) {
        // TODO: Implement
        return "";
    }
}
