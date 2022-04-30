
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

package it.futurecraft.futureapi.command;

import it.futurecraft.futureapi.command.invoker.Invoker;

import java.util.function.Predicate;

/**
 * Command permission.
 */
public final class Permission implements Predicate<Invoker> {
    private final String node;
    private boolean negate;

    public Permission(String node) {
        this(node, false);
    }

    public Permission(String node, boolean negate) {
        this.node = node;
        this.negate = negate;
    }

    /**
     * Get the permission node.
     *
     * @return The permission node.
     */
    public String getNode() {
        return node;
    }

    /**
     * Get whether the permission is negated.
     *
     * @return {@code true} if the permission is negated, {@code false} otherwise.
     */
    public boolean isNegate() {
        return negate;
    }

    /**
     * Negate the permission.
     *
     * @return The negated permission.
     */
    public Permission negate() {
        negate = true;
        return this;
    }

    /**
     * Check if the invoker has this permission.
     *
     * @param invoker The invoker.
     * @return {@code true} if the invoker has the permission, {@code false} otherwise.
     */
    @Override
    public boolean test(Invoker invoker) {
        return invoker.hasPermission(this);
    }
}
