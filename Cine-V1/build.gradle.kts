plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.9.23"
    id("org.jetbrains.dokka") version "1.9.10"
    id("app.cash.sqldelight") version "2.0.2"
    // Plugin de Koin KSP
    id("com.google.devtools.ksp") version "1.9.23-1.0.20" //"1.8.21-1.0.11"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.lighthousegames:logging:1.3.0")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    //https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-params
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.3")
    //https://mvnrepository.com/artifact/io.mockk/mockk
    testImplementation("io.mockk:mockk:1.13.5")
    implementation("org.mybatis:mybatis:3.5.15")
    // Koin, con BOM ya se instalan todas las dependencias necesarias con la versión correcta
    implementation(platform("io.insert-koin:koin-bom:3.5.6"))
    implementation("io.insert-koin:koin-core") // Core
    implementation("io.insert-koin:koin-test") // Para test y usar checkModules
    // Para las anotaciones
    implementation(platform("io.insert-koin:koin-annotations-bom:1.3.1")) // BOM
    implementation("io.insert-koin:koin-annotations") // Annotations
    ksp("io.insert-koin:koin-ksp-compiler:1.3.1") // KSP Compiler, debes poner el mismo que el de las anotaciones
    // https://mvnrepository.com/artifact/app.cash.sqldelight/runtime-jvm
    implementation("app.cash.sqldelight:sqlite-driver:2.0.2")
    implementation("com.michael-bull.kotlin-result:kotlin-result:2.0.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

sqldelight {
    databases {
        // Nombre de la base de datos
        create("AppDatabase") {
            // Paquete donde se generan las clases
            packageName.set("dev.javiercine.database")
        }
    }
}