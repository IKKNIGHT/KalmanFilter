

plugins {
    java
    `maven-publish`
    id("signing")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.knowm.xchart:xchart:3.8.4")
}



tasks.test {
    useJUnitPlatform()
}
publishing {
    publications {
        create<MavenPublication>("library") {
            groupId = "org.isaaq"
            artifactId = "kalman-filter"
            version = "1.0"
            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}

