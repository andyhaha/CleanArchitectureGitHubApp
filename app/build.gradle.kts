import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.github.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.github.app"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Custom test runner to set up Hilt dependency graph
//        testInstrumentationRunner = "com.andy.testing.AppTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        vectorDrawables {
            useSupportLibrary = true
        }

        // get API_TOKEN from local.properties
//        val localProperties = Properties()
//        val localPropertiesFile = rootProject.file("local.properties")
//        if (localPropertiesFile.exists()) {
//            localProperties.load(localPropertiesFile.inputStream())
//        }
//        val apiToken = localProperties.getProperty("API_TOKEN", "default_token")
//        buildConfigField("String", "API_TOKEN", "\"$apiToken\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
//        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.google.dagger.hilt.android.testing)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    kspTest(libs.google.dagger.hilt.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.material3.android)

    ksp(libs.google.dagger.hilt.compiler)
    // only ksp(libs.hilt.compiler) is needed!
//    implementation(libs.dagger.hilt.compiler)
    implementation(libs.google.dagger.hilt.android)
    // navigation
    implementation(libs.androidx.hilt.navigation.compose)
    // navigation new version
    implementation (libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    // network
//    implementation(project(":libs:network"))

    // Home Page
    implementation(project(":feature:home"))
    // Details Page
    implementation(project(":feature:details"))
    implementation(project(":libs:testing"))
}