import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${extra["springBootVersion"]}")
        classpath("org.jetbrains.kotlin:kotlin-allopen:${extra["kotlinVersion"]}")
    }
}

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.2.41"
    id("io.spring.dependency-management") version "1.0.5.RELEASE"
}

apply {
    plugin("kotlin-spring")
    plugin("org.springframework.boot")
}

repositories {
    mavenCentral()
    maven { setUrl("https://repo.spring.io/snapshot") }
    maven { setUrl("https://repo.spring.io/milestone") }
}

dependencies {
    compile("org.springframework.cloud:spring-cloud-starter-gateway")
    compile("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    compile("org.springframework.cloud:spring-cloud-config-client")
    compile("org.springframework.cloud:spring-cloud-starter-netflix-hystrix")
    compile("org.springframework.boot:spring-boot-starter-actuator")
    compile("org.springframework.boot:spring-boot-starter-security")
    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    testCompile("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testCompile("org.springframework.security:spring-security-test")
    testCompile("org.junit.jupiter:junit-jupiter-api")
    testRuntime("org.junit.jupiter:junit-jupiter-engine")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${extra["springCloudDependenciesVersion"]}")
    }
}

tasks {
    tasks.withType<Test> {
        useJUnitPlatform()
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
}