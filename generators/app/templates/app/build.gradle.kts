plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.google.devtools.ksp")
}

android {
    namespace = "<%= package %>"
    compileSdk = 35
    defaultConfig {
        applicationId = "<%= package %>"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
        sourceSets {
            getByName("androidTest").assets.srcDir("$projectDir/schemas")
        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    sourceSets {
        getByName("androidTest").assets.srcDirs("$projectDir/schemas")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            val proguards = fileTree("proguardrules") {
                include("*.pro")
            }
            proguardFiles(*proguards.toList().toTypedArray())
            buildConfigField("boolean", "ENABLE_CRASH_REPORT", "true")

        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            buildConfigField("boolean", "ENABLE_CRASH_REPORT", "false")
        }
    }

    flavorDimensions.add("mode")
    productFlavors {
        create("prod") {
            dimension = "mode"
            applicationId  = "<%= package %>"
            buildConfigField("String", "BASE_URL", "\"https://pokeapi.co/api/v2/\"")
        }
        create("dev") {
            dimension = "mode"
            applicationId  = "<%= package %>.dev"
            buildConfigField("String", "BASE_URL", "\"https://pokeapi.co/api/v2/\"")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.constraintLayout)
    implementation(libs.google.material)
    implementation(libs.sdp.android)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.cardview)
    implementation(libs.androidx.swipeRefreshLayout)

    //room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.rxjava2)
    testImplementation(libs.androidx.room.testing)
    androidTestImplementation(libs.androidx.room.testing)
    implementation(libs.androidx.room.paging)

    //Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.espresso)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.junit.ktx)

    //Others
    implementation(libs.easypermissions)

    //ViewModel,LiveData,Saved state module for ViewModel,Annotation processor
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    implementation(libs.androidx.lifecycle.common.java8)

    //paging library
    implementation(libs.androidx.paging)

    //timber log
    implementation(libs.timber)

    //Glide
    implementation(libs.glide)
    implementation(libs.glide.okhttp)
    ksp(libs.glide.compiler)

    //RxJava
    implementation(libs.rx.android)
    implementation(libs.rx.java)

    //retrofit & okhttp
    implementation(libs.retrofit.converter)
    implementation(libs.retrofit.adapter)
    implementation(libs.okhttp)
    implementation(libs.okio)
    implementation(libs.okhttp.logging.interceptor)

    //dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    kapt(libs.javax.annotation)
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    kapt(libs.dagger.android.processor)
}
