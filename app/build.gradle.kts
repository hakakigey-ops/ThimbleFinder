plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.example.thimblefinder"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.thimblefinder"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "0.1-maquette"
    }
    compileOptions { sourceCompatibility = JavaVersion.VERSION_17; targetCompatibility = JavaVersion.VERSION_17 }
    kotlinOptions { jvmTarget = "17" }
}
// Volontairement aucune dépendance réseau : aucun appel à 1xBet n'est effectué.
