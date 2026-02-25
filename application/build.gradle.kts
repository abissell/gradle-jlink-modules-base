plugins {
    id("myproject.java-conventions")
    application
    id("org.beryx.jlink") version "3.2.1"
    id("org.gradlex.extra-java-module-info")
}

dependencies {
    implementation(project(":utilities"))
    implementation(libs.com.abissell.javautil) // a modularized library
    implementation(libs.com.google.code.gson.gson)      // real module
    implementation(libs.org.apache.commons.commons.lang3) // automatic module
    implementation(libs.commons.cli.commons.cli)           // plain library
    implementation(libs.commons.beanutils.commons.beanutils) // plain library (also brings in other libraries transitively)
}

extraJavaModuleInfo {
    module("net.openhft:chronicle-core", "chronicle.core") // javautil dep
    module("net.openhft:chronicle-analytics", "chronicle.analytics") // javautil transitive dep
    module("net.openhft:posix", "net.openhft.posix") // javautil dep
    knownModule("org.slf4j:slf4j-api", "org.slf4j") // javautil transitive dep
    module("net.java.dev.jna:jna", "com.sun.jna") // javautil transitive dep
    module("net.java.dev.jna:jna-platform", "com.sun.jna.platform") // javautil transitive dep
    knownModule("org.jetbrains:annotations", "org.jetbrains.annotations") // javautil transitive dep
    module("com.github.jnr:jnr-a64asm", "jnr.a64asm") // javautil dep
    module("com.github.jnr:jnr-x86asm", "jnr.x86asm") // javautil dep
    module("com.github.jnr:jnr-ffi", "org.jnrproject.ffi") // javautil transitive dep
    knownModule("com.github.jnr:jffi", "org.jnrproject.jffi") // javautil transitive dep
    module("com.github.jnr:jnr-constants", "org.jnrproject.constants") // javautil transitive dep
    knownModule("org.ow2.asm:asm", "org.objectweb.asm") // javautil transitive dep
    knownModule("org.ow2.asm:asm-commons", "org.objectweb.asm.commons") // javautil transitive dep
    knownModule("org.ow2.asm:asm-analysis", "org.objectweb.asm.tree.analysis") // javautil transitive dep
    knownModule("org.ow2.asm:asm-tree", "org.objectweb.asm.tree") // javautil transitive dep
    knownModule("org.ow2.asm:asm-util", "org.objectweb.asm.util") // javautil transitive dep
    knownModule("com.google.errorprone:error_prone_annotations", "com.google.errorprone.annotations") // javautil transitive dep

    // This does not have to be a complete description (e.g. here 'org.apache.commons.collections' does not export anything here).
    // It only needs to be good enough to work in the context of this application we are building.
    automaticModule("commons-logging:commons-logging", "org.apache.commons.logging")
    module("commons-cli:commons-cli", "org.apache.commons.cli") {
        exports("org.apache.commons.cli")
    }
    module("commons-collections:commons-collections", "org.apache.commons.collections")
    module("commons-beanutils:commons-beanutils", "org.apache.commons.beanutils") {
        exports("org.apache.commons.beanutils")

        requires("org.apache.commons.logging")
        requires("java.sql")
        requires("java.desktop")
    }
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

