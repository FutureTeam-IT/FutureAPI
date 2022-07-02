
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
import it.futurecraft.futureapi.util.SchemaUtils;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

/**
 * A column from a table.
 *
 * @param <T> The type of the column.
 */
public abstract class Column<T> {
    protected final String name;
    private final ColumnType<T> type;

    private boolean unique;
    private boolean nullable;

    public Column(String name, ColumnType<T> type) {
        this.name = name;
        this.type = type;
        this.unique = false;
        this.nullable = true;
    }

    /**
     * Get the name of the column.
     *
     * @return The name of the column.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the type of the column.
     *
     * @return The type of the column.
     * @see ColumnType
     */
    public ColumnType<T> getType() {
        return type;
    }

    /**
     * Get whether the column is unique.
     *
     * @return {@code true} if the column is unique.
     */
    public boolean isUnique() {
        return unique;
    }

    /**
     * Get whether the column is nullable.
     *
     * @return {@code true} if the column is nullable.
     */
    public boolean isNullable() {
        return nullable;
    }

    /**
     * Parse a result set to get the value of the column.
     *
     * @param resultSet The result set to parse.
     * @return The value of the column.
     * @throws SQLException If an error occurs.
     */
    public T parse(ResultSet resultSet) throws SQLException {
        return type.parse(resultSet, name);
    }

    /**
     * Set the column as unique.
     *
     * @return The column.
     */
    public Column<T> unique() {
        return unique(true);
    }

    /**
     * Set whether the column is unique.
     *
     * @param unique {@code true} if the column is unique.
     * @return The column.
     */
    public Column<T> unique(boolean unique) {
        this.unique = unique;
        return this;
    }

    /**
     * Set the column as nullable.
     *
     * @return The column.
     */
    public Column<T> nullable() {
        return nullable(true);
    }

    /**
     * Add to the column the not null constraint.
     *
     * @return The column.
     */
    public Column<T> notNull() {
        return nullable(false);
    }

    /**
     * Set whether the column is nullable.
     *
     * @param nullable {@code true} if the column is nullable.
     * @return The column.
     */
    public Column<T> nullable(boolean nullable) {
        this.nullable = nullable;
        return this;
    }

    /**
     * Set the column as part of the table primaryKey.
     *
     * @return The column.
     */
    public Column<T> primaryKey() {
        return primaryKey(true);
    }

    /**
     * Set whether the column is part of the table primaryKey.
     *
     * @param primaryKey {@code true} if the column is part of the table primaryKey.
     * @return The column.
     */
    public abstract Column<T> primaryKey(boolean primaryKey);

    /**
     * Create a reference to another table.
     * Pay attention, the table referenced schema should already be created when creating this table schema.
     *
     * @param table    The table to reference.
     * @param selector The selector for the column to reference.
     * @param <S>      The table type.
     * @return The column.
     */
    public abstract <S extends Table<?>> Column<T> references(Class<S> table, Function<S, Column<T>> selector);
}
