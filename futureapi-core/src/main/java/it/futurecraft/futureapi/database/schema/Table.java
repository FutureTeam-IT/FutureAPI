
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

package it.futurecraft.futureapi.database.schema;

import it.futurecraft.futureapi.database.entity.Entity;
import it.futurecraft.futureapi.database.schema.annotations.Named;
import it.futurecraft.futureapi.database.schema.types.*;
import it.futurecraft.futureapi.database.schema.types.Date;
import it.futurecraft.futureapi.util.SchemaUtils;
import it.futurecraft.futureapi.util.StringUtils;
import it.futurecraft.futureapi.util.TypeReference;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;

/**
 * A table in the database.
 * <p>The default name of the table is the class name of the model in camel case.</p>
 * <p>If you need a custom name, you can use the {@code @Named} annotation.</p>
 *
 * @see Named
 */
public abstract class Table {
    private final String name;
    private final Set<Column<?>> columns;
    private final Set<String> primaryKeys;
    private final Map<String, Reference<?, ?>> foreignKeys;

    public Table() {
        this.columns = new HashSet<>();
        this.primaryKeys = new HashSet<>();
        this.foreignKeys = new HashMap<>();

        Class<? extends Table> clazz = getClass();
        if (clazz.isAnnotationPresent(Named.class)) {
            Named name = clazz.getAnnotation(Named.class);
            this.name = name.value();
        } else {
            String name = clazz.getSimpleName();
            this.name = StringUtils.toSnakeCase(name);
        }
    }

    /**
     * Get the name of the table.
     *
     * @return The name of the table.
     */
    public String getName() {
        return name;
    }

    /**
     * Get an immutable list of the columns of the table.
     *
     * @return The columns of the table.
     */
    public List<Column<?>> getColumns() {
        return columns.stream().toList();
    }

    /**
     * Get an immutable list of the primary keys of the table.
     * <p>The primary keys are the columns that are used to uniquely identify a row.</p>
     *
     * @return The primary keys of the table.
     */
    public List<String> getPrimaryKeys() {
        return primaryKeys.stream().toList();
    }

    /**
     * Get an immutable list of the foreign keys of the table.
     * Foreign keys are used to create a link to another table, by a reference to its primary key.
     *
     * @return The foreign keys of the table.
     */
    public List<Map.Entry<String, Reference<?, ?>>> getForeignKeys() {
        return foreignKeys.entrySet().stream().toList();
    }

    /**
     * Register a column in the table.
     *
     * @param column The column to register.
     * @param <T>    The type of the column.
     * @return The registered column.
     */
    @Nullable
    public <T> Column<T> register(Column<T> column) {
        columns.add(column);
        return column;
    }

    /**
     * Register a new integer column in the table.
     *
     * @param name The name of the column.
     * @return The new column.
     */
    public Column<Integer> integer(String name) {
        return register(new ColumnExtension<>(name, new Int()));
    }

    /**
     * Register a new short column in the table.
     *
     * @param name The name of the column.
     * @return The new column.
     */
    public Column<Short> smallint(String name) {
        return register(new ColumnExtension<>(name, new Small()));
    }

    /**
     * Register a new long column in the table.
     *
     * @param name The name of the column.
     * @return The new column.
     */
    public Column<Long> bigint(String name) {
        return register(new ColumnExtension<>(name, new Big()));
    }

    /**
     * Register a new double column in the table.
     *
     * @param name The name of the column.
     * @return The new column.
     */
    public Column<Double> decimal(String name) {
        return register(new ColumnExtension<>(name, new Decimal()));
    }

    /**
     * Register a new date column in the table.
     *
     * @param name The name of the column.
     * @return The new column.
     */
    public Column<LocalDate> date(String name) {
        return register(new ColumnExtension<>(name, new Date()));
    }

    /**
     * Register a new timestamp column in the table.
     *
     * @param name The name of the column.
     * @return The new column.
     */
    public Column<LocalDateTime> timestamp(String name) {
        return register(new ColumnExtension<>(name, new DateTime()));
    }

    /**
     * Register a new varchar column in the table.
     *
     * @param name   The name of the column.
     * @param length The maximum length of the string.
     * @return The new column.
     */
    public Column<String> varchar(String name, int length) {
        return register(new ColumnExtension<>(name, new VarChar(length)));
    }

    /**
     * Register a new varchar column in the table.
     *
     * @param name The name of the column.
     * @return The new column.
     */
    public Column<String> varchar(String name) {
        return register(new ColumnExtension<>(name, new VarChar()));
    }

    /**
     * Register a new char column in the table.
     *
     * @param name   The name of the column.
     * @param length The length of the string.
     * @return The new column.
     */
    public Column<String> character(String name, int length) {
        return register(new ColumnExtension<>(name, new FixedChar(length)));
    }

    /**
     * Register a new char column in the table.
     *
     * @param name The name of the column.
     * @return The new column.
     */
    public Column<String> character(String name) {
        return register(new ColumnExtension<>(name, new FixedChar()));
    }

    final class ColumnExtension<T> extends Column<T> {
        public ColumnExtension(String name, ColumnType<T> type) {
            super(name, type);
        }

        @Override
        public Column<T> primaryKey(boolean primaryKey) {
            if (primaryKey) {
                Table.this.primaryKeys.add(this.name);
            }

            return this;
        }

        @Override
        public <S extends Table> Column<T> references(Class<S> table, Function<S, Column<T>> selector) {
            try {
                S instance = SchemaUtils.initTable(table);
                Table.this.foreignKeys.put(getName(), new Reference<>(instance, selector.apply(instance)));
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            return this;
        }
    }
}
