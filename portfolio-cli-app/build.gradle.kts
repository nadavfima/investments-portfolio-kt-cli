/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.3.72"

    // Plugin to help with slf4j config
    id("dev.jacomet.logging-capabilities") version "0.9.0"

    // Apply the application plugin to add support for building a CLI application.
    application
}

loggingCapabilities {
    enforceSlf4JSimple()
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    mavenCentral()

}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    // Use Kotlin CLI library
    implementation("org.jetbrains.kotlinx", "kotlinx-cli", "0.3.2")
    implementation("org.jetbrains.kotlinx", "kotlinx-cli-jvm", "0.3.2")

    // Use Selenium Chrome Driver
    implementation("org.seleniumhq.selenium", "selenium-chrome-driver", "3.141.59")

    // Use Webdriver Manager for Selenium
    implementation("io.github.bonigarcia", "webdrivermanager", "4.4.3")

}

application {
    // Define the main class for the application.
    mainClassName = "portfolio.cli.app.AppKt"
}

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Main-Class"] = "portfolio.cli.app.AppKt"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }) {
        exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    }
}