import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.gradleup.shadow") version("8.3.1")
}

group = "dev.scarday"
version = "1.0"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/")}
    maven { url = uri("https://oss.sonatype.org/content/groups/public/")}
    maven { url = uri("https://repo.by1337.space/repository/maven-releases/")}
    maven { url = uri("https://storehouse.okaeri.eu/repository/maven-public/")}
    maven { url = uri("https://repo.panda-lang.org/releases")}
}

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")

    // -- Lombok --
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")

    // -- By1337 --
    compileOnly("dev.by1337.virtualentity.api:VirtualEntityApi-api:1.2")
    compileOnly("org.by1337.blib:Blib-api:1.5.3")

    // -- Kyori --
    implementation("net.kyori:adventure-text-minimessage:4.19.0")
    implementation("net.kyori:adventure-platform-bukkit:4.3.4")

    // -- Configuration --
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.6")
    implementation("eu.okaeri:okaeri-configs-serdes-okaeri-bukkit:5.0.6")

    // - LiteCommands -
    implementation("dev.rollczi:litecommands-bukkit:3.9.7")
}

tasks.withType<ShadowJar> {
    relocate("net.kyori", "dev.scarday.libs")
    relocate("eu.okaeri", "dev.scarday.libs")
    relocate("dev.rollczi", "dev.scarday.libs")


}