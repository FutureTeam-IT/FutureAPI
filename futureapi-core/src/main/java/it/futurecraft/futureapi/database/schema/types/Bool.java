
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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class Bool extends ColumnType<Boolean> {

    public Bool() {
        super("BOOLEAN", Types.BOOLEAN);
    }

    @Override
    public Boolean parse(ResultSet resultSet, int index) throws SQLException {
        return resultSet.getBoolean(index);
    }

    @Override
    public void set(PreparedStatement statement, int index, Boolean value) throws SQLException {
        statement.setBoolean(index, value);
    }
}
