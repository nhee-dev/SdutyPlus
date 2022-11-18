package com.d205.sdutyplus.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.d205.data.dao.UserSharedPreference
import com.d205.sdutyplus.R
import com.d205.sdutyplus.view.login.LoginActivity
import com.d205.sdutyplus.view.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


private const val TAG = "SplashActivity"

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val loginViewModel : LoginViewModel by viewModels()
    private val userPref = UserSharedPreference(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val jwt = userPref.getStringFromPreference("jwt")
        Log.d(TAG, "jwt : $jwt")

        CoroutineScope(Dispatchers.Main).launch {
            delay(1200)
            if(isJwtAvailable()) {
                moveToMainActivity()
            }
            else {
                moveToLoginActivity()
            }
        }
    }


    private suspend fun isJwtAvailable(): Boolean {
        loginViewModel.checkJwt()
        return loginViewModel.isJwtAvailable
    }

    private fun moveToMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    private fun moveToLoginActivity() {
        val intent = Intent(this@SplashActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}