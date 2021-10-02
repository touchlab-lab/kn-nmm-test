package co.touchlab.nmm

import kotlin.native.concurrent.AtomicInt
import kotlin.test.Test

class NmmTest {
//    @Test
    fun runFail() {
        var c = 1
        ThreadHelper.doInBackgroundUnfrozen {
            c++
        }
    }

    @Test
    fun run() {
        val c = AtomicInt(1)
        ThreadHelper.doInBackgroundUnfrozen {
            c.value++
        }
    }
}

