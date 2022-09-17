package com.example.codepath_wordleclone

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Getting view ID's
        val wordToGuess = findViewById<TextView>(R.id.word)

        val button:Button = findViewById(R.id.guessBTN)
        val reset:Button = findViewById(R.id.resetBTN)

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
        var randomWord = FourLetterWordList.getRandomFourLetterWord()

        //Displaying randomWord in word textView
        randomWord.also { wordToGuess.text = it }


        /**
         * Parameters / Fields:
         *   randomWord : String - the target word the user is trying to guess
         *   guess : String - what the user entered as their guess
         *
         * Returns a String of 'O', '+', and 'X', where:
         *   'O' represents the right letter in the right place
         *   '+' represents the right letter in the wrong place
         *   'X' represents a letter not in the target word
         */
        fun checkGuess(guess: String) {

            var result = ""

            for (i in 0..3) {
                if (guess[i] == randomWord[i]) {

                    result += "O"
                }
                else if (guess[i] in randomWord) {

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

                checkGuess(guessedWord.text.toString().uppercase())

                //Added user input to input textViews
                when(guessCount) {
                    1 -> firstInput.text = guessedWord.text
                    2 -> secondInput.text = guessedWord.text
                    3 -> thirdInput.text = guessedWord.text
                }
                if((guessCount == 3) || (guessedWord.text.toString().uppercase() == randomWord)){
                    button.visibility = View.INVISIBLE
                    reset.visibility = View.VISIBLE
                    wordToGuess.visibility = View.VISIBLE
                }

                guessedWord.text.clear()

            }

        //Reset game
            reset.setOnClickListener {
                0.also { guessCount = it }

                firstInput.text = ""
                secondInput.text = ""
                thirdInput.text = ""

                firstOutput.text = ""
                secondOutput.text = ""
                thirdOutput.text = ""

                button.visibility = View.VISIBLE
                reset.visibility = View.INVISIBLE
                wordToGuess.visibility = View.INVISIBLE

                randomWord = FourLetterWordList.getRandomFourLetterWord()

            }
        }
}
