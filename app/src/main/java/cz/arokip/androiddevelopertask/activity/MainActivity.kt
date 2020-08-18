package cz.arokip.androiddevelopertask.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cz.arokip.androiddevelopertask.R
import cz.arokip.androiddevelopertask.api.GitHubJobsApi
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //var api = GitHubJobsApi.makeRetrofitService(gitHubJobsUrl) do repo

        downloadButton.setOnClickListener {
            textView.text = "clicked"
        }

    }

    companion object {
        const val gitHubJobsUrl = "https://jobs.github.com/"
    }
}