plugins {
    signing
    `java-library`
    `maven-publish`
}

group = "it.futurecraft"
version = "0.3.0"

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
}

dependencies {
    implementation("net.kyori:adventure-api:4.10.1")
    implementation("com.mojang:brigadier:1.0.18")

    implementation("com.moandjiezana.toml:toml4j:0.7.2")

    compileOnly("mysql:mysql-connector-java:8.0.28")
    implementation("com.zaxxer:HikariCP:5.0.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(16))

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
            artifactId = "future-api"

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
