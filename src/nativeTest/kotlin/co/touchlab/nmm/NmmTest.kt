package co.touchlab.nmm

import kotlin.native.concurrent.AtomicReference
import kotlin.test.Test

class NmmTest {
    @Test
    fun enumFreeze() {
        val ar = AtomicReference(BasicEnum.A)
        ar.value = BasicEnum.B
    }
}

enum class BasicEnum {
    A, B, C
}