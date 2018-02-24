package ninja.sakib.rapunzel.android.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ninja.sakib.rapunzel.android.R
import ninja.sakib.rapunzel.android.services.initGRPCConnection

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initGRPCConnection()

        startActivity(Intent(applicationContext, RegistrationActivity::class.java))
    }
}
