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
