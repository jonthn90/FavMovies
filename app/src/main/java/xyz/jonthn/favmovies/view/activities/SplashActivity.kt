package xyz.jonthn.favmovies.view.activities

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import xyz.jonthn.favmovies.R
import xyz.jonthn.favmovies.databinding.ActivitySplashBinding

/**
 * This activity just have the purpose to present an animation as splash screen.
 * Also this activity could be use to made a login process
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        //intentToMainActivity()
        setAnimation()
    }

    /**
     * Use Lottie Lib to implement ligth and beautiful animations
     * Check https://github.com/airbnb/lottie-android
     */
    private fun setAnimation() {

        binding.animationViewSplash.apply {

            setAnimation(R.raw.splash)

            addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationEnd(animation: Animator) {
                    intentToMainActivity()
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
        }
    }

    /**
     * When the animation ends, finish current activity and start the main activity app
     * The App works on a single activity architecture
     */
    private fun intentToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
