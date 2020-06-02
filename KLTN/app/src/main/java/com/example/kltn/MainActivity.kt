package com.example.kltn;

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.example.kltn.screen.cart.CartFragment
import com.example.kltn.screen.home.HomeFragment
import com.example.kltn.screen.home.`interface`.SendData
import com.example.kltn.screen.notification.NotificationFragment
import com.example.kltn.screen.profile.ProfileFragment
import com.example.kltn.screen.suggest.SuggestFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(),SendData{
    private var homeFragment: HomeFragment? = null
    private var profileFragment: ProfileFragment? = null
    private var notificationFragment: NotificationFragment? = null
    private var cartFragment: CartFragment? = null
    private var suggestFragment: SuggestFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setDialogFullScreen()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.selectedItemId = R.id.navigation_home
        navView.setOnNavigationItemSelectedListener { menuItem ->
            showFragmentForMenuItem(menuItem.itemId)
            return@setOnNavigationItemSelectedListener true
        }

        val intent = getIntent()
        val check = intent.getIntExtra("check",-1)
        if (check == 1) {
            navView.selectedItemId = R.id.navigation_cart
        }
        else {
            navView.selectedItemId = R.id.navigation_home
        }
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit()
            return true
        }
        return false
    }


    fun showFragmentForMenuItem(menuItem: Int){
        checkFragmentExist()
        val ft = this.supportFragmentManager.beginTransaction()
        when (menuItem) {
            R.id.navigation_home -> if (homeFragment != null && homeFragment?.isAdded!!) {
                ft.show(homeFragment!!)
            } else {
                homeFragment =
                    HomeFragment()
                ft.replace(R.id.frame_layout, homeFragment!!, "HomeFragment")
            }

            R.id.navigation_profile -> if (profileFragment !=null && profileFragment?.isAdded!! ) {
                ft.show(profileFragment!!)
            }
            else
            {
                profileFragment =
                    ProfileFragment()
                ft.replace(R.id.frame_layout, profileFragment!!, menuItem.toString())
            }

            R.id.navigation_suggest-> if (suggestFragment !=null && suggestFragment?.isAdded!! ) {
                ft.show(suggestFragment!!)
            }
            else {
                suggestFragment =
                    SuggestFragment()
                ft.replace(R.id.frame_layout,suggestFragment!!,menuItem.toString())
            }

            R.id.navigation_notifications-> if (notificationFragment !=null && notificationFragment?.isAdded!! ) {
                ft.show(notificationFragment!!)
            }
            else
            {
                notificationFragment =
                    NotificationFragment()
                ft.replace(R.id.frame_layout,notificationFragment!!,menuItem.toString())
            }

            R.id.navigation_cart-> if (cartFragment!= null && cartFragment?.isAdded!!) {

                ft.show(cartFragment!!)
            }
            else
            {
                cartFragment =
                    CartFragment()
                ft.replace(R.id.frame_layout,cartFragment!!,"Cart")
            }
        }
        hideOtherFragment(ft,menuItem)
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        ft.commit()
    }
    private fun checkFragmentExist() {
        val fragments = this.supportFragmentManager.fragments
        for (f in fragments) {
            if (homeFragment == null && f is HomeFragment) {
                homeFragment = f
            }
            if (notificationFragment == null && f is NotificationFragment) {
                notificationFragment = f
            }
            if (cartFragment == null && f is CartFragment) {
                cartFragment = f
            }
            if (suggestFragment == null && f is SuggestFragment) {
                suggestFragment = f
            }
            if (profileFragment == null && f is ProfileFragment) {
                profileFragment = f
            }
        }
    }
    private fun hideOtherFragment(ft: FragmentTransaction, itemId: Int) {
        if (profileFragment != null && profileFragment!!.isAdded && itemId != R.id.navigation_profile)
            ft.hide(profileFragment!!)
        if (notificationFragment != null && notificationFragment?.isAdded!! && itemId != R.id.navigation_notifications)
            ft.hide(notificationFragment!!)
        if (cartFragment != null && cartFragment!!.isAdded && itemId != R.id.navigation_cart)
            ft.hide(cartFragment!!)
        if (homeFragment != null && homeFragment!!.isAdded && itemId != R.id.navigation_home)
            ft.hide(homeFragment!!)
        if (suggestFragment != null && suggestFragment!!.isAdded && itemId != R.id.navigation_suggest)
            ft.hide(suggestFragment!!)
    }

    private fun setDialogFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            this.window?.statusBarColor = this.resources.getColor(R.color.colorPrimary)
            this.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    override fun sendDataToFragment(data: String) {

    }
}
