package it.futurecraft.api.files;

/**
 * The plugin configuration file model.
 * <p>
 * Every plugin need to have its own implementation to properly run.
 *
 * @since v0.1.2
 */
public interface ConfigModel {
    /**
     * Get the database connection information.
     *
     * @return The database connection information model.
     */
    Database getDatabase();

    /**
     * The database connection information model.
     *
     * @since v0.1.2
     */
    class Database {
        String hostname;
        Long port;
        String database;
        String username;
        String password;


        public String toURL() {
            return "jdbc:mysql://" + hostname + ":" + port + "/" + database;
        }

        /**
         * Get the database server hostname.
         *
         * @return The database server hostname.
         */
        public String getHostname() {
            return hostname;
        }

        /**
         * Get the database server port.
         *
         * @return The database server port.
         */
        public Long getPort() {
            return port;
        }

        /**
         * Get the database to use.
         *
         * @return The database to use.
         */
        public String getDatabase() {
            return database;
        }

        /**
         * Get the username used to connect to the database server.
         *
         * @return The username used to connect to the database server.
         */
        public String getUsername() {
            return username;
        }

        /**
         * Get the password used to connect to the database server.
         *
         * @return The password used to connect to the database server.
         */
        public String getPassword() {
            return password;
        }
    }
}
