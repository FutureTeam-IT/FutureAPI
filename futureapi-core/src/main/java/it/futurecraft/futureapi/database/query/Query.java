
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

package it.futurecraft.futureapi.database.query;

import it.futurecraft.futureapi.database.Database;
import it.futurecraft.futureapi.database.query.expressions.Group;
import it.futurecraft.futureapi.database.query.expressions.Order;
import it.futurecraft.futureapi.database.query.expressions.Selection;
import it.futurecraft.futureapi.database.schema.Column;
import it.futurecraft.futureapi.database.schema.Table;
import org.jetbrains.annotations.Nullable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Represent a query in the database.
 */
public class Query {
    private final Database database;
    private final Table table;
    private final List<Selection> selections;

    @Nullable
    private final Condition where;

    @Nullable
    private final Group groupBy;

    @Nullable
    private final Order orderBy;

    private ResultSet rs;

    private Query(Database database, Table table, List<Selection> selections, @Nullable Condition where, @Nullable Group groupBy, @Nullable Order orderBy) {
        this.database = database;
        this.table = table;
        this.selections = selections;
        this.where = where;
        this.groupBy = groupBy;
        this.orderBy = orderBy;
    }

    /**
     * Get the table of the query.
     *
     * @return The table.
     */
    public Table getTable() {
        return table;
    }

    /**
     * Get the selections of the query.
     *
     * @return The selections.
     */
    public List<Selection> getSelections() {
        return selections;
    }

    /**
     * Get the where clause of the query.
     *
     * @return The where clause.
     */
    public Optional<Condition> getWhere() {
        return Optional.ofNullable(where);
    }

    /**
     * Get the group by clause of the query.
     *
     * @return The group by clause.
     */
    public Optional<Group> getGroupBy() {
        return Optional.ofNullable(groupBy);
    }

    /**
     * Get the order by clause of the query.
     *
     * @return The order by clause.
     */
    public Optional<Order> getOrderBy() {
        return Optional.ofNullable(orderBy);
    }

    /**
     * Get the result set of the query.
     *
     * @return The result set.
     */
    public ResultSet getResultSet() {
        return rs;
    }

    /**
     * Execute the query.
     *
     * @return The result set.
     */
    public CompletableFuture<Void> execute() {
        return database.withTransaction(t -> {
            try (Connection connection = t.getConnection(); Statement stmt = connection.createStatement()) {
                this.rs = stmt.executeQuery(this.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SELECT ");

        if (selections.isEmpty()) {
            sb.append("*");
        } else {
            selections.forEach(selection -> sb.append(selection).append(", "));
            sb.setLength(sb.length() - 2);
        }

        sb.append(" FROM ").append(table.getName());

        if (where != null) {
            sb.append(" WHERE ").append(where);
        }

        if (groupBy != null) {
            sb.append(" ").append(groupBy);
        }

        if (orderBy != null) {
            sb.append(" ").append(orderBy);
        }

        return sb.toString();
    }

    public static final class Builder<T extends Table> {
        private final Database database;
        private final T table;

        private final List<Selection> selections;

        private Condition where;
        private Group groupBy;
        private Order orderBy;

        public Builder(Database db, T table) {
            this.database = db;
            this.table = table;
            this.selections = new ArrayList<>();
        }

        public <C> Builder<T> select(Function<T, Column<C>> selector) {
            Selection selection = new Selection(selector.apply(table), null);
            selections.add(selection);

            return this;
        }

        public <C> Builder<T> select(Function<T, Column<C>> selector, String alias) {
            Selection selection = new Selection(selector.apply(table), alias);
            selections.add(selection);

            return this;
        }

        public Builder<T> where(Function<T, Condition.Builder> predicate) {
            Condition.Builder builder = predicate.apply(table);
            where = builder.build();

            return this;
        }

        public Builder<T> groupBy(Function<T, Column<?>> selector, Function<T, Condition.Builder> having) {
            Condition.Builder builder = having.apply(table);
            this.groupBy = new Group(selector.apply(table), builder.build());

            return this;
        }

        public Builder<T> orderBy(Function<T, Order> selector) {
            this.orderBy = selector.apply(table);

            return this;
        }

        public Query build() {
            return new Query(database, table, selections, where, groupBy, orderBy);
        }
    }
}
