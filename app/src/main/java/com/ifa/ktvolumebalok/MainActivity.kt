package com.ifa.ktvolumebalok

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // Membuat object konstanta untuk key agar dapat digunakan oleh onSavedInstanceState
    companion object {
        private const val STATE_RESULT = "state_result"
    }

    // Membuat variabel untuk menampung komponen view
    private lateinit var etLength : EditText
    private lateinit var etWidth: EditText
    private lateinit var etHeight: EditText
    private lateinit var btnTotal: Button
    private lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hubungkan variabel dengan komponen view
        etLength = findViewById(R.id.et_length)
        etWidth = findViewById(R.id.et_width)
        etHeight = findViewById(R.id.et_height)
        btnTotal = findViewById(R.id.btn_total)
        tvTotal = findViewById(R.id.tv_total)

        btnTotal.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvTotal.text = result
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_total){
            // Take the input field from length, width, and height to variable val
            val inputLength = etLength.text.toString().trim()
            val inputWidth = etWidth.text.toString().trim()
            val inputHeight = etHeight.text.toString().trim()

            // Make variable for empty fields and is invalid double with boolean data type
            var isInvalidDouble: Boolean = false

            // Convert the input (string) to double
            val length = toDouble(inputLength)
            val width = toDouble(inputWidth)
            val height = toDouble(inputHeight)

            if (length.isNull()) {
                isInvalidDouble = true
                etLength.error = "This field must be valid number"
            }

            if(width.isNull()){
                isInvalidDouble = true
                etWidth.error = "This field must be valid number"
            }

            if (height.isNull()){
                isInvalidDouble = true
                etHeight.error = "This field must be valid number"
            }

            if(!isInvalidDouble){
                val volume = length!! * width!! * height!!
                tvTotal.text = volume.toString()
            }
        }
    }

    private fun Double?.isNull() : Boolean{
        return this == null
    }

    private fun toDouble(str: String) : Double?{
        return try{
            str.toDouble()
        } catch (e : NumberFormatException){
            null
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvTotal.text.toString())
    }
}