version = "0.1.0"

repositories {
    maven("https://nexus.velocitypowered.com/repository/maven-public/")
}

dependencies {
    api(project(":future-api"))

    compileOnly("com.velocitypowered:velocity-api:3.0.1")
    annotationProcessor("com.velocitypowered:velocity-api:3.0.1")
}
