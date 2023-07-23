plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "com.ra"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(JavaVersion.VERSION_17.ordinal)
}
