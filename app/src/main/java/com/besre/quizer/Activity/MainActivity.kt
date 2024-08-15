package com.besre.quizer.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.besre.quizer.Domain.Question
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
            val intent = Intent(this@MainActivity, QuestionActivity::class.java)
            intent.putParcelableArrayListExtra("list",ArrayList(questionList()))
            startActivity(intent)
            }

            bottomMenu.setItemSelected(R.id.btnHome)
            bottomMenu.setOnItemSelectedListener {
                if(it == R.id.btnBoard){
                    startActivity(Intent(this@MainActivity, LeaderBoardActivity::class.java))
                }
            }
        }

    }


    private fun questionList(): MutableList<Question>{
        val questions:MutableList<Question> = mutableListOf()

        questions.add(
            Question(1,
                "which one has the greatest average depth?",
                "Pacific ocean",
                "Atlantic Ocean",
                "Indian ocean",
                "Southern ocean",
                "d",
                5,
                "Q.1",
                null)
        )
        questions.add(
            Question(2,
                "which one has the greatest average depth?",
                "Pacific ocean",
                "Atlantic Ocean",
                "Indian ocean",
                "Southern ocean",
                "d",
                5,
                "Q.2",
                null)
        )
        questions.add(
            Question(3,
                "which one has the greatest average depth?",
                "Pacific ocean",
                "Atlantic Ocean",
                "Indian ocean",
                "Southern ocean",
                "d",
                5,
                "Q.3",
                null)
        )
        questions.add(
            Question(4,
                "which one has the greatest average depth?",
                "Pacific ocean",
                "Atlantic Ocean",
                "Indian ocean",
                "Southern ocean",
                "d",
                5,
                "Q.4",
                null)
        )
        questions.add(
            Question(4,
                "which one has the greatest average depth?",
                "Pacific ocean",
                "Atlantic Ocean",
                "Indian ocean",
                "Southern ocean",
                "d",
                5,
                "Q.4",
                null)
        )


        return questions
    }
}