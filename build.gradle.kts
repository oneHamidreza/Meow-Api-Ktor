plugins {
    kotlin("jvm") version "1.3.61"
}

group = "com.etebarian"
version = "0.0.1.beta"

val ktor_version = "1.3.0"
val logback_version= "1.2.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation ("io.ktor:ktor-server-netty:$ktor_version")
    implementation ("ch.qos.logback:logback-classic:$logback_version")
    implementation ("io.ktor:ktor-server-core:$ktor_version")
//    implementation ("io.ktor:ktor-html-builder:$ktor_version")
    implementation ("io.ktor:ktor-client-core:$ktor_version")
    implementation ("io.ktor:ktor-client-core-jvm:$ktor_version")
    implementation ("io.ktor:ktor-client-apache:$ktor_version")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}