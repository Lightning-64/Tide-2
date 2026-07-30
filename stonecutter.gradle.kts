import org.gradle.kotlin.dsl.maven

plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter active "1.21.1-fabric"

stonecutter registerChiseled tasks.register("chiseledBuild", stonecutter.chiseled) {
    group = "project"
    ofTask("build")
}

allprojects {
    repositories {
        mavenCentral()
        mavenLocal()

        maven("https://maven.neoforged.net/releases")
        maven("https://maven.fabricmc.net/")
        maven("https://maven.shedaniel.me/")
        maven("https://maven.terraformersmc.com/")
        maven("https://maven.ladysnake.org/releases")
        maven("https://maven.theillusivec4.top/")
        maven("https://cursemaven.com")
        maven("https://api.modrinth.com/maven")
        exclusiveContent {
            forRepository {
                maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
            }
            filter {
                includeGroup("software.bernie.geckolib")
                includeGroup("com.eliotlash.mclib")
            }
        }
//        exclusiveContent {
//            forRepository {
//                maven("https://api.modrinth.com/maven")
//            }
//            filter {
//                includeGroup("maven.modrinth")
//            }
//        }
    }
}