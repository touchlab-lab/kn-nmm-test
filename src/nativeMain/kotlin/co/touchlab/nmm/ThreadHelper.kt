package co.touchlab.nmm

import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.native.concurrent.freeze

object ThreadHelper {
    val worker = Worker.start()

    fun <T> doInBackgroundUnfrozen(action: () -> T): T {
        val future = worker.execute(
            TransferMode.SAFE,
            {
                if(isStrictMemoryModel)
                    action.freeze()
                else
                    action
            }, // No more freeze() call
            {
                return@execute try {
                    it()
                } catch (e: Exception) {
                    e
                }
            }
        )
        val res = future.result
        if(res is Throwable)
            throw res
        else
            return res as T
    }

    val isStrictMemoryModel: Boolean
        get() = Platform.memoryModel == MemoryModel.STRICT
}