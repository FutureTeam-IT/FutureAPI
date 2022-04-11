plugins {
    id("io.papermc.paperweight.userdev") version "1.3.5"
}

version = "0.1.0"

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    api(project(":future-api"))
    paperDevBundle("1.18.2-R0.1-SNAPSHOT")
}
