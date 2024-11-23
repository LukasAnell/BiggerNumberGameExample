package com.example.biggernumbergame

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.biggernumbergame.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        // ignore this stuff
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
         * talk about naming convention in layout
         * --> WHAT_WHERE_WHY Ex. "textView_main_score"
         */

        // set the textView with the score
        binding.textViewMainScore.text = "Score: $score"

        // randomize the numbers on each button separately
        // use a function for this so you can just call it over and over again
        // will be called every time you click on a button
        randomizeButtons()

        // set listeners for buttons
        // --> will run a block of code whenever a button is pressed
        // kind of like a method/function
        setListeners()
    }

    private fun randomizeButtons() {
        // remember Math.random() syntax for making ranges
        // Math.random() * possibilities + minimum
        binding.buttonMainLeft.text = (Math.random() * 100 + 1).roundToInt().toString()
        binding.buttonMainRight.text = (Math.random() * 100 + 1).roundToInt().toString()

        // if you want, you can use a while loop to keep assigning a new number to the buttons if they turn out to be the same
        // while num1 == num2 --> reassign random number
        // will ensure the two numbers are different, which means the two buttons are guaranteed to have different numbers
    }

    private fun setListeners() {
        // choose setOnClickListener without ()
        binding.buttonMainLeft.setOnClickListener {
            // extract the numbers each button stores and assign them to their own variable
            // using Google is important, in this case to figure out how to convert a String into an int
            val button1Val = Integer.parseInt(binding.buttonMainLeft.text.toString())
            val button2Val = Integer.parseInt(binding.buttonMainRight.text.toString())

            // check if their answer is correct
            // compare the number stored in the button to the number stored in the other button
            // if button1 is the bigger number, add to the score
            // if not, subtract from the score
            if(button1Val > button2Val) {
                score++
            } else {
                // if you want, you can make sure the score doesn't go below 0
                // if(score < 0) --> do nothing
                // else --> reduce the score
                score--
            }

            // update textView with the score
            binding.textViewMainScore.text = "Score: $score"

            // re-randomize buttons
            randomizeButtons()
        }

        binding.buttonMainRight.setOnClickListener {
            // do the same thing but the other way
            // so if button2 is correct you get points
            val button1Val = Integer.parseInt(binding.buttonMainLeft.text.toString())
            val button2Val = Integer.parseInt(binding.buttonMainRight.text.toString())

            if(button2Val > button1Val) {
                score++
            } else {
                score--
            }

            binding.textViewMainScore.text = "Score: $score"

            randomizeButtons()
        }
    }
}