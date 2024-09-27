plugins {
    id("net.labymod.labygradle")
    id("net.labymod.labygradle.addon")
}

val versions = providers.gradleProperty("net.labymod.minecraft-versions").get().split(";")

group = "org.example"
version = providers.environmentVariable("VERSION").getOrElse("1.0.0")

labyMod {
    defaultPackageName = "com.rappytv.deathfinder" //change this to your main package name (used by all modules)
    addonInfo {
        namespace = "deathfinder"
        displayName = "Death Finder"
        author = "RappyTV"
        description = "This addon saves your last death point, so you can find your items again."
        minecraftVersion = "1.8<1.21"
        version = System.getenv().getOrDefault("VERSION", "1.0.8")

        addon("labyswaypoints", true)
    }

    minecraft {
        registerVersion(versions.toTypedArray()) {
            runs {
                getByName("client") {
                    devLogin = true
                }
            }
        }
    }
}

subprojects {
    plugins.apply("net.labymod.labygradle")
    plugins.apply("net.labymod.labygradle.addon")

    group = rootProject.group
    version = rootProject.version
}