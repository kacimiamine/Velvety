pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

if (!file(".git").exists()) {
    val errorText = """

        =====================[ ERROR ]=====================
         The Velvety project directory is not a properly cloned Git repository.

         In order to build Velvety from source you must clone
         the Velvety repository using Git, not download a code
         zip from GitHub.
        ===================================================
    """.trimIndent()
    error(errorText)
}

rootProject.name = "velvety"

for (name in listOf("velvety-api", "velvety-server", "velvety-checkstyle")) {
    include(name)
    file(name).mkdirs()
}

optionalInclude("test-plugin")

fun optionalInclude(name: String, op: (ProjectDescriptor.() -> Unit)? = null) {
    val settingsFile = file("$name.settings.gradle.kts")
    if (settingsFile.exists()) {
        apply(from = settingsFile)
        findProject(":$name")?.let { op?.invoke(it) }
    } else {
        settingsFile.writeText(
            """
            // Uncomment to enable the '$name' project
            // include(":$name")

            """.trimIndent()
        )
    }
}

gradle.lifecycle.beforeProject {
    val mcVersion = providers.gradleProperty("mcVersion").get().trim()
    val velvetyVersionChannel = providers.gradleProperty("channel").get().trim()
    val velvetyBuildNumber = providers.environmentVariable("BUILD_NUMBER").orNull?.trim()?.toInt()
    val versionString = if (velvetyBuildNumber == null) {
        "$mcVersion.local-SNAPSHOT"
    } else {
        "$mcVersion.build.$velvetyBuildNumber-${velvetyVersionChannel.lowercase()}"
    }
    version = versionString
}
