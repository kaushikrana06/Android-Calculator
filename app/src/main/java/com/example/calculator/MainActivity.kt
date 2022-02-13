package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var last : Boolean =false
    var lastdot : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view: View){
        ansInput.append((view as Button).text)
        last = true
    }

    fun clr(view: View){
        ansInput.text=""
        last = false
        lastdot=false
    }

    fun ondeci(view: View){
        if(last && !lastdot){
            ansInput.append(".")
            last=false
            lastdot=true
        }
    }

    fun equal(view: View){
        if(last){
            var value = ansInput.text.toString()
            var prefix=""
            try{

                if(value.startsWith("-")){
                    prefix="-"
                    value=value.substring(1)
                }
                if(value.contains("-")){
                    val split = value.split("-")

                    var one = split[0]
                    var two = split[1]

                    if(!prefix.isEmpty()){
                        one = prefix+one
                    }
                    ansInput.text = rem((one.toDouble() - two.toDouble()).toString())
                }else if(value.contains("*")){
                    val split = value.split("*")

                    var one = split[0]
                    var two = split[1]

                    if(!prefix.isEmpty()){
                        one = prefix+one
                    }
                    ansInput.text = rem((one.toDouble() * two.toDouble()).toString())
                }else if(value.contains("+")){
                    val split = value.split("+")

                    var one = split[0]
                    var two = split[1]

                    if(!prefix.isEmpty()){
                        one = prefix+one
                    }
                    ansInput.text = rem((one.toDouble() + two.toDouble()).toString())
                }else if(value.contains("/")){
                    val split = value.split("/")

                    var one = split[0]
                    var two = split[1]

                    if(!prefix.isEmpty()){
                        one = prefix+one
                    }
                    ansInput.text = rem((one.toDouble() / two.toDouble()).toString())
                }


            }catch (e: ArithmeticException){

                e.printStackTrace()
            }
        }
    }

    private fun rem(result: String) : String{
        var valu = result
        if(result.contains(".0"))
            valu = result.substring(0, result.length-2)
        return valu

    }




    fun onop(view: View){
        if(last && !isadd(ansInput.text.toString())){

            ansInput.append((view as Button).text)
            lastdot=false
            last=false
             }
    }


    private fun isadd(value: String) : Boolean{

        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("*")
                    || value.contains("+") || value.contains("-")
        }


    }






}