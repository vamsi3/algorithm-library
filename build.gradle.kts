plugins {
    kotlin("jvm") version "1.7.0"
}

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
