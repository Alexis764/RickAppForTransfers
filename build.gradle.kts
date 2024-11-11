// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    //Dagger hilt
    id("com.google.dagger.hilt.android") version "2.48" apply false
    //Serialization
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.20" apply false
    //Compose Compiler
    alias(libs.plugins.compose.compiler) apply false
}