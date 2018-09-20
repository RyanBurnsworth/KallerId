package com.ryanburnsworth.kallerid.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.ryanburnsworth.kallerid.R
import com.ryanburnsworth.kallerid.model.ServiceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    var callerNameView: TextView? = null
    var callerNumberView: TextView? = null
    var phoneNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        phoneNumber = intent.extras?.getString("phoneNumber").toString()

        callerNameView = findViewById(R.id.callerName)
        callerNumberView = findViewById(R.id.callerNumber)
        if (phoneNumber != "")
            getCallerInfo(phoneNumber)
        else
            finish()
    }

    private fun getCallerInfo(number: String) {
        val serviceProvider = ServiceProvider
                .getServiceProvider()
                .getCallerInfo(number)

        serviceProvider.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    callerNameView?.setText(result.belongs_to.get(0).name)
                    callerNumberView?.setText(phoneNumber)
                },
                        { error -> Log.e("TAG", "ERROR: " + error.message) })
    }
}