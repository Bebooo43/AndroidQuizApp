package com.bebooo43.androidquizapp

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

}