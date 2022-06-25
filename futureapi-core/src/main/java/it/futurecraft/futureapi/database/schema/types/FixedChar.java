
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

package it.futurecraft.futureapi.database.schema.types;

import it.futurecraft.futureapi.database.schema.ColumnType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class FixedChar extends ColumnType<String> {
    private final int length;

    public FixedChar(int length) {
        super("CHAR", Types.CHAR);
        this.length = length;
    }

    public FixedChar() {
        this(1);
    }

    /**
     * Get the size of the column.
     *
     * @return The size of the column.
     */
    public int getLength() {
        return length;
    }

    @Override
    public String parse(ResultSet resultSet, int index) throws SQLException {
        return resultSet.getString(index);
    }

    @Override
    public String toString() {
        return String.format("%s(%d)", getName(), length);
    }
}
