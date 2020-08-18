package cz.arokip.androiddevelopertask.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import cz.arokip.androiddevelopertask.R
import cz.arokip.androiddevelopertask.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        downloadButton.setOnClickListener {
            textView.text = "clicked"
            mainViewModel.positions.observe(this, Observer { positions ->
                positions.forEach { position->
                    println("position: $position")
                }
            })
        }

    }


}