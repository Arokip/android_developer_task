package cz.arokip.androiddevelopertask.activity

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import cz.arokip.androiddevelopertask.R
import cz.arokip.androiddevelopertask.data.Position
import kotlinx.android.synthetic.main.activity_position_detail.*


class PositionDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_position_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val position = Gson().fromJson(
            intent.getStringExtra(POSITION_DETAIL),
            Position::class.java
        )

        positionCompany.text = position.company

        positionDescription.text = fromHtml(position.description)

    }

    private fun fromHtml(html: String?): Spanned? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    companion object {
        const val POSITION_DETAIL = "POSITION_DETAIL"
    }
}