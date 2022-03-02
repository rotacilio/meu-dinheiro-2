package br.com.rotacilio.android.meudinheiro

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import br.com.rotacilio.android.meudinheiro.di.modules.modules

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }
}