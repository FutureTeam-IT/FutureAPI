
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

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * A column from a table.
 *
 * @param <T> The type of the column.
 */
public abstract class Column<T> {
    protected final String name;
    private final ColumnType<T> type;

    private boolean unique;

    public Column(String name, ColumnType<T> type) {
        this.name = name;
        this.type = type;
        this.unique = false;
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
}