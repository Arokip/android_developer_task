package cz.arokip.androiddevelopertask.activity

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.squareup.picasso.Picasso
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

        if (position.companyLogo != null) {
            positionDetailCompanyLogo.visibility = View.VISIBLE
            Picasso.get().load(position.companyLogo).into(positionDetailCompanyLogo)
        }

        positionDetailTitle.text = position.title

        positionDetailCompany.text = position.company

        positionDetailLocation.text = position.location

        positionDetailType.text = position.type

        positionDetailDescription.text = fromHtml("${position.description}\n${position.howToApply}")
        positionDetailDescription.movementMethod = LinkMovementMethod.getInstance();

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