
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

package it.futurecraft.futureapi.database.query.expressions;

import it.futurecraft.futureapi.database.schema.Column;

/**
 * The "ORDER BY" clause of a query.
 *
 * @param column The column to order by.
 * @param type   The order to use.
 */
public record Order(Column<?> column, OrderType type) {
    /**
     * Create a new order by clause by ascending.
     *
     * @param column The column to order by.
     * @return The order by clause.
     */
    public static Order asc(Column<?> column) {
        return new Order(column, OrderType.ASC);
    }

    /**
     * Create a new order by clause by descending.
     *
     * @param column The column to order by.
     * @return The order by clause.
     */
    public static Order desc(Column<?> column) {
        return new Order(column, OrderType.DESC);
    }

    @Override
    public String toString() {
        return "ORDER BY " + column.getName() + " " + type.name();
    }

    /**
     * The type of the order by clause.
     */
    public enum OrderType {
        ASC, DESC
    }
}
