version = "1.0.0-SNAPSHOT"

dependencies {
    compileOnly(project(":velvety-api"))
}

tasks.processResources {
    val props = mapOf(
        "version" to project.version,
        "apiversion" to "\"${rootProject.providers.gradleProperty("apiVersion").get()}\"",
    )
    inputs.properties(props)
    filesMatching("paper-plugin.yml") {
        expand(props)
    }
}
