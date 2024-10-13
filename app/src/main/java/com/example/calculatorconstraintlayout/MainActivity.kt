package com.example.calculatorconstraintlayout

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var screentText: TextView
    lateinit var op1Text: TextView
    lateinit var opText: TextView
    lateinit var op2Text: TextView
    var state: Int = 1
    var _operation: Int = 0 //1: plus, 2: minus, 3: product, 4: divide
    var op1: Int = 0
    var op2: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screentText = findViewById(R.id.screenText)
        op1Text = findViewById(R.id.op1)
        opText = findViewById(R.id.op)
        op2Text = findViewById(R.id.op2)
        op1Text.text = "";
        op2Text.text = "";
        opText.text = "";
        findViewById<Button>(R.id.button0).setOnClickListener(this)
        findViewById<Button>(R.id.button1).setOnClickListener(this)
        findViewById<Button>(R.id.button2).setOnClickListener(this)
        findViewById<Button>(R.id.button3).setOnClickListener(this)
        findViewById<Button>(R.id.button4).setOnClickListener(this)
        findViewById<Button>(R.id.button5).setOnClickListener(this)
        findViewById<Button>(R.id.button6).setOnClickListener(this)
        findViewById<Button>(R.id.button7).setOnClickListener(this)
        findViewById<Button>(R.id.button8).setOnClickListener(this)
        findViewById<Button>(R.id.button9).setOnClickListener(this)
        findViewById<Button>(R.id.plusButton).setOnClickListener(this)
        findViewById<Button>(R.id.minusButton).setOnClickListener(this)
        findViewById<Button>(R.id.productButton).setOnClickListener(this)
        findViewById<Button>(R.id.divideButton).setOnClickListener(this)
        findViewById<Button>(R.id.equalButton).setOnClickListener(this)
        findViewById<Button>(R.id.signButton).setOnClickListener(this)
        findViewById<Button>(R.id.bsButton).setOnClickListener(this)
        findViewById<Button>(R.id.cButton).setOnClickListener(this)
        findViewById<Button>(R.id.ceButton).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val id = v?.id
        if (id == R.id.button0){
            addDigit(0)
        }
        else if (id == R.id.button1){
            addDigit(1)
        }
        else if (id == R.id.button2){
            addDigit(2)
        }
        else if (id == R.id.button3){
            addDigit(3)
        }
        else if (id == R.id.button4){
            addDigit(4)
        }
        else if (id == R.id.button5){
            addDigit(5)
        }
        else if (id == R.id.button6){
            addDigit(6)
        }
        else if (id == R.id.button7){
            addDigit(7)
        }
        else if (id == R.id.button8){
            addDigit(8)
        }
        else if (id == R.id.button9){
            addDigit(9)
        }
        else if (id == R.id.bsButton){
            removeDigit()
        }
        else if (id == R.id.signButton){
            if (state == 1) {
                op1 = -op1
                screentText.text = op1.toString()
            } else {
                op2 = -op2
                screentText.text = op2.toString()
            }
        }
        else if (id == R.id.plusButton){
            _operation = 1;
            if (state == 1) {
            state = 2
            } else{
                op1 = calculate(_operation)
            }
        }else if (id == R.id.minusButton){
            _operation = 2;
            if (state == 1) {
                state = 2
            } else{
                op1 = calculate(_operation)
            }
        }
        else if (id == R.id.productButton){
            _operation = 3;
            if (state == 1) {
                state = 2
            } else{
                op1 = calculate(_operation)
            }
        }
        else if (id == R.id.divideButton){
            _operation = 4;
            if (state == 1) {
                state = 2
            } else{
                op1 = calculate(_operation)
            }
        }
        else if (id == R.id.equalButton){
            if (state == 2){
                op1 = calculate(_operation)
                op2 = 0;
            }
        }
        else if (id == R.id.ceButton){
            op1 = 0
            op2 = 0
            screentText.text = "0"
            state = 1
        }
        op1Text.text = op1.toString()
        op2Text.text = op2.toString()
        if (_operation == 1){
            opText.text = "+"
        } else if (_operation == 2){
            opText.text = "-"
        } else if (_operation == 3){
            opText.text = "x"
        } else if (_operation == 4){
            opText.text = "/"
        }
    }
    private fun addDigit(c: Int) {
        if (state == 1) {
            if (op1 >= 0){
                op1 = op1 * 10 + c
            } else {
                op1 = op1 * 10 - c
            }
            screentText.text = op1.toString()
        } else {
            if (op2 >= 0){
                op2 = op2 * 10 + c
            } else {
                op2 = op2 * 10 - c
            }
            screentText.text = op2.toString()
        }
    }
    private fun removeDigit() {
        if (state == 1) {
            op1 /= 10
            screentText.text = op1.toString()
        } else {
            op2 /= 10
            screentText.text = op2.toString()
        }
    }
    private fun calculate(operation: Int): Int{
        var result = 0
        when (operation) {
            1 -> {
                result = op1 + op2
            }
            2 -> {
                result = op1 - op2
            }
            3 -> {
                result = op1 * op2
            }
            4 -> {
                if (op2 == 0){
                    screentText.text = "Error"
                }
                else{
                    result = op1 / op2
                    screentText.text = result.toString()
                }
            }
        }
        if (_operation != 4){
            screentText.text = result.toString()
        }
        state = 1
        op2 = 0
        return result
    }
}