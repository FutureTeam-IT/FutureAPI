package it.futurecraft.futureapi.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.futurecraft.futureapi.files.ConfigModel;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Function;

/**
 * Represents a database.
 */
public final class Database {
    private final HikariConfig config;
    private final HikariDataSource dataSource;
    private final TransactionManager transactionManager;
    private final String tablePrefix;

    private Database(HikariConfig config, String tablePrefix) {
        this.config = config;
        this.tablePrefix = tablePrefix;
        this.dataSource = new HikariDataSource(config);
        this.transactionManager = new TransactionManagerImpl(dataSource::getConnection);
    }

    /**
     * Creates a new connection to the database.
     *
     * @param databaseInfo The connection info to the database.
     * @return The database connection.
     */
    public static Database connect(@NotNull ConfigModel.DatabaseInfo databaseInfo) {
        return connect(databaseInfo, 15);
    }

    /**
     * Get the database connection with a defined pool size.
     *
     * @param databaseInfo The connection info to the database.
     * @param poolSize     The pool size.
     * @return The database connection.
     */
    public static Database connect(@NotNull ConfigModel.DatabaseInfo databaseInfo, int poolSize) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(databaseInfo.toURL());
        config.setUsername(databaseInfo.getUsername());
        config.setPassword(databaseInfo.getPassword());

        config.setMaximumPoolSize(poolSize);

        config.addDataSourceProperty("cachePrepStmts", true);
        config.addDataSourceProperty("prepStmtCacheSize", 250);
        config.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        config.addDataSourceProperty("useServerPrepStmts", true);
        config.addDataSourceProperty("useLocalSessionState", true);
        config.addDataSourceProperty("rewriteBatchedStatements", true);
        config.addDataSourceProperty("cacheResultSetMetadata", true);
        config.addDataSourceProperty("cacheServerConfiguration", true);
        config.addDataSourceProperty("elideSetAutoCommits", true);
        config.addDataSourceProperty("maintainTimeStats", false);

        return new Database(config, databaseInfo.getTablePrefix());
    }

    /**
     * Create a new transaction to be consumed.
     * <p>If the current thread already has a opened transaction, will be used.</p>
     *
     * @param consumer The consumer to be executed.
     * @return The transaction.
     */
    public <T> T withTransaction(Function<Transaction, T> consumer) throws Exception {
        try (Transaction transaction = transactionManager.get().orElse(transactionManager.newTransaction())) {
            return consumer.apply(transaction);
        }
    }

    /**
     * Get the database transaction manager.
     *
     * @return The transaction manager.
     */
    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    /**
     * Get the table prefix for the plugin.
     * <p>This is used to avoid conflicts with other plugins.</p>
     *
     * @return The table prefix.
     */
    public Optional<String> getTablePrefix() {
        return Optional.ofNullable(tablePrefix);
    }
}
