package com.shivam.demo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.google.gson.JsonObject
import com.shivam.demo.constants.Constants
import com.shivam.demo.dao.ReportDao
import com.shivam.demo.fragment.HomeFragment
import com.shivam.demo.fragment.ViewListFragment
import com.shivam.demo.utils.IFragmentChangeListener

class HomeActivity : AppCompatActivity(), IFragmentChangeListener {


    private var currentFragment: Fragment? = null
    private var fragmentCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Dashboard"
        pushFragment(Constants.FRAGMENT_HOME, null)

    }

    override fun pushFragment(fragmentId: Int, data: Bundle?) {
        when (fragmentId) {
            Constants.FRAGMENT_HOME -> {
                currentFragment = HomeFragment.newInstance(data)

            }
            Constants.FRAGMENT_ADD_RECORD -> {
                currentFragment = ViewListFragment.newInstance(data)
            }
        }

        if (currentFragment != null) {
            fragmentCount++
            supportFragmentManager.beginTransaction()
                .addToBackStack(fragmentCount.toString())
                .replace(R.id.frameLayout, currentFragment!!, fragmentCount.toString())
                .commit()
        }

    }

    override fun popFragment() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
            fragmentCount--
            currentFragment = supportFragmentManager.findFragmentByTag(fragmentCount.toString())

        } else
            finish()
    }

    override fun onBackPressed() {
        if(currentFragment is ViewListFragment) {
            var reportList: MutableList<ReportDao>? = (currentFragment as ViewListFragment).dataList
            popFragment()
            if (currentFragment != null && currentFragment is ViewListFragment) {
                (currentFragment as ViewListFragment).resetData(reportList)
            }
        }else{
            popFragment()
        }
    }




}
