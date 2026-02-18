# gradle-jlink-modules-base

I wanted a simple example project with all of the following:
* JPMS based
* Multiple modules within the project, with dependencies between them
* External library dependencies which may or may not be annotated with `module-info.java`
* Builds the whole application into an optimized and well-encapsulated executable using `jlink`

I was able to pull all this together using the [badass-jlink](https://badass-jlink-plugin.beryx.org/releases/latest/) and [extra-java-module-info](https://github.com/gradlex-org/extra-java-module-info) plugins.

So that the repo can serve as a demonstration, the commit history shows the process for adding each type of dependency (including transitive deps) as a series of individual steps.
The current version of the repo is known to build successfully with Gradle 9.3.1 and Java 25. The jlink-built executable can be tested using the `test_main.sh` script.

Following is the README from the source Gradle multi-modules project used to bootstrap this repo:

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

```
:samples-dir: /home/tcagent1/agent/work/2822076975d89a59/promote-projects/gradle/build/git-checkout/platforms/documentation/docs/build/working/samples/install/java-modules-multi-project
:gradle-version: 8.12

= Building Java Modules Sample

[.download]
- link:zips/sample_java_modules_multi_project-groovy-dsl.zip[icon:download[] Groovy DSL]
- link:zips/sample_java_modules_multi_project-kotlin-dsl.zip[icon:download[] Kotlin DSL]

NOTE: You can open this sample in an link:{userManualPath}/gradle_ides.html#gradle_ides[IDE that supports Gradle].

This sample shows how to create a multi-project containing https://www.oracle.com/corporate/features/understanding-java-9-modules.html[Java Modules].
Java Modules are a feature of Java itself, available since Java 9, that allows for better encapsulation.

In Gradle, each _source set_ containing Java sources can be turned into a module by adding a `module-info.java` file.
Typically, in a project with Java Modules like this one, the _main_ source set of a subproject represents a module.

```
src
└── main
    └── java
        └── module-info.java
```

In the `module-info.java` file you define dependencies to other modules using keywords like `requires` or `requires transitive`.
These correspond to the `implementation` and `api` dependencies defined in the Gradle build file.
In addition, a module `exports` packages that should be visible to consumers.
Other packages are not visible outside of the module.

```
module org.gradle.sample.utilities {
    requires transitive org.gradle.sample.list;
    exports org.gradle.sample.utilities;
}
```

Unit (whitebox) tests that need to access the internals of a module can be written in the traditional way by **not** adding a `module-info.java` to the test source set.
In test execution, the modules are then treated as standard Java libraries with the encapsulation deactivated.

Blackbox (e.g. integration) tests, which should also follow the encapsulation rules during test execution, can be written by turning the corresponding test sources set itself into a module by adding a `module-info.java`.
This is shown in link:sample_java_modules_multi_project_with_integration_tests.html[this extended sample].

For more information, see link:{userManualPath}/java_library_plugin.html#sec:java_library_modular[Java Module support in the Java Library Plugin],
link:{userManualPath}/application_plugin.html#sec:application_modular[Java Module support in the Application Plugin] and
link:{userManualPath}/java_testing.html#sec:java_testing_modular[testing Java Modules].
```
