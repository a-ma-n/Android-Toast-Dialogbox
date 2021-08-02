package aman.myapplication

import MyCustomDialog
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.ProgressDialog.show
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.wang.avi.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    var button_date:Button?=null
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var progressBar:AVLoadingIndicatorView?=findViewById(R.id.avi)
        progressBar?.setVisibility(View.VISIBLE)
        val timer = object: CountDownTimer(5000, 2000) {
            override fun onTick(millisUntilFinished: Long)
            {
//                ProgressDialog.show(this@MainActivity,"abc","def")
                  progressBar?.setVisibility(View.VISIBLE)
            }
            override fun onFinish()
            {
                progressBar?.setVisibility(View.INVISIBLE)
            }
        }
        timer.start()

        setContentView(R.layout.activity_main)
        val alert: Button? = findViewById<Button>(R.id.alerter)
        var button_date = findViewById<Button>(R.id.button)


        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int)
            {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        button_date!!.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(view: View)
            {
                DatePickerDialog(
                    this@MainActivity,
                    dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })

        alert!!.setOnClickListener(object : View.OnClickListener
        {
            override fun onClick(view: View)
            {
                onLogin(view)
            }
        })

        snack!!.setOnClickListener(object : View.OnClickListener
        {
            @SuppressLint("ShowToast")
            override fun onClick(view: View) {
                val snackbar = Snackbar.make(root_layout, "welcome", Snackbar.LENGTH_INDEFINITE)
                snackbar.setAction("Close",View.OnClickListener {
                    snackbar.dismiss()
                })
                snackbar.setActionTextColor(ContextCompat.getColor(this@MainActivity,R.color.design_default_color_primary_dark))
                snackbar.show()
            }
        })

    }

//    fun startAnim(view: View)
//    {
//        avi.show()
//    }
//

//    fun stopAnim()
//    {
//        avi.hide()
//        // or avi.smoothToHide();
//    }

    private fun updateDateInView()
    {
            val myFormat = "MM/dd/yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            button_date!!.text = sdf.format(cal.getTime())
    }
    private fun onLogin(view: View) {
        MyCustomDialog().show(supportFragmentManager, "MyCustomFragment")
    }
    }