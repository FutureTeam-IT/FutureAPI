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

repositories {
    maven("https://libraries.minecraft.net")
}

dependencies {
    implementation("net.kyori", "adventure-api", "4.10.1")
    compileOnly("com.mojang", "brigadier", "1.0.18")
    implementation("com.moandjiezana.toml", "toml4j", "0.7.2")
    compileOnly("mysql", "mysql-connector-java", "8.0.28")
    implementation("com.zaxxer", "HikariCP", "5.0.1")

    testImplementation("mysql", "mysql-connector-java", "8.0.28")
}
