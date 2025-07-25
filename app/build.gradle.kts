import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.shmryandex"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.shmryandex"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/license.txt"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/LGPL2.1"
            excludes += "META-INF/AL2.0"
        }
    }
}

dependencies {

    implementation(project(":core"))
    implementation(project(":features:accounts"))
    implementation(project(":features:expenses"))
    implementation(project(":features:incomes"))
    implementation(project(":features:history"))
    implementation(project(":features:categories"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    androidTestImplementation(libs.mockk.android)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //Dagger2
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
    //Navigation
    implementation(libs.navigation)
    //Lottie
    implementation(libs.lottie)
    //Splash
    implementation(libs.splash)
    //Network
    implementation(libs.retrofit.moshi)
    implementation(libs.retrofit.core)
    implementation(libs.moshi.kotlin)
    implementation(libs.okhttp.core)
    implementation(libs.okhttp.logging)
    ksp(libs.moshi.codegen)
    //Gson
    implementation(libs.gson)
    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    //WorkManager
    implementation(libs.work.runtime)
    //EncryptedSharedPreferences
    implementation(libs.security.crypto)
}