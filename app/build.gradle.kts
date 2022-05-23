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

        getByName("debug") {
            val apiKeyDebug = properties.getProperty("MAPS_API_KEY_DEBUG")
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            isMinifyEnabled = false
            isDebuggable = true
            buildConfigField(
                type = "String",
                name = "MAPS_API_KEY",
                value =  apiKeyDebug
            )
            manifestPlaceholders["API_KEY"] = apiKeyDebug
        }

        getByName("release") {
            val apiKeyRelease = properties.getProperty("MAPS_API_KEY")
            versionNameSuffix = "-full"
            applicationIdSuffix = ".prod"

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
            manifestPlaceholders.putAll(mapOf(
                "API_KEY" to apiKeyRelease
            ))
        }

        applicationVariants.all {
            val variant = this
            variant.outputs.map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
                .forEach { output ->
                    val outputFileName = "googleMaps-${variant.versionName}-${variant.baseName}.apk"
                    output.outputFileName = outputFileName
                }
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