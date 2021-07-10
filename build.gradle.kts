allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    tasks.register<Copy>("packageDistribution") {
        dependsOn("jar")
        from("${project.rootDir}/scripts/portfolio-cli")
        from("${project.projectDir}/build/libs/") {
            into("lib/")
        }
        into("${project.rootDir}/dist")
    }
}