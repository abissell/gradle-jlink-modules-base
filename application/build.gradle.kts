plugins {
    id("myproject.java-conventions")
    application
    id("org.beryx.jlink") version "3.1.1"
    id("org.gradlex.extra-java-module-info")
}

extraJavaModuleInfo {
    // This does not have to be a complete description (e.g. here 'org.apache.commons.collections' does not export anything here).
    // It only needs to be good enough to work in the context of this application we are building.
    automaticModule("commons-logging-1.2.jar", "org.apache.commons.logging")
    module("commons-cli-1.4.jar", "org.apache.commons.cli", "3.2.2") {
        exports("org.apache.commons.cli")
    }
    module("commons-beanutils-1.9.4.jar", "org.apache.commons.beanutils", "1.9.4") {
        exports("org.apache.commons.beanutils")

        requires("org.apache.commons.logging")
        requires("java.sql")
        requires("java.desktop")
    }
}

dependencies {
    implementation(project(":utilities"))
    implementation("com.google.code.gson:gson:2.11.0")      // real module
    implementation("org.apache.commons:commons-lang3:3.10") // automatic module
    implementation("commons-cli:commons-cli:1.4")           // plain library
    implementation("commons-beanutils:commons-beanutils:1.9.4") // plain library (also brings in other libraries transitively)
}

application {
    applicationDefaultJvmArgs = listOf("-ea", "--enable-preview", "-XX:+UseCompressedOops")
    mainModule = "org.gradle.sample.app"
    mainClass = "org.gradle.sample.app.Main"
}

jlink {
    addOptions("--strip-debug", "--compress", "zip-6", "--no-header-files", "--no-man-pages")
    launcher {
        name = "main"
    }
}
