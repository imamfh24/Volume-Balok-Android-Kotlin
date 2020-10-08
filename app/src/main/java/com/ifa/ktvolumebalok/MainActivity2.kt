package com.ifa.ktvolumebalok

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    //constanta
    companion object{
        private const val STATE_RESULT = "state_result"
    }

    //View Property
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    private fun bindView(){ //bind view property
        edtWidth = findViewById(R.id.et_width)
        edtHeight = findViewById(R.id.et_height)
        edtLength = findViewById(R.id.et_length)
        btnCalculate = findViewById(R.id.btn_total)
        tvResult = findViewById(R.id.tv_total)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindView()// call bind view
        if(savedInstanceState != null){ //if saved instance state
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    override fun onStart() {
        super.onStart()
        btnCalculate.setOnClickListener(this) //setOnClickListener on btnCalculate
    }

    override fun onClick(v: View?) {
        doCalculate(v)//When click doing this function
    }

    private fun doCalculate(v: View?){
        if(v!!.id == R.id.btn_total){ //if id view click same with btn_total

            //get value from property text
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()
            val inputLength = edtLength.text.toString().trim()

            var isEmptyFields = false //condition for check the empty fields

            when {
                inputLength.isEmpty() -> { //when input length is empty
                    isEmptyFields = true
                    edtLength.error = "Field ini tidak boleh error"
                }
                inputWidth.isEmpty() -> { //when input width is empty
                    isEmptyFields = true
                    edtWidth.error = "Field ini tidak boleh error"
                }
                inputHeight.isEmpty() -> {
                    isEmptyFields = true //when input height is empty
                    edtHeight.error = "Field ini tidak boleh error"
                }
            }

            if(!isEmptyFields){ //if is empty field is false
                //make variable input to double
                val width = inputWidth.toDouble()
                val height = inputHeight.toDouble()
                val length = inputLength.toDouble()
                tvResult.text = calculateValue(width, height, length) //set result text with return function calculate value
            }
        }
    }

    private fun calculateValue(width: Double, height: Double, length: Double) : String {
        val volume = width * height * length //formula for beam value
        return volume.toString() //return volume value
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString()) //set bundle with put string with key state result and value is tv result text
    }
}