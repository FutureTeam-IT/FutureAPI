
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

import it.futurecraft.futureapi.util.TypeReference;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ColumnType<T> extends TypeReference<T> {
    private final String name;
    private final int code;

    public ColumnType(String name, int code) {
        this.name = name;
        this.code = code;
    }

    /**
     * Get the name of the column type.
     *
     * @return The name of the type.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the code of the column type.
     *
     * @return The code of the type.
     */
    public int getCode() {
        return code;
    }

    /**
     * Parse a result set to get the value of the column.
     *
     * @param resultSet The result set to parse.
     * @param index     The index of the column.
     * @return The value of the column.
     * @throws SQLException If an error occurs.
     */
    public abstract T parse(ResultSet resultSet, int index) throws SQLException;

    /**
     * Parse a result set to get the value of the column.
     *
     * @param resultSet The result set to parse.
     * @param label     The label of the column.
     * @return The value of the column.
     * @throws SQLException If an error occurs.
     */
    public T parse(ResultSet resultSet, String label) throws SQLException {
        int index = resultSet.findColumn(label);
        return parse(resultSet, index);
    }

    public abstract void set(PreparedStatement statement, int index, T value) throws SQLException;

    @Override
    public String toString() {
        return name;
    }
}
