include("futureapi-core")
include("futureapi-paper")
include("futureapi-velocity")

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")
    }
}

rootProject.name = "futureapi"
