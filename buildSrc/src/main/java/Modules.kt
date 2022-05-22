object Modules {

    object Plugins {

        object Version {
            const val androidApplication = "7.1.3"
            const val androidLibrary = "7.1.3"
            const val kotlinAndroid = "1.6.20"
            const val mapsPlatform = "2.0.1"
        }

        const val androidApplication = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val kotlinAndroid = "org.jetbrains.kotlin.android"
        const val mapsPlatform = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin"
    }

    object AndroidSdk {
        const val compile = 32
        const val minSdk = 21
        const val target = 32
        const val versionCode = 1
        const val versionName = "1.0"
        const val instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    object Libraries {
        private object Versions {
            const val coreKtx = "1.7.0"
            const val appCompat = "1.4.1"
            const val material = "1.6.0"
            const val maps = "18.0.2"
            const val constraintLayout = "2.1.4"
        }
        const val coreKtxLib = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appCompatLib = "androidx.appcompat:appcompat:${Versions.appCompat}"
        const val materialLib = "com.google.android.material:material:${Versions.material}"
        const val mapsLib = "com.google.android.gms:play-services-maps:${Versions.maps}"
        const val constraintLayoutLib = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    }

    object TestLibraries {
        private object Versions {
            const val jUnit = "4.13.2"
            const val androidxJUnit = "1.1.3"
            const val espressoCore = "3.4.0"
        }

        const val jUnitLib = "junit:junit:${Versions.jUnit}"
        const val androidxJUnitLib = "androidx.test.ext:junit:${Versions.androidxJUnit}"
        const val espressoCoreLib = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    }
}