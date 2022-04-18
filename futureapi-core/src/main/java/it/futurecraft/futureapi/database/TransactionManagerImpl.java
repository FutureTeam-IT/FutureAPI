
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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

final class TransactionManagerImpl implements TransactionManager {
    private final ThreadLocal<Transaction> transaction;
    private final ConnectionSupplier supplier;

    public TransactionManagerImpl(ConnectionSupplier supplier) {
        this.transaction = new ThreadLocal<>();
        this.supplier = supplier;
    }

    @Override
    public Optional<Transaction> current() {
        return Optional.ofNullable(transaction.get());
    }

    @Override
    public Transaction newTransaction() throws Exception {
        if (transaction.get() != null) {
            throw new IllegalStateException("Current thread has already started a transaction.");
        }

        Transaction transaction = new TransactionImpl(supplier.get());
        this.transaction.set(transaction);

        return transaction;
    }

    public interface ConnectionSupplier {
        Connection get() throws SQLException;
    }

    private final class TransactionImpl implements Transaction {
        private final Connection connection;

        public TransactionImpl(Connection connection) {
            this.connection = connection;
        }

        @Override
        public Connection getConnection() {
            return connection;
        }

        @Override
        public void commit() throws SQLException {
            connection.commit();
        }

        @Override
        public void rollback() throws SQLException {
            connection.rollback();
        }

        @Override
        public void close() {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                transaction.remove();
            }
        }
    }
}
