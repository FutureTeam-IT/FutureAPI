
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

    public static <T extends Table<?>> T initTable(Class<T> clazz) throws Exception {
        return clazz.getConstructor().newInstance();
    }

    /**
     * Create the schema of the table without skipping foreign keys.
     * @param db The db where to create the schemas in.
     * @param schema The schemas to create.
     * @param <T> The table type.
     * @throws Throwable If any SQL Error occurs.
     */
    public static <T extends Table<?>> void create(@NotNull Database db, @NotNull Class<T> schema) throws Throwable {
        create(db, schema, false);
    }

    /**
     * Create the schema of the table.
     * @param db The db where to create the schemas in.
     * @param schema The schemas to create.
     * @param skipReferences Whether it should skip the creation of foreign keys.
     * @param <T> The table type.
     * @throws Throwable If any SQL Error occurs.
     */
    public static <T extends Table<?>> void create(@NotNull Database db, @NotNull Class<T> schema, boolean skipReferences) throws Throwable {
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

        if (!skipReferences) {
            instance.getForeignKeys().forEach(entry -> query.append(", CONSTRAINT FK_")
                    .append(StringUtils.toPascalCase(entry.getKey()))
                    .append(" FOREIGN KEY(")
                    .append(entry.getKey())
                    .append(") ")
                    .append(entry.getValue())
            );
        }

        query.append(");");

        db.withTransaction(t -> {
            try (Statement stmt = t.getConnection().createStatement()) {
                stmt.execute(query.toString());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }).get();
    }

    /**
     * Create the schema of multiple tables.
     *
     * @param db The db where to create the schemas in.
     * @param schemas The schemas to create.
     * @throws Throwable if any SQL Error occurs.
     */
    @SafeVarargs
    public static void create(@NotNull Database db, Class<? extends Table>... schemas) throws Throwable {
        for (Class<? extends Table> clazz : schemas) create(db, clazz, true);

        StringBuilder query = new StringBuilder();
        for (Class<? extends Table<?>> clazz : schemas) {
            Table<?> instance = initTable(clazz);

            instance.getForeignKeys().forEach(entry -> {
                query.append("ALTER TABLE ")
                        .append(instance.getName())
                        .append(" ADD CONSTRAINT Fk_")
                        .append(StringUtils.toPascalCase(entry.getKey()))
                        .append(" FOREIGN KEY(")
                        .append(entry.getKey())
                        .append(") ")
                        .append(entry.getValue())
                        .append(";\n");
            });
        }

        db.withTransaction(t -> {
            try (Statement stmt = t.getConnection().createStatement()) {
                for (String alter : query.toString().split("\\n")) {
                    stmt.executeUpdate(alter);
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }).get();
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
