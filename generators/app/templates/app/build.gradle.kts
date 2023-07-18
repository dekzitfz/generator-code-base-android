@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "<%= package %>"
    compileSdk = 33
    buildToolsVersion = "33.0.0"
    defaultConfig {
        applicationId = "<%= package %>"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
            }
        }
    }

    buildFeatures {
        viewBinding = true
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

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.9.0")
    implementation("com.intuit.sdp:sdp-android:1.0.4")
    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //room
    implementation("androidx.room:room-runtime:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")
    implementation("androidx.room:room-ktx:2.4.3")
    implementation("androidx.room:room-rxjava2:2.4.3")
    testImplementation("androidx.room:room-testing:2.4.3")
    androidTestImplementation("androidx.room:room-testing:2.4.3")

    //Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.5")

    //Others
    debugImplementation("com.github.amitshekhariitbhu.Android-Debug-Database:debug-db:v1.0.6")
    implementation("pub.devrel:easypermissions:3.0.0")

    //ViewModel,LiveData,Saved state module for ViewModel,Annotation processor
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.6.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.6.1")

    //paging library
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")

    //timber log
    implementation("com.jakewharton.timber:timber:5.0.1")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.11.0")
    implementation("com.github.bumptech.glide:okhttp3-integration:4.11.0")
    kapt("com.github.bumptech.glide:compiler:4.11.0")

    //RxJava
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.9")

    //retrofit & okhttp
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.squareup.okio:okio:2.8.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    //dagger
    implementation("com.google.dagger:dagger:2.37")
    kapt("com.google.dagger:dagger-compiler:2.37")
    kapt("org.glassfish:javax.annotation:10.0-b28")
    implementation("com.google.dagger:dagger-android:2.37")
    implementation("com.google.dagger:dagger-android-support:2.37")
    kapt("com.google.dagger:dagger-android-processor:2.37")
}
