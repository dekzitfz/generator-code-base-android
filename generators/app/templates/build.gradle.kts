// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.gradlePlugin) apply false
    alias(libs.plugins.ksp) apply false
}

tasks.register("clean",Delete::class){
    delete(rootProject.layout.buildDirectory)
}