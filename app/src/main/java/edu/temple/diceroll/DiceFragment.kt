package edu.temple.diceroll

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

const val DIE_SIDES = "dIcE_SiDeS"

class DiceFragment : Fragment() {
    private var sides: Int? = null
    private var currentNumber = 0

    private lateinit var numberDisplayTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sides = it.getInt(DIE_SIDES)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dice, container, false).apply {

            numberDisplayTextView = findViewById<TextView>(R.id.numberDisplay)
            findViewById<Button>(R.id.rollButton).setOnClickListener {
                numberDisplayTextView.text = (Random.nextInt(sides!!) + 1).toString()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.run {
            currentNumber = getInt(DIE_SIDES, 0)
        }

        if (currentNumber != 0) {
            changeNumber()
        } else {
            changeNumber(currentNumber)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(DIE_SIDES, currentNumber)
    }

    fun changeNumber() {
        if (sides != null) {
            val randomNumber = (Random.nextInt(sides!!) + 1).toString()
            numberDisplayTextView.text = randomNumber
        }
    }

    fun changeNumber(side: Int) {
        numberDisplayTextView.text = side.toString()
    }

    companion object {

        @JvmStatic
        fun newInstance(sides: Int) =
            DiceFragment().apply {
                arguments = Bundle().apply {
                    putInt(DIE_SIDES, sides)
                }
            }
    }
}