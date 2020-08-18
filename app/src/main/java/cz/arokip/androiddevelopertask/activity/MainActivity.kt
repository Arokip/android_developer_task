package cz.arokip.androiddevelopertask.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cz.arokip.androiddevelopertask.R
import cz.arokip.androiddevelopertask.view.PositionAdapter
import cz.arokip.androiddevelopertask.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
    PositionAdapter.ItemClickListener {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var adapter: PositionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        adapter = PositionAdapter()
        adapter.setClickListener(this)
        adapter.setPositions(emptyList())

        positionRecyclerView.layoutManager = LinearLayoutManager(this)
        positionRecyclerView.adapter = adapter

        downloadButton.setOnClickListener {
            mainViewModel.positions.observe(this, Observer { positions ->
//                positions.forEach { position->
//                    println("position: $position")
//                }
                if (positions.isNotEmpty()) {
                    println(positions[0])
                }
                adapter.setPositions(positions)
                textView.text = "downloaded"
            })
        }
    }

    override fun onItemClick(view: View, position: Int) {
        println("position's position: $position")
    }
}