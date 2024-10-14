buildscript {
    // Define properties directly within the dependencies block
    val kotlinVersion = "1.9.0"  // Ensure this version of Kotlin is compatible with all plugins
    val hiltVersion = "2.48"      // Updated Hilt version for better compatibility
    val composeVersion = "1.5.0"  // Defined for use in module-level gradle files

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.6.1") // Make sure this version is compatible with your Android Studio version
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
    }
}
