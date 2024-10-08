plugins {
    kotlin("jvm") version "1.9.21"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // RabbitMQ client
    implementation("com.rabbitmq:amqp-client:5.12.0")
    // Jackson for JSON processing
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.2.7.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    // Zookeeper
    implementation("org.apache.zookeeper:zookeeper:3.7.0")
    // Apache Kafka
    implementation("org.apache.kafka:kafka-clients:3.3.0")
    // cli
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.3")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("ch.qos.logback:logback-classic:1.2.11")
    // mockito
    testImplementation("org.mockito:mockito-core:2.24.5")
    testImplementation("org.mockito.kotlin:mockito-kotlin:2.24.5")

}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        (21)
    }
}
