package ge.c0d3in3.task10

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import ge.c0d3in3.task10.App.Companion.sharedPreferencesManager

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        checkUser()
    }

    private fun checkUser() {
        val user = sharedPreferencesManager.getUser()
        if (user == null) Toast.makeText(baseContext, "იუზერი არაა შენახული", Toast.LENGTH_SHORT)
            .show()
    }
}