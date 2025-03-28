pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()

        maven ( url = "https://mvn.shalltry.com/repository/maven-public/")
        maven( url = "https://mvn.shalltry.com/repository/game-releases/" )
        maven ( url = "https://mvn.shalltry.com/repository/ad-releases/" )

        maven(
            url= "https://jfrog.anythinktech.com/artifactory/overseas_sdk"
        )


        //Vungle
        maven(
            url= "https://s01.oss.sonatype.org/content/groups/staging/"
        )

        //Ironsource
        maven(
            url= "https://android-sdk.is.com/"
        )

        //Pangle
        maven(
            url= "https://artifact.bytedance.com/repository/pangle/"
        )

        //Mintegral
        maven(
            url= "https://dl-maven-android.mintegral.com/repository/mbridge_android_sdk_oversea/"
        )
        //------The addresses of dependent repositories have been configured.----------
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven ( url = "https://mvn.shalltry.com/repository/maven-public/")
        maven( url = "https://mvn.shalltry.com/repository/game-releases/" )
        maven ( url = "https://mvn.shalltry.com/repository/ad-releases/" )

        maven(
            url= "https://jfrog.anythinktech.com/artifactory/overseas_sdk"
        )


        //Vungle
        maven(
            url= "https://s01.oss.sonatype.org/content/groups/staging/"
        )

        //Ironsource
        maven(
            url= "https://android-sdk.is.com/"
        )

        //Pangle
        maven(
            url= "https://artifact.bytedance.com/repository/pangle/"
        )

        //Mintegral
        maven(
            url= "https://dl-maven-android.mintegral.com/repository/mbridge_android_sdk_oversea/"
        )
        //------The addresses of dependent repositories have been configured.----------
    }
}

rootProject.name = "TranssionSDK"
include(":app")
include(":transsionsdk")
