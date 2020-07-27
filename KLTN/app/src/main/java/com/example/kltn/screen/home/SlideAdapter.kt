package com.example.kltn.screen.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.kltn.R
import kotlinx.android.synthetic.main.slide_poster_home.view.*

class SlideAdapter(private val context: Context?) : PagerAdapter() {
    private var custom_position:Int=0
    private var inflater: LayoutInflater? = null
    private val images = arrayOf(
        R.drawable.poster_1,
        R.drawable.poster_2,
        R.drawable.poster_3,
        R.drawable.poster_4,
        R.drawable.poster_5,
        R.drawable.poster_6
    )

    private val background = arrayOf(
        R.drawable.background_poster_1,
        R.drawable.background_poster_2,
        R.drawable.background_poster_3,
        R.drawable.background_poster_4,
        R.drawable.background_poster_5,
        R.drawable.background_poster_6
    )

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view === `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater!!.inflate(R.layout.slide_poster_home, null)
        view.imageView_slide.setImageResource(images[position])
        view.frameLayout.setImageResource(background[position])
        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}