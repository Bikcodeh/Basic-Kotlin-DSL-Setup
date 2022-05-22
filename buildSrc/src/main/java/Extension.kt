/*
sealed class BuildType(val secret: String) {
    data class DebugBuildType(val value: String): BuildType(secret = value)
    data class ReleaseBuildType(val value: String): BuildType(secret = value)
}

fun org.gradle.language.nativeplatform.internal.BuildType.getConfiguration(): BuildType {
    return when(this.name) {
        "release" -> BuildType.ReleaseBuildType
        else -> BuildType.DebugBuildType
    }
}*/
