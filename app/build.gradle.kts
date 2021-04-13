import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id(Plugins.app)
    id(Plugins.kotlin)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.daggerHilt)
    id(Plugins.firebase)
}

android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.devhhub.campus"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = Config.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
        kotlinCompilerVersion = "1.4.30"
    }
}

dependencies {

    implementation(Libs.Android.coreKtx)
    implementation(Libs.Android.appCompat)
    implementation(Libs.Android.material)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.material)
    implementation(Libs.Compose.uiTooling)
    implementation(Libs.Hilt.HiltNavCompose)
    implementation(Libs.LifeCycle.lifeCycle)
    implementation(Libs.Activity.activityCompose)
    implementation(Libs.Hilt.hiltAndroid)
    implementation(Libs.Hilt.hiltViewModel)
    implementation(Libs.Hilt.hiltNavigation)
    implementation(Libs.Navigation.compose)
    implementation(platform(Libs.Firebase.Bom))
    implementation(Libs.Firebase.Auth)
    implementation(Libs.Firebase.Analytics)
    kapt(Libs.Hilt.hiltCompiler)
    kapt(Libs.Hilt.daggerCompiler)
    testImplementation("junit:junit:4.+")
}