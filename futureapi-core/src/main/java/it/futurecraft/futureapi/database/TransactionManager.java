package it.futurecraft.futureapi.database;

import java.sql.SQLException;
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
    Optional<Transaction> get();

    /**
     * Create a new transaction.
     * <p>If a transaction is already opened an exception is thrown.</p>
     *
     * @return The new transaction.
     */
    Transaction newTransaction() throws Exception;
}
