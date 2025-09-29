plugins {
    id("net.labymod.labygradle")
    id("net.labymod.labygradle.addon")
}

val versions = providers.gradleProperty("net.labymod.minecraft-versions").get().split(";")

group = "org.example"
version = providers.environmentVariable("VERSION").getOrElse("1.1.1")

labyMod {
    defaultPackageName = "com.rappytv.deathfinder"
    addonInfo {
        namespace = "deathfinder"
        displayName = "Death Finder"
        author = "RappyTV"
        description = "This addon saves your last death point, so you can find your items again."
        minecraftVersion = "1.8<1.21.8"
        version = rootProject.version.toString()

        addon("labyswaypoints", true)
        addon("smartchat", true)
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