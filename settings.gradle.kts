rootProject.name = "modules-multi-project"
include("application", "list", "utilities")
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
