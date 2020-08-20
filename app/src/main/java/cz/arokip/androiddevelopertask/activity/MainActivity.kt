package cz.arokip.androiddevelopertask.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import cz.arokip.androiddevelopertask.R
import cz.arokip.androiddevelopertask.activity.PositionDetailActivity.Companion.POSITION_DETAIL
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

        mainViewModel.positions.observe(this, Observer { positions ->
            when {
                positions == null -> {
                    errorText.visibility = View.VISIBLE
                    errorText.text = mainViewModel.errorMessage
                }
                positions.isEmpty() -> {
                    errorText.visibility = View.VISIBLE
                    errorText.text = getString(R.string.no_job_found)
                }
                else -> {
                    adapter.setPositions(positions)
                }
            }

            progressBar.visibility = View.GONE
            searchPositionButton.isEnabled = true
        })

        searchPositionButton.setOnClickListener {

            val inputManager: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputManager.hideSoftInputFromWindow(
                currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )

            errorText.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            searchPositionButton.isEnabled = false

            mainViewModel.getAllPositions()

            adapter.setPositions(emptyList())
            errorText.text = "downloading..."
        }
    }

    override fun onItemClick(view: View, position: Int) {
        val intent = Intent(this@MainActivity, PositionDetailActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(
            POSITION_DETAIL,
            Gson().toJson(mainViewModel.positions.value?.get(position))
        )
        startActivity(intent)
    }
}