package com.mobikasa.workmanagerexample

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class WorkManagertwo(mContext:Context,parameters:WorkerParameters):Worker(mContext,parameters) {
    val TAG = "MyWorkManager"

    companion object {

        const val Key_Worker = "Key_Worker"
        const val Key_Count = "Key_Count"

        fun showToast(msg: String, mCOntext: Context) {
            Toast.makeText(mCOntext, msg, Toast.LENGTH_SHORT).show()
        }

    }

    override fun doWork(): Result {

        val count: Int = inputData.getInt(Key_Count, 0)

        try {

            for (i: Int in 0 until count) {

                Log.d(TAG, "Count  $i")


            }

            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentdate: String = time.format(Date())

            val outputData: Data = Data.Builder()
                .putString(Key_Worker, currentdate)
                .build()

            return Result.success(outputData)


        } catch (e: Exception) {
            return Result.failure()
        }


    }
}