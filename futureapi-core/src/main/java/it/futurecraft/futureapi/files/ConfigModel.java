
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

package it.futurecraft.futureapi.files;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The plugin configuration file model.
 * <p>
 * Every plugin need to have its own implementation to properly run.
 */
public interface ConfigModel {
    /**
     * Get the database connection information.
     *
     * @return The database connection information model.
     */
    DatabaseInfo getDatabase();

    /**
     * The database connection information model.
     */
    class DatabaseInfo {
        String hostname;
        Long port;
        String database;
        String username;
        String password;
        String tablePrefix;

        /**
         * Create the database connection URL in JDBC format.
         *
         * @return The database connection URL
         */
        public @NotNull String toURL() {
            return "jdbc:mysql://" + hostname + ":" + port + "/" + database;
        }

        /**
         * Get the database server hostname.
         *
         * @return The database server hostname.
         */
        public @NotNull String getHostname() {
            return hostname;
        }

        /**
         * Get the database server port.
         *
         * @return The database server port.
         */
        public @NotNull Long getPort() {
            return port;
        }

        /**
         * Get the database to use.
         *
         * @return The database to use.
         */
        public @NotNull String getDatabase() {
            return database;
        }

        /**
         * Get the username used to connect to the database server.
         *
         * @return The username used to connect to the database server.
         */
        public @NotNull String getUsername() {
            return username;
        }

        /**
         * Get the password used to connect to the database server.
         *
         * @return The password used to connect to the database server.
         */
        public @NotNull String getPassword() {
            return password;
        }

        /**
         * Get the table prefix to use.
         *
         * @return The table prefix to use.
         */
        public @Nullable String getTablePrefix() {
            return tablePrefix;
        }
    }
}
