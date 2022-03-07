import kotlin.collections.*

plugins {
    id("com.android.application")
    id("kotlin-android-extensions")
    kotlin("android")
    kotlin("kapt")
    id("com.github.ben-manes.versions") version "0.42.0"
}

// Disabling the generating of kotlin synthetics as we use ViewBinding
// https://www.google.com/search?q=androidExtensions+features&rlz=1C5CHFA_enCZ965CZ965&oq=androidExtensions+features&aqs=chrome..69i57j0i22i30j0i8i13i30.1685j0j7&sourceid=chrome&ie=UTF-8
androidExtensions.features = setOf("parcelize")
kapt.correctErrorTypes = true

android {
    compileSdk = 31
    defaultConfig {
        applicationId = "org.gradle.kotlin.dsl.samples.androidstudio"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures.viewBinding = true
    lintOptions.isAbortOnError = false

    val meta = "META-INF/"
    packagingOptions.jniLibs.excludes.addAll(
        setOf(
            "${meta}DEPENDENCIES", "${meta}LICENSE",
            "${meta}LICENSE.txt", "${meta}license.txt",
            "${meta}NOTICE", "${meta}NOTICE.txt", "${meta}notice.txt",
            "${meta}ASL2.0",
            "${meta}*.kotlin_module"
        )
    )

    buildTypes {
        debug {
            buildConfigField("boolean", "isDebug", true.toString())
            signingConfig = signingConfigs["debug"]
        }
        release {
            isMinifyEnabled = false
            buildConfigField("boolean", "isDebug", false.toString())
            proguardFiles.add(getDefaultProguardFile("proguard-android-optimize.txt"))
            proguardFiles.add(File("proguard-rules.pro"))
            signingConfig = signingConfigs.create("release")
        }
    }

    val defaultFlavorDimension = "type"
    flavorDimensions.add(defaultFlavorDimension)
    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            dimension = defaultFlavorDimension
        }
        create("prod") {
            applicationIdSuffix = ".prod"
            dimension = defaultFlavorDimension
        }
    }
}

dependencies {
    // -> ./gradlew dependencyUpdates to more properly check for newer versions.
    val kotlinStdLibJdk7 = "1.6.10"

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(kotlin("stdlib-jdk7", kotlinStdLibJdk7))

    implementation("androidx.core:core-ktx:1.7.0")
    // https://developer.android.com/studio/build/multidex
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")

    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}