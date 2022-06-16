plugins {
    kotlin("jvm") version "1.7.0"
    `maven-publish`
}

group = "com.vamsi3.algorithm"
version = "1.0.0"

kotlin {
    java {
        withSourcesJar()
    }
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    sourceSets {
        val main by getting {
            kotlin.srcDir("src/main")
        }
        val test by getting {
            kotlin.srcDir("src/test")
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/vamsi3/algorithm-library")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }
    test {
        useJUnitPlatform()
    }
}
