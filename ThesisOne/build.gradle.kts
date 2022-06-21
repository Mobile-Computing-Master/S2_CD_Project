group = "fh.thesis"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {

}

jib {
    from {
        image = "eclipse-temurin:17-jre-alpine"
    }
    to {
        image = "v3ro/thesis-one"
    }
    container {
        creationTime = "USE_CURRENT_TIMESTAMP"
        args = listOf("--spring.profiles.active=prod")
        ports = listOf("5001")
        format = com.google.cloud.tools.jib.api.buildplan.ImageFormat.OCI
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}