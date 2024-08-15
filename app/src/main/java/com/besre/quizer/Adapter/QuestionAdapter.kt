package com.besre.quizer.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.besre.quizer.R
import com.besre.quizer.databinding.ViewHolderQuestionBinding

class QuestionAdapter(val correctAnswer:String, val users:MutableList<String> = mutableListOf(),
var returnScore: score):RecyclerView.Adapter<QuestionAdapter.ViewHolder>(){
    interface score{
        var allScore:Int
        fun amount(number :Int, clickedAnswer:String)
    }

    private lateinit var binding:ViewHolderQuestionBinding

    inner class ViewHolder : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.ViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        binding = ViewHolderQuestionBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: QuestionAdapter.ViewHolder, position: Int) {
        val binding = ViewHolderQuestionBinding.bind(holder.itemView)
        binding.txtAlternative.text=differ.currentList[position]
        var currentPos = 0
        when(correctAnswer){
            "a"->{
                currentPos=0
            }
            "b"->{
                currentPos=1
            }
            "c"->{
                currentPos=2
            }
            "d"->{
                currentPos=3
            }
        }

        if(differ.currentList.size == 5 && currentPos==position){
            binding.txtAlternative.setBackgroundResource(R.drawable.right_answer_bg)
            val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.tick)
            binding.txtAlternative.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)
        }

        if(differ.currentList.size == 5){
            var clickedPos=0
            when(differ.currentList[4]){
                "a"->{
                    clickedPos=0
                }
                "b"->{
                    clickedPos=1
                }
                "c"->{
                    clickedPos=2
                }
                "d"->{
                    clickedPos=3
                }
            }
            if(clickedPos==position && clickedPos!=currentPos){
                binding.txtAlternative.setBackgroundResource(R.drawable.wrong_answer_bg)
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.thieves)
                binding.txtAlternative.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)

            }
        }

        if (position == 4){
            binding.root.visibility= View.GONE
        }

        holder.itemView.setOnClickListener{
            var str =""
            when(position){
                0->{
                    str="a"
                }
                1->{
                    str="b"
                }
                2->{
                    str="c"
                }
                3->{
                    str="d"
                }
            }

            users.add(4,str)
            notifyDataSetChanged()

            if (currentPos == position){
                binding.txtAlternative.setBackgroundResource(R.drawable.right_answer_bg)
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.tick)
                binding.txtAlternative.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)
            returnScore.amount(5, str)
            }else{
                binding.txtAlternative.setBackgroundResource(R.drawable.wrong_answer_bg)
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.thieves)
                binding.txtAlternative.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, drawable, null)

            }
            returnScore.amount(0,str)
        }

        if(differ.currentList.size == 5) holder.itemView.setOnClickListener(null)

    }

    override fun getItemCount()= differ.currentList.size

    private val differCallback = object: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem==newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}
