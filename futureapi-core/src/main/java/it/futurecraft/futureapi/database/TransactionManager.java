
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

package it.futurecraft.futureapi.database;

import java.util.Optional;

/**
 * The transaction manager.
 */
public interface TransactionManager {
    /**
     * Get the current transaction for this thread.
     *
     * @return The current transaction.
     */
    Optional<Transaction> current();

    /**
     * Create a new transaction.
     * <p>If a transaction is already opened an exception is thrown.</p>
     *
     * @return The new transaction.
     */
    Transaction newTransaction() throws Exception;
}
