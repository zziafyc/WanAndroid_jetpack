/**
 *
 * @author zziafyc
 * @date 2021/7/22 0022
 * @description 管理kotlin相关依赖
 */
@Suppress("SpellCheckingInspection")
object Kotlin {
    private const val kotlin_version = "1.4.32"
    const val jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"

    val coroutines = Coroutines

    object Coroutines {
        private const val coroutines_version = "1.3.7"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version"
    }
}