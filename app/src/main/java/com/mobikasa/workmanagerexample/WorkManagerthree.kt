package com.mobikasa.workmanagerexample

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkManagerthree(mContext:Context,parameters:WorkerParameters):Worker(mContext,parameters) {
    override fun doWork(): Result {




        return Result.success()
    }


}