group = "fh.thesis"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {

}

jib {
    from {
        image = "openjdk:17.0.2-oraclelinux8"
        platforms {
            platform {
                architecture = "arm64"
                os = "linux"
            }
        }
    }
    to {
        image = "v3ro/thesis-two"
        tags = setOf(findProperty("IMAGE_TAG").toString())
        auth {
            username = findProperty("DOCKERHUB_USERNAME").toString()
            password = findProperty("DOCKERHUB_PASSWORD").toString()
        }
    }
    container {
        creationTime = "USE_CURRENT_TIMESTAMP"
        args = listOf("--spring.profiles.active=prod")
        ports = listOf("5001")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}