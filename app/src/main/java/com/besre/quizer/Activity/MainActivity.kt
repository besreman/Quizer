package com.besre.quizer.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.besre.quizer.R
import com.besre.quizer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.apply {
            singlePlayerBtn.setOnClickListener{

            }

            bottomMenu.setItemSelected(R.id.btnHome)
            bottomMenu.setOnItemSelectedListener {
                if(it == R.id.btnBoard){
                    startActivity(Intent(this@MainActivity, LeaderBoardActivity::class.java))
                }
            }
        }

    }
}