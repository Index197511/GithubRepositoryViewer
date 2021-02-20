plugins {
    id("com.android.application")
    kotlin("android")
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.2")

    // Androidx
    val androidxCoreVersion = "1.3.2"
    val androidxAppCompatVersion = "1.2.0"
    val androidxLifeCycleVersion = "2.3.0-rc01"
    val androidxFragmentVersion = "1.3.0-rc01"
    implementation("androidx.core:core-ktx:$androidxCoreVersion")
    implementation("androidx.appcompat:appcompat:$androidxAppCompatVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$androidxLifeCycleVersion")
    implementation("androidx.fragment:fragment-ktx:$androidxFragmentVersion")

    // Navigation
    val navigationVersion = "2.3.2"
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")

    // Jetpack Compose
    val composeVersion = "1.0.0-alpha10"
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling:$composeVersion")

    // Glide
    val glideVersion = "4.11.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")

    val koinVersion = "3.0.0-alpha-4"
    implementation("org.koin:koin-core:$koinVersion")
    implementation("org.koin:koin-android:$koinVersion")
    implementation("org.koin:koin-android-viewmodel:$koinVersion")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "me.index197511.githubrepositoryviewer.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-alpha10"
        kotlinCompilerVersion = "1.4.21"
    }
}