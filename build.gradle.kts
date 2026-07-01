plugins {
    id("java")
    id("maven-publish")
    id("me.champeau.jmh") version "0.7.2"
}

group = "cat.breadcat"
version = "1.2.0"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("cat.breadcat:toolbox:2.0.0")

    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            artifactId = rootProject.name
        }
    }
}

