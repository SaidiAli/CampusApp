// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val compose_version by extra("1.0.0-beta01")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Classpaths.androidGradlePlugin)
        classpath(Classpaths.kotlinGradlePlugin)
        classpath(Classpaths.daggerPlugin)
        classpath(Classpaths.firebase)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}