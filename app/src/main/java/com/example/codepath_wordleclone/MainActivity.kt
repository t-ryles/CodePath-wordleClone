package com.example.codepath_wordleclone

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wordToGuess = findViewById<TextView>(R.id.word)

        fun checkGuess(guess: String) : String {
            var result = ""
            for (i in 0..3) {

                if (guess[i] == wordToGuess[i]) {
                    result += "O"
                }
                else if (guess[i] in wordToGuess) {
                    result += "+"
                }
                else {
                    result += "X"
                }
            }
            return result
        }

    }

}
