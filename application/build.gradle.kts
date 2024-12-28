plugins {
    id("myproject.java-conventions")
    application
    id("org.beryx.jlink") version "3.1.1"
}

dependencies {
    implementation(project(":utilities"))
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
