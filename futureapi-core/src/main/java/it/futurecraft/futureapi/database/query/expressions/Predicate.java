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

public final class Predicate<T> {
    private final String column;
    private final T value;

    private final PredicateOperator operator;

    public Predicate(String column, T value, PredicateOperator operator) {
        this.column = column;
        this.value = value;
        this.operator = operator;
    }

    /**
     * Check whether the column value is the same as the given one.
     *
     * @param column The column to check.
     * @param value  The value to compare.
     * @param <T>    The type of the column.
     * @return The predicate object.
     */
    public static <T> Predicate<T> eq(Column<T> column, T value) {
        return new Predicate<>(column.getName(), value, PredicateOperator.EQ);
    }

    /**
     * Check whether the column value is not the same as the given one.
     *
     * @param column The column to check.
     * @param value  The value to compare.
     * @param <T>    The type of the column.
     * @return The predicate object.
     */
    public static <T> Predicate<T> neq(Column<T> column, T value) {
        return new Predicate<>(column.getName(), value, PredicateOperator.NEQ);
    }

    /**
     * Check whether the column value is greater than the given one.
     *
     * @param column The column to check.
     * @param value  The value to compare.
     * @param <T>    The type of the column.
     * @return The predicate object.
     */
    public static <T> Predicate<T> gt(Column<T> column, T value) {
        return new Predicate<>(column.getName(), value, PredicateOperator.GT);
    }

    /**
     * Check whether the column value is greater than or equal to the given one.
     *
     * @param column The column to check.
     * @param value  The value to compare.
     * @param <T>    The type of the column.
     * @return The predicate object.
     */
    public static <T> Predicate<T> gte(Column<T> column, T value) {
        return new Predicate<>(column.getName(), value, PredicateOperator.GTE);
    }

    /**
     * Check whether the column value is less than the given one.
     *
     * @param column The column to check.
     * @param value  The value to compare.
     * @param <T>    The type of the column.
     * @return The predicate object.
     */
    public static <T> Predicate<T> lt(Column<T> column, T value) {
        return new Predicate<>(column.getName(), value, PredicateOperator.LT);
    }

    /**
     * Check whether the column value is less than or equal to the given one.
     *
     * @param column The column to check.
     * @param value  The value to compare.
     * @param <T>    The type of the column.
     * @return The predicate object.
     */
    public static <T> Predicate<T> lte(Column<T> column, T value) {
        return new Predicate<>(column.getName(), value, PredicateOperator.LTE);
    }

    /**
     * Check whether the column value is null.
     *
     * @param column The column to check.
     * @param <T>    The type of the column.
     * @return The predicate object.
     */
    public static <T> Predicate<T> isNull(Column<T> column) {
        return new Predicate<>(column.getName(), null, PredicateOperator.NULL);
    }

    /**
     * Check whether the column value is not null.
     *
     * @param column The column to check.
     * @param <T>    The type of the column.
     * @return The predicate object.
     */
    public static <T> Predicate<T> notNull(Column<T> column) {
        return new Predicate<>(column.getName(), null, PredicateOperator.NOTNULL);
    }

    /**
     * Get the name of the column involved in the predicate.
     *
     * @return The column name.
     */
    public String getColumn() {
        return column;
    }

    /**
     * Get the value to compare to the column.
     *
     * @return The value.
     */
    public T getValue() {
        return value;
    }

    /**
     * The operator used in the predicate.
     *
     * @return The operator.
     */
    public PredicateOperator getOperator() {
        return operator;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(column).append(" ").append(operator);

        if (operator != PredicateOperator.NULL && operator != PredicateOperator.NOTNULL) {
            sb.append(" ").append(value);
        }

        return sb.toString();
    }

    public enum PredicateOperator {
        EQ("="), NEQ("<>"), GT(">"), GTE(">="), LT("<"), LTE("<="), NULL("IS NULL"), NOTNULL("IS NOT NULL");

        public final String operator;

        PredicateOperator(String operator) {
            this.operator = operator;
        }

        @Override
        public String toString() {
            return operator;
        }
    }
}
