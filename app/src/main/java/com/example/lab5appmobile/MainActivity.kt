package com.example.lab5appmobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lab5appmobile.navigation.GuideApp
import com.example.lab5appmobile.ui.theme.GuideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GuideTheme {
                GuideApp(
                    appName = getString(R.string.app_name),
                    version = packageManager.getPackageInfo(packageName, 0).versionName ?: "",
                )
            }
        }
    }
}
