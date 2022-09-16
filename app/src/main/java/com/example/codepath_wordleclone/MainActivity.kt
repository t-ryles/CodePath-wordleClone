package com.example.codepath_wordleclone

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Getting view ID's
        val wordToGuess = findViewById<TextView>(R.id.word)

        val button:Button = findViewById(R.id.guessBTN)

        val firstInput = findViewById<TextView>(R.id.firstGuessInput)
        val secondInput = findViewById<TextView>(R.id.secondGuessInput)
        val thirdInput = findViewById<TextView>(R.id.thirdGuessInput)
        val guessedWord:EditText = findViewById(R.id.guessInput)

        val firstOutput = findViewById<TextView>(R.id.firstGuessOutput)
        val secondOutput = findViewById<TextView>(R.id.secondGuessOutput)
        val thirdOutput = findViewById<TextView>(R.id.thirdGuessOutput)


        //Setting up guess count
        var guessCount = 0

        //Getting random word
        val randomWord = FourLetterWordList.getRandomFourLetterWord()
        //Displaying randomWord in word textView
        randomWord.also { wordToGuess.text = it }

        /**
         * Parameters / Fields:
         *   wordToGuess : String - the target word the user is trying to guess
         *   guessedWord : String - what the user entered as their guess
         *
         * Returns a String of 'O', '+', and 'X', where:
         *   'O' represents the right letter in the right place
         *   '+' represents the right letter in the wrong place
         *   'X' represents a letter not in the target word
         */
        fun checkGuess(guessedWord: String) {
            var result = ""
            for (i in 0..3) {
                if (guessedWord[i] == randomWord[i]) {
                    result += "O"
                }
                else if (guessedWord[i] in randomWord) {
                    result += "+"
                }
                else {
                    result += "X"
                }
            }
            when(guessCount) {
                1 -> firstOutput.text = result
                2 -> secondOutput.text = result
                3 -> thirdOutput.text = result
            }
        }


        //Getting the user input on click
            button.setOnClickListener {
                guessCount++

                checkGuess(guessedWord.toString())

                //Added user input to input textViews
                when(guessCount) {
                    1 -> firstInput.text = guessedWord.text
                    2 -> secondInput.text = guessedWord.text
                    3 -> thirdInput.text = guessedWord.text
                }
                if(guessCount == 3 ){
                    button.visibility = View.INVISIBLE
                    wordToGuess.visibility = View.VISIBLE
                }
            }
        }
}
