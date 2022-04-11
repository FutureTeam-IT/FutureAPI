include("future-api")
include("future-api-paper")
include("future-api-velocity")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

rootProject.name = "future-api-lib"
