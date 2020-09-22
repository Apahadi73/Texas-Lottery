package com.example.lotogen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.lotogen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var selectedGame: String;
    private lateinit var numOfPlays: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        //  creates and  handles all the operation related to lottery game spinner
        createGameSpinner(binding.lotteryGameSpinner, R.array.lottery_games, "lottery")
//        creates and handles the number of plays operation on the screen
        createGameSpinner(binding.numPlaySpinner, R.array.numberOfPlays, "number")

        //sets onclick listener to our button and handles the response
        binding.generateButton.setOnClickListener {
            val intent = Intent(this,ResultActivity::class.java).apply {
                putExtra("selectedGame",selectedGame)
                putExtra("numOfPlays",numOfPlays)
            }
            startActivity(intent)
        }
    }

    //    creates spinner, handles all the operation and returns selected value
    private fun createGameSpinner(spinner: Any, stringArray: Any, spinnerType: String): Unit {
        var selectedValue: String
        val gameSpinner: Spinner = spinner as Spinner

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            stringArray as Int,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            gameSpinner.adapter = adapter
        }
        gameSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("reached", "reached here")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent != null) {
                    selectedValue = parent.getItemAtPosition(position).toString()
                    //fills our lazy variables in accordance with the selected type
                    if (spinnerType == "lottery") {
                        selectedGame = selectedValue
                        Log.i("value", "Game Selected: $selectedGame")
                    } else {
                        numOfPlays = selectedValue
                        Log.i("value", "Number of times: $numOfPlays")
                    }
                }
            }

        }
    }

}