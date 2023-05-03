package io.newsystemtest.filtersapp.ui

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.newsystemtest.filtersapp.R
import io.newsystemtest.filtersapp.databinding.ActivityMainBinding

class DashBoardActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        setListeners()

    }

    fun initView() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    private fun setListeners() {



        val navView: BottomNavigationView = activityMainBinding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_dashboard)
        navView.setupWithNavController(navController)


        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            Log.e("onDestinationChanged: ", destination.label.toString());
             activityMainBinding.TitleTv.text = destination.label.toString()
        }
    }


}