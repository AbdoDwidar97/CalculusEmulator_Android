package com.example.abd_el_rahman.calculus_emu

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import org.xml.sax.DTDHandler
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus
import android.view.Window

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
    }


    var FirstOP = true
    var TDtals : String = ""
    fun NumberSelection(view : View){
        if(FirstOP) {
            TVShow.text = ""
            FirstOP = false
        }

        var InputText : String = TVShow.text.toString()

        var BtnSelect = view as Button

        when (BtnSelect.id){
            Btn0.id -> {
                InputText = InputText + "0"
                TDtals+= "0"
            }
            Btn1.id -> {
                InputText = InputText + "1"
                TDtals+= "1"
            }
            Btn2.id -> {
                InputText = InputText + "2"
                TDtals+= "2"
            }
            Btn3.id -> {
                InputText = InputText + "3"
                TDtals+= "3"
            }
            Btn4.id -> {
                InputText = InputText + "4"
                TDtals+= "4"
            }
            Btn5.id -> {
                InputText = InputText + "5"
                TDtals+= "5"
            }
            Btn6.id -> {
                InputText = InputText + "6"
                TDtals+= "6"
            }
            Btn7.id -> {
                InputText = InputText + "7"
                TDtals+= "7"
            }
            Btn8.id -> {
                InputText = InputText + "8"
                TDtals+= "8"
            }
            Btn9.id -> {
                InputText = InputText + "9"
                TDtals+= "9"
            }
            BtnDot.id -> {
                InputText = InputText + "."
                TDtals+= "."
            }
            Btn_PM.id -> {
                InputText = "-" + InputText
                TDtals += InputText
            }
            Btn_C.id ->{
                TVShow.text = "0"
                TvDetails.text = ""
                TDtals = ""
                FirstOP = true
                Numb.clear()
                Pwr.clear()
                return
            }
            Btn_Del.id ->{
                try {
                    var intrange : Int = InputText.length - 1
                    var intrange2 : Int = TDtals.length - 1
                    InputText = InputText.removeRange(intrange , intrange + 1)
                    TDtals = TDtals.removeRange(intrange2 , intrange2 + 1)
                }catch (Ex : Exception){
                    Toast.makeText(this,"there is no number to delete !",Toast.LENGTH_LONG).show()
                }

            }

        }
        TVShow.text = InputText
        TvDetails.text = TDtals
    }

    var OPMark : String = "+"
    var FirstNum = ""
    fun OperationSelection(view : View){
        var OPSelect = view as Button
        var InputDetails : String = TDtals
        if(isEquation){
            when(OPSelect.id){
                Btn_Plus.id -> {
                    InputDetails += "+"
                    Pwr.add(TVShow.text.toString().toInt())
                    isEquation = false
                }
                Btn_Minus.id -> {
                    InputDetails += "-"
                    Pwr.add(TVShow.text.toString().toInt())
                    isMinusEquation = true
                    isEquation = false
                }
            }
            FirstOP = true
            TDtals = InputDetails
            TvDetails.text = TDtals
            return
        }

        FirstNum = TVShow.text.toString()


        when (OPSelect.id){
            Btn_Plus.id -> {
                OPMark = "+"
                InputDetails += "+"

            }
            Btn_Minus.id -> {
                OPMark = "-"
                InputDetails += "-"
            }
            Btn_Product.id -> {
                OPMark = "*"
                InputDetails += "*"
            }
            Btn_Devide.id -> {
                OPMark = "/"
                InputDetails += "/"
            }

            Btn_Pwr.id -> {
                OPMark = "^"
                InputDetails += "^"
            }
        }
        FirstOP = true
        TDtals = InputDetails
        TvDetails.text = TDtals
    }

    fun CalculateNumbers(view : View){
        var SecondNum = TVShow.text.toString()
        var Result : Double = 1.0
        when(OPMark){
            "+" -> Result = FirstNum.toDouble() + SecondNum.toDouble()
            "-" -> Result = FirstNum.toDouble() - SecondNum.toDouble()
            "*" -> Result = FirstNum.toDouble() * SecondNum.toDouble()
            "/" -> Result = FirstNum.toDouble() / SecondNum.toDouble()

            "^" -> {
                for(l in 1..SecondNum.toInt()){
                    Result *= FirstNum.toDouble()
                }
            }
        }
        TVShow.text = Result.toString()
        TvDetails.text = TDtals
    }

    fun CalcFactorial(view : View){
        var Num = TVShow.text.toString().toInt()
        var Res : Int = 1
        for(i in 1..Num){
            Res *= i
        }
        TVShow.text = Res.toString()
    }

    var isEquation = false
    var Numb = ArrayList<Double>()
    var Pwr = ArrayList<Int>()
    var isMinusEquation = false

    fun XcalculateEquation(view : View){
        isEquation  = true
        if(isMinusEquation){
            Numb.add(-1 *(TVShow.text.toString().toDouble()))
            isMinusEquation = false
        }else Numb.add(TVShow.text.toString().toDouble())

        TDtals += "X^"
        FirstOP = true
        TvDetails.text = TDtals
    }



    fun DiffEquation(view : View){

        if(isEquation)Pwr.add(TVShow.text.toString().toInt())
        var FullDiffEquation : String = ""
        var NewNumb = ArrayList<Double>()
        var NewPwr = ArrayList<Int>()

        for(i in 0..Numb.size - 1){
            NewNumb.add(Numb[i]*Pwr[i])
            NewPwr.add(Pwr[i] - 1)
        }

        for(i in 0..NewNumb.size - 1){
            if(NewPwr[i] == 1) FullDiffEquation += NewNumb[i].toString() + "X  "
            else if (NewPwr[i] == 0) FullDiffEquation += NewNumb[i].toString() + "  "
            else FullDiffEquation += NewNumb[i].toString() + "X^" + NewPwr[i].toString() + "  "
        }
        TVShow.text = FullDiffEquation
        NewNumb.clear()
        NewPwr.clear()
        Pwr.clear()
        Numb.clear()

    }

    fun IntegrationOfEquation(view : View){
        var SingleNum : String = ""
        if(isEquation)Pwr.add(TVShow.text.toString().toInt())
        if(!isEquation) SingleNum = TVShow.text.toString()
        var FullDiffEquation : String = ""
        var NewNumb = ArrayList<Double>()
        var NewPwr = ArrayList<Int>()

        for(i in 0..Numb.size - 1){
            NewNumb.add((Numb[i]/(Pwr[i] + 1)))
            NewPwr.add(Pwr[i] + 1)
        }

        for(i in 0..NewNumb.size - 1){
            if(NewPwr[i] == 1) FullDiffEquation += NewNumb[i].toString() + "X  "
            else if (NewPwr[i] == 0) FullDiffEquation += NewNumb[i].toString() + "  "
            else FullDiffEquation += NewNumb[i].toString() + "X^" + NewPwr[i].toString() + "  "
        }
        if(SingleNum != "") FullDiffEquation+= SingleNum + "X" + "+C"
        else FullDiffEquation += "+C"
        TVShow.text = FullDiffEquation
        NewNumb.clear()
        NewPwr.clear()
        Pwr.clear()
        Numb.clear()


    }

}

