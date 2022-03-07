/*
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "ComposeApp"
include ':app'
*/

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
    }
}

include(":app")
rootProject.name = "ComposeApp"
