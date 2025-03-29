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

//    implementation(libs.appcompat)
//    implementation(libs.material)
//    testImplementation(libs.junit)
//    androidTestImplementation(libs.ext.junit)
//    androidTestImplementation(libs.espresso.core)

    //Note that the artifactId value is ad.
    //noinspection UseTomlInstead
    implementation ("com.transsion.game:ad:5.5.2.0")

    //The following part lists advertising sources and versions supported by the current GameSDK version. Please use the specified version to ensure compatibility.
    val hs_ver = "2.9.7.2"
    val topon_ver = "6.3.27"


    //AdMob
    api ("com.anythink.sdk:adapter-admob:6.3.27")
    api ("com.google.android.gms:play-services-ads:23.0.0")
    implementation ("com.cloud.hisavana.sdk.mediation:admob-lib:$hs_ver")

    //Vungle
    api ("com.anythink.sdk:adapter-vungle:$topon_ver")
    api ("com.vungle:vungle-ads:7.1.0")
    api ("androidx.core:core:1.3.2")
    api ("androidx.localbroadcastmanager:localbroadcastmanager:1.0.0")
    api ("com.google.android.gms:play-services-basement:18.1.0")
    api ("com.google.android.gms:play-services-ads-identifier:18.0.1")
    implementation ("com.google.ads.mediation:vungle:7.3.2.0")

    //UnityAds
    implementation ("com.google.ads.mediation:unity:4.11.2.0")
    api ("com.anythink.sdk:adapter-unityads:$topon_ver")
    api ("com.unity3d.ads:unity-ads:4.9.3")
    implementation ("com.cloud.hisavana.sdk.mediation:unity-lib:$hs_ver")


    //Ironsource
    implementation ("com.google.ads.mediation:ironsource:8.0.0.1")
    api ("com.anythink.sdk:adapter-ironsource:$topon_ver")
    api ("com.ironsource.sdk:mediationsdk:7.9.0")
    api ("com.google.android.gms:play-services-appset:16.0.2")
    api ("com.google.android.gms:play-services-ads-identifier:18.0.1")
    api ("com.google.android.gms:play-services-basement:18.1.0")
    implementation("com.cloud.hisavana.sdk.mediation:ironsource-lib:$hs_ver")


    //Pangle
    implementation ("com.google.ads.mediation:pangle:5.9.0.4.0")
    api ("com.anythink.sdk:adapter-pangle-nonchina:6.3.27")
    api ("com.pangle.global:ads-sdk:5.8.1.0")
    api ("com.google.android.gms:play-services-ads-identifier:18.0.1")
    implementation("com.cloud.hisavana.sdk.mediation:pangle-lib:$hs_ver")

    //Inmobi
    implementation ("com.google.ads.mediation:inmobi:10.6.7.1")
    api ("com.anythink.sdk:adapter-inmobi:$topon_ver")
    api ("com.inmobi.monetization:inmobi-ads-kotlin:10.6.7")
    implementation("com.cloud.hisavana.sdk.mediation:inmobi-lib:$hs_ver")

    //AppLovin
    implementation ("com.google.ads.mediation:applovin:12.4.3.0")
    api ("com.anythink.sdk:adapter-applovin:6.3.27")
    api ("com.applovin:applovin-sdk:12.3.0")
    implementation ("com.cloud.hisavana.sdk.mediation:applovin-lib:$hs_ver")

    //Meta(facebook)
    implementation ("com.google.ads.mediation:facebook:6.17.0.0")
    api ("com.anythink.sdk:adapter-facebook:$topon_ver")
    api ("com.anythink.sdk:adapter-facebook:6.3.27.2")
    api ("com.facebook.android:audience-network-sdk:6.17.0")
    api ("androidx.annotation:annotation:1.0.0")
    implementation ("com.cloud.hisavana.sdk.mediation:fan-lib:$hs_ver")

    //Mintegral
    implementation ("com.google.ads.mediation:mintegral:16.7.21.0")
    api ("com.anythink.sdk:adapter-mintegral-nonchina:$topon_ver")
    api ("com.mbridge.msdk.oversea:reward:16.6.51")
    api ("com.mbridge.msdk.oversea:newinterstitial:16.6.51")
    api ("com.mbridge.msdk.oversea:mbnative:16.6.51")
    api ("com.mbridge.msdk.oversea:mbnativeadvanced:16.6.51")
    api ("com.mbridge.msdk.oversea:mbsplash:16.6.51")
    api ("com.mbridge.msdk.oversea:mbbanner:16.6.51")
    api ("com.mbridge.msdk.oversea:mbbid:16.6.51")
    api ("androidx.recyclerview:recyclerview:1.1.0")
    implementation ("com.cloud.hisavana.sdk.mediation:mintegral-lib:$hs_ver")

    //AdMob
//    api (libs.adapter.admob)
//    api (libs.play.services.ads)
//    implementation (libs.admob.lib)
//
//    //Vungle
//    api (libs.com.anythink.sdk.adapter.vungle4)
//    api (libs.com.vungle.vungle.ads2)
//    api (libs.core)
//    api (libs.localbroadcastmanager)
//    api (libs.play.services.basement)
//    api (libs.play.services.ads.identifier)
//    implementation (libs.vungle)
//
//    //UnityAds
//    implementation (libs.unity)
//    api (libs.adapter.unityads)
//    api (libs.unity.ads)
//    implementation (libs.unity.lib)
//
//
//    //Ironsource
//    implementation (libs.ironsource)
//    api (libs.adapter.ironsource)
//    api (libs.mediationsdk)
//    api (libs.play.services.appset)
//    api (libs.play.services.ads.identifier.v1801)
//    api (libs.play.services.basement.v1810)
//    implementation(libs.ironsource.lib)
//
//
//    //Pangle
//    implementation (libs.pangle)
//    api (libs.adapter.pangle.nonchina)
//    api (libs.ads.sdk)
//    api (libs.play.services.ads.identifier)
//    implementation(libs.pangle.lib)
//
//    //Inmobi
//    implementation (libs.inmobi)
//    api (libs.adapter.inmobi)
//    api (libs.inmobi.ads.kotlin)
//    implementation(libs.inmobi.lib)
//
//    //AppLovin
//    implementation (libs.applovin)
//    api (libs.adapter.applovin)
//    api (libs.applovin.sdk)
//    implementation (libs.applovin.lib)
//
//    //Meta(facebook)
//    implementation (libs.facebook)
//    api (libs.adapter.facebook)
//    api (libs.adapter.facebook.v63272)
//    api (libs.audience.network.sdk)
//    api (libs.annotation)
//    implementation (libs.fan.lib)
//
//    //Mintegral
//    implementation (libs.mintegral)
//    api (libs.adapter.mintegral.nonchina)
//    api (libs.reward)
//    api (libs.newinterstitial)
//    api (libs.mbnative)
//    api (libs.mbnativeadvanced)
//    api (libs.mbsplash)
//    api (libs.mbbanner)
//    api (libs.mbbid)
//    api (libs.recyclerview)
}
