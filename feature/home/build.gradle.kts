plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "2.0.21"
}

android {
    namespace = "com.andy.github.home"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    ksp(libs.squareup.moshi.codegen)
    ksp(libs.hilt.compiler)
    // only ksp(libs.hilt.compiler) is needed!
//    implementation(libs.dagger.hilt.compiler)
    implementation(libs.hilt.android)
    // room
    implementation(libs.room.common)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    // navigation
    implementation(libs.hilt.navigation.compose)
    // material icons
    implementation(libs.androidx.compose.material.iconsExtended)
    // paging
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.common.android)
    implementation(libs.coil)
    implementation(libs.coil.compose)
    // navigation new version
    implementation (libs.androidx.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    // network
    implementation(project(":libs:network"))
}