import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.travelookup.R

class OnboardingActivity : AppCompatActivity() {
    private var viewPagerAdapter: OnboardingAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.onboarding)
        viewPagerAdapter = OnboardingAdapter(supportFragmentManager)
        val view_pager = findViewById<androidx.viewpager.widget.ViewPager>(R.id.view_pager)
        val indicator = findViewById<com.tbuonomo.viewpagerdotsindicator.DotsIndicator>(R.id.indicator)

        view_pager.adapter = viewPagerAdapter
        indicator.setViewPager(view_pager)
    }

    override fun onStart() {
        super.onStart()
        val nextButton = findViewById<android.widget.Button>(R.id.nextButton)
        nextButton.setOnClickListener {
//            startActivity(Intent(this, InitUserInfoActivity::class.java))
            finish()
        }
    }

    private inner class OnboardingAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
        override fun getCount(): Int {
            return 3
        }

        override fun getItem(i: Int): Fragment {
            when (i) {
                0 -> {
                    return OnboardingOne()
                }
                1 -> {
                    return OnboardingTwo()
                }
                2 -> {
                    return OnboardingThree()
                }
                else -> {
                    return OnboardingOne()
                }
            }
        }
    }

    class OnboardingOne : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.onboarding_one, container, false)
        }
    }

    class OnboardingTwo : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.onboarding_two, container, false)
        }
    }

    class OnboardingThree : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.onboarding_three, container, false)
        }
    }
}

