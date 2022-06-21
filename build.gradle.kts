plugins {
    id("java")
    id("com.google.cloud.tools.jib") version("3.2.1") apply false
}

group = "fh.thesis"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

allprojects {
    apply(plugin = "java")

    dependencies {
        implementation ("com.google.guava:guava:31.1-jre")
        implementation ("org.springframework.boot:spring-boot-starter-web:2.7.0")
        implementation ("org.springframework.boot:spring-boot-starter-webflux:2.7.0")
        annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor:2.7.0")
        testImplementation ("org.springframework.boot:spring-boot-starter-test:2.7.0")
    }
}

subprojects {
    apply(plugin = "com.google.cloud.tools.jib")
}


tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.register("buildAllImages") {
    dependsOn(":ThesisOne:jibDockerBuild")
    dependsOn(":ThesisTwo:jibDockerBuild")
    dependsOn(":ThesisThree:jibDockerBuild")
    dependsOn(":ThesisFour:jibDockerBuild")
    dependsOn(":ThesisFive:jibDockerBuild")
}