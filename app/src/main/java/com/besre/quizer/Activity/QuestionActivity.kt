package com.besre.quizer.Activity

import android.content.Intent
import android.os.Bundle
import android.view.RoundedCorner
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.besre.quizer.Adapter.QuestionAdapter
import com.besre.quizer.Domain.Question
import com.besre.quizer.R
import com.besre.quizer.databinding.ActivityMainBinding
import com.besre.quizer.databinding.ActivityQuestionBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class QuestionActivity : AppCompatActivity(), QuestionAdapter.score {
    private lateinit var binding:ActivityQuestionBinding
    var position:Int = 0
    var recievedList:MutableList<Question> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        recievedList = intent.getParcelableArrayListExtra<Question>("list")!!.toMutableList()


        binding.apply {
            btnBack.setOnClickListener{ finish() }
            progressBar2.progress=1

            txtQuestion.text = recievedList[position].question
            val drawableResId:Int = binding.root.resources.getIdentifier(
                recievedList[position].picPath, "drawable", binding.root.context.packageName
            )

            Glide.with(this@QuestionActivity)
                .load(drawableResId)
                .centerCrop()
                //.apply(RequestOptions.bitmapTransform(RoundedCorner(60)) )
                .into(questionPic)

            loadAnswers()

            rightArrow.setOnClickListener{
                if(progressBar2.progress==5){
                    val intent = Intent(this@QuestionActivity, ScoreActivity::class.java)
                    intent.putExtra("score", allScore)
                    startActivity(intent)
                    finish()
                    return@setOnClickListener
                }
                position++
                progressBar2.progress = progressBar2.progress +1
                txtQuestionNum.text = "Question "+ progressBar2.progress + "/5"

                val  drawableResId:Int = binding.root.resources.getIdentifier(recievedList[position].picPath, "drawable", binding.root.context.packageName)
                Glide.with(this@QuestionActivity)
                    .load(drawableResId)
                    .centerCrop()
                    .into(questionPic)

                loadAnswers()
            }

            leftArrow.setOnClickListener{
                if(progressBar2.progress==1){

                    return@setOnClickListener
                }
                position--
                progressBar2.progress = progressBar2.progress -1
                txtQuestionNum.text = "Question "+ progressBar2.progress + "/5"

                val  drawableResId:Int = binding.root.resources.getIdentifier(recievedList[position].picPath, "drawable", binding.root.context.packageName)
                Glide.with(this@QuestionActivity)
                    .load(drawableResId)
                    .centerCrop()
                    .into(questionPic)

                loadAnswers()
            }
        }
    }

    private fun loadAnswers(){
        val users:MutableList<String> = mutableListOf()
        users.add(recievedList[position].answer_1.toString())
        users.add(recievedList[position].answer_2.toString())
        users.add(recievedList[position].answer_3.toString())
        users.add(recievedList[position].answer_4.toString())

        if(recievedList[position].clickedAnswer!=null) users.add(recievedList[position].clickedAnswer.toString())

        val questionAdapter by lazy { QuestionAdapter(recievedList[position].correctAnswer.toString(), users, this) }

        questionAdapter.differ.submitList(users)
        binding.questionList.apply { 
            layoutManager = LinearLayoutManager(this@QuestionActivity)
            adapter = questionAdapter;
        }
    }



    override var allScore: Int = 0
        get() = field
        set(value) { field = value}

    override fun amount(number:Int, clickedAnswer:String){
        this.allScore += number
        recievedList[position].clickedAnswer = clickedAnswer
    }
}