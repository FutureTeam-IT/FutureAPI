
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

import it.futurecraft.futureapi.database.query.expressions.Predicate;
import it.futurecraft.futureapi.util.Pair;

import java.util.*;

public final class Condition {
    private final Predicate<?> root;
    private final List<Pair<Predicate<?>, BooleanOperator>> nodes;

    private Condition(Predicate<?> root, List<Pair<Predicate<?>, BooleanOperator>> nodes) {
        this.root = root;
        this.nodes = nodes;
    }

    /**
     * Create a new condition builder.
     *
     * @param root The root predicate.
     * @return The condition builder.
     */
    public static Condition.Builder of(Predicate<?> root) {
        return new Condition.Builder(root);
    }

    /**
     * Get the root predicate of the condition.
     *
     * @return The root predicate.
     */
    public Predicate<?> getRoot() {
        return root;
    }

    /**
     * Get the nodes of the condition.
     *
     * @return The nodes.
     */
    public List<Pair<Predicate<?>, BooleanOperator>> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(root.toString());

        nodes.forEach(node ->  sb.append(" ")
                .append(node.getSecond())
                .append(" ")
                .append(node.getFist())
        );

        return sb.toString();
    }

    /**
     * The boolean operator that joins two predicates.
     */
    public enum BooleanOperator {
        AND, OR
    }

    /**
     * The condition builder.
     */
    public static final class Builder {
        private final Predicate<?> root;
        private final List<Pair<Predicate<?>, BooleanOperator>> nodes;

        public Builder(Predicate<?> root) {
            this.root = root;
            this.nodes = new ArrayList<>();
        }

        /**
         * Add a new node to the condition joining it through the AND operator.
         *
         * @param predicate The predicate.
         * @return The condition builder.
         */
        public Builder and(Predicate<?> predicate) {
            nodes.add(Pair.of(predicate, BooleanOperator.AND));
            return this;
        }

        /**
         * Add a new node to the condition joining it through the OR operator.
         *
         * @param predicate The predicate.
         * @return The condition builder.
         */
        public Builder or(Predicate<?> predicate) {
            nodes.add(Pair.of(predicate, BooleanOperator.OR));
            return this;
        }

        public Condition build() {
            return new Condition(root, nodes);
        }
    }
}
