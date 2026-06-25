plugins {
    id("java")
    id("maven-publish")
}

group = "cat.breadcat"
version = "1.0.2"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("cat.breadcat:toolbox:1.3.1")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            artifactId = rootProject.name;
        }
    }
}