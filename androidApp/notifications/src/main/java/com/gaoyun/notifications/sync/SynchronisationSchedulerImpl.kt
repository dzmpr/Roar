package com.gaoyun.notifications.sync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.gaoyun.roar.domain.SynchronisationScheduler
import com.gaoyun.roar.domain.backup.CreateBackupUseCase
import com.gaoyun.roar.network.SynchronisationApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.concurrent.TimeUnit

class SynchronisationSchedulerImpl(
    private val workManager: WorkManager,
) : SynchronisationScheduler {

    override fun scheduleSynchronisation() {
        val request = OneTimeWorkRequestBuilder<SynchronisationWorker>()
            .setInitialDelay(30, TimeUnit.SECONDS)
            .build()

        workManager.enqueueUniqueWork("sync", ExistingWorkPolicy.REPLACE, request)
    }

}

class SynchronisationWorker(
    context: Context,
    params: WorkerParameters,
    private val createBackupUseCase: CreateBackupUseCase,
    private val api: SynchronisationApi,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        return createBackupUseCase.createBackupToSync()
            .catch { Result.failure() }
            .map {
                try {
                    if (it != null) api.sendBackup(it)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                Result.success()
            }
            .first()
    }
}