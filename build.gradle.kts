import java.util.*

plugins {
    id("java")
    id("maven-publish")
}

group = "moe.caramel"
version = "1.0-SNAPSHOT"

val javaVersion = 17
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
    withSourcesJar()
    withJavadocJar()
}

/* Dependencies */
repositories {
    mavenCentral()
}

dependencies {
    implementation("javax.annotation", "javax.annotation-api", "1.3.2")
    implementation("org.jetbrains", "annotations", "24.1.0")
}

/* Tasks */
tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(javaVersion)
    }
    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }
    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }
}

/* Publish */
val isSnapshot = project.version.toString().endsWith("-SNAPSHOT")
configure<PublishingExtension> {
    repositories.maven {
        name = "maven"
        url = uri("https://repo.caramel.moe/repository/maven-" + (if (isSnapshot) "snapshots" else "releases") + "/")
        credentials {
            username = System.getenv("DEPLOY_ID")
            password = System.getenv("DEPLOY_PW")
        }
    }

    publications.create<MavenPublication>("maven") {
        artifactId = project.name.lowercase(Locale.ENGLISH)
        from(components["java"])
    }
}
