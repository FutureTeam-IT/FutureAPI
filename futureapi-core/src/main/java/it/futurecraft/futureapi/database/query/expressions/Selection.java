
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
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * Represent a selection in a query
 */
public final class Selection {
    private final Column<?> column;

    @Nullable
    private final String alias;

    public Selection(Column<?> column, @Nullable String alias) {
        this.column = column;
        this.alias = alias;
    }

    public Selection(Column<?> column) {
        this.column = column;
        this.alias = null;
    }

    /**
     * Get the selected column.
     * @return The column
     */
    public Column<?> getColumn() {
        return column;
    }

    /**
     * Get the alias of the selected column.
     * @return The alias wrapped in an Optional.
     */
    public Optional<String> getAlias() {
        return Optional.ofNullable(alias);
    }

    @Override
    public String toString() {
        return column.getName() + (alias != null ? " AS " + alias : "");
    }
}
