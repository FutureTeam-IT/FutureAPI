
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

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.futurecraft.futureapi.files.ConfigModel;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Function;

/**
 * Plugin database connection.
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
        try (Transaction transaction = transactionManager.current().orElse(transactionManager.newTransaction())) {
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
