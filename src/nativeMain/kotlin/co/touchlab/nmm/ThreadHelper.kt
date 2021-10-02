package co.touchlab.nmm

import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.native.concurrent.freeze

object ThreadHelper {
    val worker = Worker.start()

    fun <T> doInBackgroundUnfrozen(action: () -> T): T {
        val ex = AtomicReference<Throwable?>(null)
        val future = worker.execute(
            TransferMode.SAFE,
            {
                val block = if(isStrictMemoryModel)
                    action.freeze()
                else
                    action
                Pair(block, ex)
            }, // No more freeze() call
            {
                return@execute try {
                    it.first()
                } catch (e: Exception) {
                    it.second.value = e.freeze()
                    throw e
                }
            }
        )
        return try {
            future.result
        } catch (e: Exception) {
            val thrown = ex.value
            if(thrown != null)
                throw thrown
            else
                throw e
        }
    }

    val isStrictMemoryModel: Boolean
        get() = Platform.memoryModel == MemoryModel.STRICT
}