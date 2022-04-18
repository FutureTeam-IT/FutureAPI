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

plugins {
    `signing`
    `java-library`
    `maven-publish`
}

allprojects {
    group = "it.futurecraft"
    version = "0.1.0"
}

subprojects {
    apply(plugin = "signing")
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.8.2")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(17))

        withJavadocJar()
        withSourcesJar()
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                artifactId = this.name

                from(components["java"])

                pom {
                    name.set("FutureAPI")
                    description.set("An API set for Minecraft Servers plugin development.")

                    url.set("https://futurecraft.it/future-api")

                    licenses {
                        license {
                            url.set("https://www.gnu.org/licenses/agpl-3.0.txt")
                            name.set("GNU Affero General Public License v3.0")
                        }
                    }

                    developers {
                        developer {
                            id.set("danieleguglietti")
                            name.set("Guglietti Daniele")
                            email.set("gugliettidaniele@gmail.com")
                        }

                        developer {
                            id.set("maxigame99")
                            name.set("Maxigame99")
                            email.set("maxi2004.ben@gmail.com")
                        }

                        developer {
                            id.set("future-team")
                            name.set("FutureTeam-IT")
                            email.set("assistenzafuturecraft@gmail.com")
                        }
                    }

                    scm {
                        connection.set("scm:git:https://github.com/FutureTeam-IT/FutureAPI.git")
                        url.set("https://github.com/FutureTeam-IT/FutureAPI")
                    }
                }
            }
        }

        repositories {
            maven {
                val snapshot = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                val release = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")

                url = if ("$version".endsWith("SNAPSHOT")) snapshot else release

                credentials {
                    username = System.getenv("MAVEN_USERNAME")
                    password = System.getenv("MAVEN_PASSWORD")
                }
            }
        }
    }

    signing {
        val key = System.getenv("SIGNING_KEY")
        val password = System.getenv("SIGNING_PASSWORD")

        useInMemoryPgpKeys(key, password)

        sign(publishing.publications["mavenJava"])
    }
}
