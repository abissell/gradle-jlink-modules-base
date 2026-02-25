plugins {
    java
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

version = "1.0.2"
group = "org.gradle.sample"

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()
}

val catalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

dependencies {
    testImplementation(catalog.findLibrary("org-junit-jupiter-junit-jupiter").get())
    testRuntimeOnly(catalog.findLibrary("org-junit-platform-junit-platform-launcher").get())
}
