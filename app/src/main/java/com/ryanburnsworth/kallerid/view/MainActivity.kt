package com.ryanburnsworth.kallerid.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.ryanburnsworth.kallerid.R
import com.ryanburnsworth.kallerid.model.ServiceProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val READ_PHONE_PERM = 99
    private var callerNameView: TextView? = null
    private var callerNumberView: TextView? = null
    private var phoneNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        getPermission()
        initInfoRetreival()
    }

    private fun initViews() {
        callerNameView = findViewById(R.id.callerName)
        callerNumberView = findViewById(R.id.callerNumber)
    }

    private fun initInfoRetreival() {
        if (intent.extras != null) {
            phoneNumber = intent.extras?.getString("PHONENUMBER").toString()
            if (phoneNumber.equals("")) finish() else getCallerInfo(phoneNumber)
        }
    }

    private fun getCallerInfo(number: String) {
        val serviceProvider = ServiceProvider
                .getServiceProvider()
                .getCallerInfo(number)

        serviceProvider.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    var name = result.belongs_to.get(0).name // we only care about the first name in the list
                    if (name.equals("")) name = resources.getString(R.string.error_retreiving)

                    callerNameView?.setText(name)
                    callerNumberView?.setText(phoneNumber)
                },
                        { error ->
                            callerNameView?.setText("")
                            callerNumberView?.setText(phoneNumber)
                        })
    }

    private fun getPermission() {
        val permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_PHONE_STATE)

        if (permission != PackageManager.PERMISSION_GRANTED)
            requestPermission()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                READ_PHONE_PERM)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            READ_PHONE_PERM -> {
                if ((grantResults.isEmpty() || grantResults[0] == PackageManager.PERMISSION_DENIED))
                    Toast.makeText(this, R.string.error_permission, Toast.LENGTH_SHORT).show()

                return
            }
        }
    }
}