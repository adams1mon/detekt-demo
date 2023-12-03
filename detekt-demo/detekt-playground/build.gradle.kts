
plugins {
    kotlin("jvm") version "1.9.0"
    application
    id("io.gitlab.arturbosch.detekt") version("1.23.4")
}

group = "edu.detektplayground"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal() // for the custom rule
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.10")

    // ktlint wrapper, brings the "formatting" rule set, active by default
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.4")

    // custom rule set from mavenLocal
    detektPlugins("edu.detektplayground:detekt-custom-rule:1.0-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}

detekt {
    config.setFrom(file("config/detekt/custom_config.yaml"))
    buildUponDefaultConfig = true
}

// settings for all of detekt's tasks
tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {

    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
        md.required.set(true)
    }
}
