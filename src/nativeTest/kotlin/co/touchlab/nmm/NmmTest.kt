package co.touchlab.nmm

import kotlin.native.concurrent.AtomicReference
import kotlin.test.Test

class NmmTest {
    @Test
    fun run() {
        var c = 1
        ThreadHelper.doInBackgroundUnfrozen {
            c++
        }
    }
}

