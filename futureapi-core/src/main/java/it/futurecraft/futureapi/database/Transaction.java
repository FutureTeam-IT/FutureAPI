package it.futurecraft.futureapi.database;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A database transaction.
 */
public interface Transaction extends Closeable {
    /**
     * Get the transaction connection.
     *
     * @return The connection.
     */
    Connection getConnection();

    /**
     * Commit transaction's changes to the database.
     *
     * @throws SQLException If an error occurs.
     */
    void commit() throws SQLException;

    /**
     * Rollback transaction's changes to the latest commit.
     *
     * @throws SQLException If an error occurs.
     */
    void rollback() throws SQLException;
}
