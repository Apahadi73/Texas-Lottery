package com.example.lotogen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.lotogen.databinding.ResultBinding
import kotlin.random.Random


class ResultActivity : AppCompatActivity() {
    lateinit var binding: ResultBinding;
    private var numOfPlays = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.result
        )
        val selectedGame = intent.getStringExtra("selectedGame")
        numOfPlays = intent.getStringExtra("numOfPlays")?.toInt() ?: 1
        if (selectedGame != null) {
            playLottery(selectedGame)
        }

//        todo: use navigation host controller instead of creating intent every time
        binding.newGameButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    //    play lottery
    private fun playLottery(selectedGame: String): Unit {
        when (selectedGame) {
            "Pick 3" -> playPick3()
            "Daily 4" -> playDaily4()
            "Power Ball" -> playPowerBall()
            "Mega Millions" -> playMegaMillions()
            "Lotto Texas" -> playLotto()
            "Texas Two Step" -> playTexasTwoStep()
            "Cash Five" -> playCashFive()
            "All or Nothing" -> playAllOrNothing()
            else -> Log.i("value", selectedGame)
        }
//        Log.i("value", numOfPlays)
    }

    //    generates the random string for the lottery games and returns the string to the function call
    private fun generateRandomString(start: Int, end: Int, ballNo: Int): String {
        var numberOfTimes = ballNo
        var numberString = " "
        while (numberOfTimes > 0) {
            numberString = numberString + Random.nextInt(start, end).toString() + "           "
            numberOfTimes--
        }
        return numberString
    }

    //plays pick3
    private fun playPick3() {
        //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
        val lotteryNumbers = ArrayList<String>()
        while (numOfPlays > 0) {
            val pick3Number = generateRandomString(0, 9, 3)
            lotteryNumbers.add(pick3Number)
            numOfPlays--
        }
        binding.lotteryNumbersList.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, lotteryNumbers)
    }

    //plays daily 4
    private fun playDaily4() {
        val lotteryNumbers = ArrayList<String>()
        while (numOfPlays > 0) {
            val daily4Numbers = generateRandomString(0, 9, 4)
            lotteryNumbers.add(daily4Numbers)
            numOfPlays--
        }
        binding.lotteryNumbersList.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, lotteryNumbers)

    }

    //play powerball
    private fun playPowerBall(): Unit {
        val lotteryNumbers = ArrayList<String>()
        while (numOfPlays > 0) {
            val powerBallNumbers =
                generateRandomString(1, 69, 5) + "  " + Random.nextInt(1, 26).toString()
            lotteryNumbers.add(powerBallNumbers)
            numOfPlays--
        }
        binding.lotteryNumbersList.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, lotteryNumbers)

    }

    //play mega millions
    private fun playMegaMillions(): Unit {
        val lotteryNumbers = ArrayList<String>()
        while (numOfPlays > 0) {
            val megaMillionNumbers =
                generateRandomString(1, 70, 5) + "  " + Random.nextInt(1, 25).toString()
            lotteryNumbers.add(megaMillionNumbers)
            numOfPlays--
        }
        binding.lotteryNumbersList.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, lotteryNumbers)


    }

    //    play texas lottery loto
    private fun playLotto(): Unit {
        val lotteryNumbers = ArrayList<String>()
        while (numOfPlays > 0) {
            val lottoNumbers = generateRandomString(1, 54, 6)
            lotteryNumbers.add(lottoNumbers)
            numOfPlays--
        }
        binding.lotteryNumbersList.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, lotteryNumbers)

    }

    //    plays Texas Two Step
    private fun playTexasTwoStep(): Unit {
        val lotteryNumbers = ArrayList<String>()
        while (numOfPlays > 0) {
            val texasTwoStepNumbers =
                generateRandomString(1, 35, 4) + "  " + Random.nextInt(1, 35).toString()
            lotteryNumbers.add(texasTwoStepNumbers)
            numOfPlays--
        }
        binding.lotteryNumbersList.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, lotteryNumbers)

    }

    //    plays All or Nothing
    private fun playAllOrNothing(): Unit {
        val lotteryNumbers = ArrayList<String>()
        while (numOfPlays > 0) {
            val allOrNothingNumbers = generateRandomString(1, 24, 12)
            lotteryNumbers.add(allOrNothingNumbers)
            numOfPlays--
        }
        binding.lotteryNumbersList.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, lotteryNumbers)

    }

    //    plays Cash Five
    private fun playCashFive(): Unit {
        val lotteryNumbers = ArrayList<String>()
        while (numOfPlays > 0) {
            val cashFiveNumbers = generateRandomString(1, 35, 5)
            lotteryNumbers.add(cashFiveNumbers)
            numOfPlays--
        }
        binding.lotteryNumbersList.adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, lotteryNumbers)

    }


}