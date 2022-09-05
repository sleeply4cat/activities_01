package otus.gpb.homework.activities.sender

import android.content.ClipDescription
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import otus.gpb.homework.activities.api.Payload
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class SenderActivity : AppCompatActivity() {
    companion object{
        private const val RESTAURANTS_KEYWORD = "рестораны"
        private const val EMAIL_RECEIVER_ADDRESS = "android@otus.ru"

        private const val RECEIVER_ACTION_TAG = "Action.SEND"
        private const val RECEIVER_PAYLOAD_TAG = "PAYLOAD"
    }

    private lateinit var payloads: PayloadReader.PayloadIterator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sender)

        findViewById<Button>(R.id.action_open_google_maps)
            .setOnClickListener { openGoogle() }
        findViewById<Button>(R.id.action_send_email)
            .setOnClickListener { openEmail() }
        findViewById<Button>(R.id.action_open_receiver)
            .setOnClickListener { sendIntent() }

        initPayloads()

        Log.w(localClassName, Payload("1", "2", "3").toString())
    }

    override fun onDestroy() {
        payloads.close()
        super.onDestroy()
    }

    private fun openGoogle(){
        val searchQuery = URLEncoder.encode(RESTAURANTS_KEYWORD, StandardCharsets.UTF_8.name())

        val uri = Uri.parse("geo:0,0?q=$searchQuery")
        val mapsIntent = Intent(Intent.ACTION_VIEW, uri)
        mapsIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapsIntent)
    }

    private fun openEmail(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = ClipDescription.MIMETYPE_TEXT_PLAIN
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(EMAIL_RECEIVER_ADDRESS))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Created by $localClassName!")
        intent.putExtra(Intent.EXTRA_TEXT, System.currentTimeMillis().toString(3))

        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    private fun sendIntent(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = ClipDescription.MIMETYPE_TEXT_PLAIN
        intent.addCategory(Intent.CATEGORY_DEFAULT)

        val jsonEncoded = Gson().toJson(nextPayload())
        intent.putExtra(RECEIVER_PAYLOAD_TAG, jsonEncoded)

        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }

    private fun nextPayload(): Payload {
        if (!payloads.hasNext()){
            payloads.close()
            initPayloads()
        }
        return payloads.next()
    }

    private fun initPayloads(){
        payloads = PayloadReader(this).iterator()
    }
}