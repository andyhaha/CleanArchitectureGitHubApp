plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
//    kotlin("plugin.serialization") version "2.0.21"
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.andy.github.home"
    compileSdk = 35

    defaultConfig {
        minSdk = 25

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
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.core)
    implementation(libs.androidx.compose.material)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
//    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    ksp(libs.squareup.moshi.codegen)
    ksp(libs.google.dagger.hilt.compiler)
    // only ksp(libs.hilt.compiler) is needed!
//    implementation(libs.dagger.hilt.compiler)
    implementation(libs.google.dagger.hilt.android)
    // room
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    // navigation
    implementation(libs.androidx.hilt.navigation.compose)
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

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.ui.test.junit4.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.google.dagger.hilt.android.testing)
    // coroutines for test
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlin.test)
    androidTestImplementation (libs.google.truth)
//    androidTestImplementation(libs.androidx.compose.ui.test)
    // compose ui test
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    // compose ui test
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    // coroutines for test
    testImplementation (libs.kotlinx.coroutines.test)
    // truth libs for test
    testImplementation (libs.google.truth)
}