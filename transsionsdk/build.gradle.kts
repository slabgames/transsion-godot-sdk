plugins {
    id("com.android.library")
}

android {
    signingConfigs {
        create("release") {
        }
    }
    namespace = "com.slabgames.transsionsdk"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 35

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    compileOnly(files("libs/godot-lib.3.6.stable.release.aar")) // godot lib

    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    //Note that the artifactId value is ad.
    //noinspection UseTomlInstead
    implementation ("com.transsion.game:ad:5.5.2.0")

    //The following part lists advertising sources and versions supported by the current GameSDK version. Please use the specified version to ensure compatibility.
//    val hsver = "2.9.7.2"
//    val toponver = "6.3.27"

    //AdMob
    api (libs.adapter.admob)
    api (libs.play.services.ads)
    implementation (libs.admob.lib)

    //Vungle
    api (libs.com.anythink.sdk.adapter.vungle4)
    api (libs.com.vungle.vungle.ads2)
    api (libs.core)
    api (libs.localbroadcastmanager)
    api (libs.play.services.basement)
    api (libs.play.services.ads.identifier)
    implementation (libs.vungle)

    //UnityAds
    implementation (libs.unity)
    api (libs.adapter.unityads)
    api (libs.unity.ads)
    implementation (libs.unity.lib)


    //Ironsource
    implementation (libs.ironsource)
    api (libs.adapter.ironsource)
    api (libs.mediationsdk)
    api (libs.play.services.appset)
    api (libs.play.services.ads.identifier.v1801)
    api (libs.play.services.basement.v1810)
    implementation(libs.ironsource.lib)


    //Pangle
    implementation (libs.pangle)
    api (libs.adapter.pangle.nonchina)
    api (libs.ads.sdk)
    api (libs.play.services.ads.identifier)
    implementation(libs.pangle.lib)

    //Inmobi
    implementation (libs.inmobi)
    api (libs.adapter.inmobi)
    api (libs.inmobi.ads.kotlin)
    implementation(libs.inmobi.lib)

    //AppLovin
    implementation (libs.applovin)
    api (libs.adapter.applovin)
    api (libs.applovin.sdk)
    implementation (libs.applovin.lib)

    //Meta(facebook)
    implementation (libs.facebook)
    api (libs.adapter.facebook)
    api (libs.adapter.facebook.v63272)
    api (libs.audience.network.sdk)
    api (libs.annotation)
    implementation (libs.fan.lib)

    //Mintegral
    implementation (libs.mintegral)
    api (libs.adapter.mintegral.nonchina)
    api (libs.reward)
    api (libs.newinterstitial)
    api (libs.mbnative)
    api (libs.mbnativeadvanced)
    api (libs.mbsplash)
    api (libs.mbbanner)
    api (libs.mbbid)
    api (libs.recyclerview)
}
