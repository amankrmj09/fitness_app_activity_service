plugins {
    java
    id("org.springframework.boot") version "3.5.13"
    id("io.spring.dependency-management") version "1.1.7"
}
val springCloudVersion by extra("2025.0.1")

group = "org.wallecodes"
version = "0.0.1-SNAPSHOT"
description = "activity_service"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-web")
    
    implementation("org.modelmapper:modelmapper:3.2.6")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.kafka:spring-kafka")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    
    compileOnly("org.projectlombok:lombok")
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
    
    annotationProcessor("org.projectlombok:lombok")
    
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
