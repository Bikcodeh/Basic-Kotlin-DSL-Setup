import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream
import java.io.File

plugins {
    id(Modules.Plugins.androidApplication)
    id(Modules.Plugins.kotlinAndroid)
    id(Modules.Plugins.mapsPlatform)
}

android {
    compileSdk = Modules.AndroidSdk.compile

    defaultConfig {
        applicationId = "com.bikcodeh.googlemapsdemo"
        minSdk = Modules.AndroidSdk.minSdk
        targetSdk = Modules.AndroidSdk.target
        versionCode = Modules.AndroidSdk.versionCode
        versionName = Modules.AndroidSdk.versionName

        testInstrumentationRunner = Modules.AndroidSdk.instrumentationRunner
        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {

        val properties = Properties()
        properties.load(FileInputStream(rootProject.file("config.properties")))
        //val apiKey = gradleLocalProperties(rootDir).getProperty("MAPS_API_KEY_DEBUG")

        getByName("debug") {
            val apiKeyDebug = properties.getProperty("MAPS_API_KEY_DEBUG")
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-dev"
            buildConfigField(
                type = "String",
                name = "MAPS_API_KEY",
                value =  apiKeyDebug
            )
            manifestPlaceholders["API_KEY"] = apiKeyDebug
        }

        getByName("release") {
            val apiKeyRelease = properties.getProperty("MAPS_API_KEY")
            //val apiKeyRelease = gradleLocalProperties(rootDir).getProperty("MAPS_API_KEY")

            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                type = "String",
                name = "MAPS_API_KEY",
                value = apiKeyRelease
            )
            manifestPlaceholders["API_KEY"] = apiKeyRelease
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {

    implementation(Modules.Libraries.coreKtxLib)
    implementation(Modules.Libraries.appCompatLib)
    implementation(Modules.Libraries.materialLib)
    implementation(Modules.Libraries.mapsLib)
    implementation(Modules.Libraries.constraintLayoutLib)
    testImplementation(Modules.TestLibraries.jUnitLib)
    androidTestImplementation(Modules.TestLibraries.androidxJUnitLib)
    androidTestImplementation(Modules.TestLibraries.espressoCoreLib)
}