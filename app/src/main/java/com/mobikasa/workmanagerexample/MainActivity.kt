package com.mobikasa.workmanagerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    lateinit var textview:TextView
    lateinit var textview1:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview=findViewById(R.id.status)
        textview1=findViewById(R.id.textView2)

        findViewById<Button>(R.id.button1).setOnClickListener(View.OnClickListener {
            OneTimeWorkRequest()
        })

        findViewById<Button>(R.id.periodic_work_btn).setOnClickListener(View.OnClickListener {
            PeriiodicWorkRequest()
        })



    }

    private fun PeriiodicWorkRequest()
    {
        val workmanager=WorkManager.getInstance(applicationContext)

        val constraints=Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        val data: Data =Data.Builder()
            .putInt(WorkManagertwo.Key_Count,10000)
            .build()

        val workRequesttwo=PeriodicWorkRequest.Builder(WorkManagertwo::class.java,16,TimeUnit.MINUTES)
            .setInputData(data)
            .build()

        workmanager.enqueue(workRequesttwo)

        workmanager.getWorkInfoByIdLiveData(workRequesttwo.id).observe(this, Observer { it

            textview.text=it.state.name
            Toast.makeText(this,it.state.name,Toast.LENGTH_SHORT).show()

            if(it.state.isFinished)
            {
                val data:Data=it.outputData
                val msg:String?=data.getString(WorkManagerone.Key_Worker)

                Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
            }

        })



    }

    private fun OneTimeWorkRequest()
    {
        val workmanager=WorkManager.getInstance(applicationContext)

        val data: Data =Data.Builder()
            .putInt(WorkManagerone.Key_Count,10000)
            .build()


        val constraints=Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true).build()

        val onetimeworkreques= OneTimeWorkRequest.Builder(WorkManagerone::class.java).
            setConstraints(constraints)
            .setInputData(data)
            .build()

        workmanager.enqueue(onetimeworkreques)


        workmanager.getWorkInfoByIdLiveData(onetimeworkreques.id).observe(this, Observer { it

            textview1.text=it.state.name

            if(it.state.isFinished)
            {
                val data:Data=it.outputData
                val msg:String?=data.getString(WorkManagerone.Key_Worker)

                Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
            }

        })
    }
}