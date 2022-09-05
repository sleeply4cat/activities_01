package otus.gpb.homework.activities.receiver

import android.content.ContentResolver
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.gson.Gson
import otus.gpb.homework.activities.api.Payload

class ReceiverActivity : AppCompatActivity() {
    companion object{
        private const val RECEIVER_PAYLOAD_TAG = "PAYLOAD"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver)

        Log.w(localClassName, intent.extras?.keySet().toString())

        val payload = Gson().fromJson(intent.getStringExtra(RECEIVER_PAYLOAD_TAG), Payload::class.java)

        if (payload != null){
            updateUi(payload)
        }
    }

    private fun updateUi(payload: Payload){
        findViewById<TextView>(R.id.name_label).text = payload.title
        findViewById<TextView>(R.id.year_label).text = payload.year
        findViewById<TextView>(R.id.description_label).text = payload.description

        val imageName = ImageRegistry().getImage(this, payload.title.split(" ")[0])

        val imageUri = (Uri.Builder())    // stackoverflow lol
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(R.drawable.no_image))
            .appendPath(resources.getResourceTypeName(R.drawable.no_image))
            .appendPath(imageName)
            .build()

        findViewById<ImageView>(R.id.photo).setImageURI(imageUri)
    }
}