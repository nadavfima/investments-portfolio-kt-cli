allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    tasks.register<Copy>("packageDistribution") {
        dependsOn("jar")
        from("${project.rootDir}/scripts/portfolio-cli")
        from("${project.projectDir}/build/libs/${project.name}-${project.version}.jar") {
            into("lib/${project.name}.jar")
        }
        into("${project.rootDir}/dist")
    }
}