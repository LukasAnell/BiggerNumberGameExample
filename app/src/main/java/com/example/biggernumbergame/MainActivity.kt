package com.example.biggernumbergame

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    lateinit var textViewMainScore: TextView
    lateinit var buttonMainLeft: Button
    lateinit var buttonMainRight: Button

    var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        // ignore this stuff
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
         * talk about naming convention in layout
         * --> WHAT_WHERE_WHY Ex. "textView_main_score"
         */

        // initialize elements
        wireWidgets()

        // set the textView with the score
        textViewMainScore.text = "Score: $score"

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
        buttonMainLeft.text = (Math.random() * 100 + 1).roundToInt().toString()
        buttonMainRight.text = (Math.random() * 100 + 1).roundToInt().toString()

        // if you want, you can use a while loop to keep assigning a new number to the buttons if they turn out to be the same
        // while num1 == num2 --> reassign random number
        // will ensure the two numbers are different, which means the two buttons are guaranteed to have different numbers
    }

    private fun setListeners() {
        // choose setOnClickListener without ()
        buttonMainLeft.setOnClickListener {
            // extract the numbers each button stores and assign them to their own variable
            // using Google is important, in this case to figure out how to convert a String into an int
            val button1Val = Integer.parseInt(buttonMainLeft.text.toString())
            val button2Val = Integer.parseInt(buttonMainRight.text.toString())

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
            textViewMainScore.text = "Score: $score"

            // re-randomize buttons
            randomizeButtons()
        }

        buttonMainRight.setOnClickListener {
            // do the same thing but the other way
            // so if button2 is correct you get points
            val button1Val = Integer.parseInt(buttonMainLeft.text.toString())
            val button2Val = Integer.parseInt(buttonMainRight.text.toString())

            if(button2Val > button1Val) {
                score++
            } else {
                score--
            }

            textViewMainScore.text = "Score: $score"

            randomizeButtons()
        }
    }

    private fun wireWidgets() {
        textViewMainScore = findViewById(R.id.textView_main_score)
        buttonMainLeft = findViewById(R.id.button_main_left)
        buttonMainRight = findViewById(R.id.button_main_right)
    }
}