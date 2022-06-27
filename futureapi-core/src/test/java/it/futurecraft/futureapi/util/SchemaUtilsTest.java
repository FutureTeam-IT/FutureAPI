
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

package it.futurecraft.futureapi.util;

import it.futurecraft.futureapi.database.Database;
import it.futurecraft.futureapi.database.schema.Column;
import it.futurecraft.futureapi.database.schema.Table;
import it.futurecraft.futureapi.files.ConfigModel.DatabaseInfo;
import org.junit.jupiter.api.Test;

public class SchemaUtilsTest {
    public static Database db;

    static {
        DatabaseInfo connectionInfo = new DatabaseInfo(
                "localhost",
                3306L,
                "futureapi",
                "root",
                "EhfEHC0rZwb4C@Nn",     // It's a development db hosted in local, don't try to do shit, port is blocked.
                null
        );

        db = Database.connect(connectionInfo);
    }

    @Test
    public void testCreate() throws Throwable {
        SchemaUtils.create(db, PluginPlayer.class, Invite.class);
    }

    public static class PluginPlayer extends Table {
        Column<Integer> id = integer("id").notNull().primaryKey();
        Column<String> name = varchar("name", 16).notNull().unique();
        Column<String> uuid = character("uuid", 36).notNull().unique();

        public PluginPlayer() {
        }
    }

    public static class Invite extends Table {
        Column<Integer> referredId = integer("referred_id")
                .notNull()
                .unique()
                .primaryKey()
                .references(PluginPlayer.class, t -> t.id);

        Column<Integer> authorId = integer("author_id")
                .notNull()
                .primaryKey()
                .references(PluginPlayer.class, t -> t.id);

        public Invite() {
        }
    }
}
