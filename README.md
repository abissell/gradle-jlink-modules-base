# gradle-jlink-modules-base

I wanted a simple example project with all of the following:
* JPMS based
* Multiple modules within the project, with dependencies between them
* External library dependencies which may or may not be annotated with `module-info.java`
* Builds the whole application into an optimized and well-encapsulated executable using `jlink`

I was able to pull all this together using the [badass-jlink](https://badass-jlink-plugin.beryx.org/releases/latest/) and [extra-java-module-info](https://github.com/gradlex-org/extra-java-module-info) plugins.

So that the repo can serve as a demonstration, the commit history shows the process for adding each type of dependency (including transitive deps) as a series of individual steps.
The current version of the repo is known to build successfully with Gradle 9.3.1 and Java 25. The jlink-built executable can be tested using the `test_main.sh` script.

The Gradle project was bootstrapped from the official docs' [Building Java Modules Sample](https://docs.gradle.org/current/samples/sample_java_modules_multi_project.html)
