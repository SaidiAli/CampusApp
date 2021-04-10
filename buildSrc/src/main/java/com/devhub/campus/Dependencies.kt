object Libs {
    object Android {
        const val coreKtx = "androidx.core:core-ktx:${Versions.core}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val material = "com.google.android.material:material:${Versions.materialVersion}"
    }

    object Firebase {
        const val Bom = "com.google.firebase:firebase-bom:${Versions.FirebaseBom}"
        const val Analytics = "com.google.firebase:firebase-analytics-ktx"
        const val Auth = "com.google.firebase:firebase-auth-ktx"
    }

    object Navigation {
        const val compose = "androidx.navigation:navigation-compose:${Versions.ComposeNav}"
    }

    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    }

    object LifeCycle {
        const val lifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    }

    object Activity {
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    }

    object Testing {
        const val junit = "junit:junit:${Versions.junit}"
        const val ext = "androidx.test.ext:junit:1.1.2"
        const val espresso = "androidx.test.espresso:espresso-core:3.3.0"
        const val compose = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    }

    object Hilt {
        const val daggerCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
//        const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.daggerHiltAndroid}"
//        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.daggerHiltAndroid}"
//        const val hiltNavigation = "androidx.hilt:hilt-navigation-fragment:${Versions.daggerHiltAndroid}"
        const val HiltNavCompose = "androidx.hilt:hilt-navigation-compose:${Versions.HiltNavCompose}"
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    }
    
}

object Config {
    const val compileSdk = 30
    const val buildToolsVersion = "30.0.3"
    const val appId = "com.devhub.campus"
    const val minSdk = 21
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val jvmTarget = "1.8"
    const val kotlinCompileVersion = Versions.kotlinCompileVersion
}

object Plugins {
    const val app = "com.android.application"
    const val kotlin = "kotlin-android"
    const val daggerHilt = "dagger.hilt.android.plugin"
    const val kotlinKapt = "kapt"
    const val firebase = "'com.google.gms.google-services"
}

object Classpaths {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinPlugin}"
    const val daggerPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val firebase = "com.google.gms:google-services:${Versions.firebase}"
}

object Versions {
    internal const val gradlePlugin  = "7.0.0-alpha12"
    internal const val kotlinPlugin = "1.4.30"
    internal const val kotlinCompileVersion = "1.4.30"
    internal const val core = "1.3.2"
    internal const val appCompat = "1.2.0"
    internal const val materialVersion = "1.2.1"
    internal const val lifecycleRuntimeKtx = "2.3.0-alpha06"
    internal const val activityCompose = "1.3.0-alpha02"
    internal const val junit = "4.+"
    internal const val hiltVersion = "2.33-beta"
    internal const val ComposeNav = "1.0.0-alpha09"
    internal const val firebase = "4.3.5"
    internal const val FirebaseBom = "26.8.0"
    internal const val HiltNavCompose = "1.0.0-alpha01"
    const val compose = "1.0.0-beta01"
}
