package com.besre.quizer.Activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.besre.quizer.Adapter.LeaderAdapter
import com.besre.quizer.Domain.User
import com.besre.quizer.R
import com.besre.quizer.databinding.ActivityLeaderBoardBinding
import com.bumptech.glide.Glide

class LeaderBoardActivity : AppCompatActivity() {
    lateinit var binding:ActivityLeaderBoardBinding
    private val leaderAdapter by lazy { LeaderAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        binding.apply{
            scoreTop1.text = loadData().get(0).score.toString()
            scoreTop2.text = loadData().get(1).score.toString()
            scoreTop3.text = loadData().get(2).score.toString()

            txtNameTop1.text = loadData().get(0).name
            txtNameTop2.text = loadData().get(1).name
            txtNameTop3.text = loadData().get(2).name

            val drawableResId1:Int = binding.root.resources.getIdentifier(
                loadData().get(0).pic, "drawable", binding.root.context.packageName
            )

            val drawableResId2:Int = binding.root.resources.getIdentifier(
                loadData().get(1).pic, "drawable", binding.root.context.packageName
            )

            val drawableResId3:Int = binding.root.resources.getIdentifier(
                loadData().get(2).pic, "drawable", binding.root.context.packageName
            )

            Glide.with(root.context)
                .load(drawableResId1)
                .into(picTop1)

            Glide.with(root.context)
                .load(drawableResId2)
                .into(picTop2)

            Glide.with(root.context)
                .load(drawableResId3)
                .into(picTop3)

            bottomMenu.setItemSelected(R.id.btnBoard)
            bottomMenu.setOnItemSelectedListener {
                if(it ==R.id.btnHome){
                    startActivity(Intent(this@LeaderBoardActivity, MainActivity::class.java))
                    finish()
                }
            }

            var list:MutableList<User> = loadData()
            list.removeAt(0)
            list.removeAt(1)
            list.removeAt(2)

            leaderAdapter.differ.submitList(list)

            leaderBoardTable.apply {
                layoutManager = LinearLayoutManager(this@LeaderBoardActivity)
                adapter = leaderAdapter
            }
        }
}
    private fun loadData():MutableList<User>{
        val users: MutableList<User> = mutableListOf()
        users.add(User(1, "Kocheche", "person1", 4850))
        users.add(User(2, "Ayele", "person2", 4560))
        users.add(User(3, "Dafersa", "person3", 4450))
        users.add(User(4, "Demeke", "person4", 4120))
        users.add(User(5, "WoldeMariam", "person5", 4090))
        users.add(User(6, "Habte", "person6", 3850))
        users.add(User(7, "Fidel", "person7", 3840))
        users.add(User(8, "Ayantu", "person8", 3598))
        users.add(User(9, "Askalech", "person9", 3597))
        users.add(User(10, "Haile", "person10", 2860))
        users.add(User(11, "Debretsion", "person2", 2850))
        users.add(User(12, "Tom", "person3", 2810))

        return users
    }
}