
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

println("===============================")
println("Root project:   ${project.rootProject}")
println("  rootDir:      ${project.rootDir}")
println("  projectDir:   ${project.projectDir}")
println("  absolute:   ${project.projectDir.absolutePath}")
println("  canonical:   ${project.projectDir.canonicalPath}")
println("  path:   ${project.projectDir.path}")
println("  parent:   ${project.projectDir.parent}")
println("  rootDir parent:   ${project.rootDir.parent}")
println("  relative to root:   ${project.rootDir.parent.substringAfterLast('/')}")

println(" relative path: ${project.rootDir.parent.substringAfterLast('/')}/${project.rootDir.name}/${project.projectDir.name}")

detekt {
//    basePath = "smth"
    // patch the default config file with a custom one
    config.setFrom(file("config/detekt/custom_config.yaml"))
    buildUponDefaultConfig = true

    // to support relative paths?
    // this doesn't really work to generate links to files on github...
//    basePath = rootProject.projectDir.absolutePath
//    basePath = "detekt-demo/$projectDir"
//    basePath = "${project.rootDir.parent.substringAfterLast('/')}/${project.rootDir.name}/${project.projectDir.name}"
//    basePath = "smth"
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
