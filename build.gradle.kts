allprojects {
    repositories {
        jcenter()
    }
}

subprojects {
    tasks.register<Copy>("packageDistribution") {
        dependsOn("jar")
        from("${project.rootDir}/scripts/portfolio-cli")
        from("${project.projectDir}/build/libs/${project.name}.jar") {
            into("lib")
        }
        into("${project.rootDir}/dist")
    }
}