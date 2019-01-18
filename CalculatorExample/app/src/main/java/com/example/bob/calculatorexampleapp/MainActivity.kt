package com.example.bob.calculatorexampleapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Numbers
        tvOne.setOnClickListener { appendOnExpression ( string: "1", canClear: true )}
        tvTwo.setOnClickListener { appendOnExpression ( string: "2", canClear: true )}
        tvThree.setOnClickListener { appendOnExpression ( string: "3", canClear: true )}
        tvFour.setOnClickListener { appendOnExpression ( string: "4", canClear: true )}
        tvFive.setOnClickListener { appendOnExpression ( string: "5", canClear: true )}
        tvSix.setOnClickListener { appendOnExpression ( string: "6", canClear: true )}
        tvSeven.setOnClickListener { appendOnExpression ( string: "7", canClear: true )}
        tvEight.setOnClickListener { appendOnExpression ( string: "8", canClear: true )}
        tvNine.setOnClickListener { appendOnExpression ( string: "9", canClear: true )}
        tvZero.setOnClickListener { appendOnExpression ( string: "0", canClear: true )}
        tvDot.setOnClickListener { appendOnExpression ( string: ".", canClear: true )}

        // Operators
        tvPlus.setOnClickListener { appendOnExpression ( string: "+", canClear: false )}
        tvMinus.setOnClickListener { appendOnExpression ( string: "-", canClear: false )}
        tvMul.setOnClickListener { appendOnExpression ( string: "*", canClear: false )}
        tvDivide.setOnClickListener { appendOnExpression ( string: "/", canClear: false )}
        tvOpen.setOnClickListener { appendOnExpression ( string: "(", canClear: false )}
        tvClose.setOnClickListener { appendOnExpression ( string: ")", canClear: false )}

        tvClear.setOnClickListener {
                tvExpression.text = ""
                tvResult.text = ""
        }

        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if (string.isNotEmpty()) {
                tvExpression.text = string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }

        tvEquals.setOnClickListener(
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()
            } catch (e:Exception) {
                Log.d("Exception", " message : " + e.message )
            } as View.OnClickListener?
        )
    }

    private fun appendOnExpression( string: String, canClear : Boolean) {
            if (tvResult.text.isNotEmpty()) {
                tvExpression.text = ""
            }
            if(canClear) {
                tvResult.text = ""
                tvExpression.append(string)
            } else {
                tvExpression.append(tvResult.text)
                tvExpression.append(string)
                tvResult.text = ""
            }
    }
}
