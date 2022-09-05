package otus.gpb.homework.activities.receiver

import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class ImageRegistry {
    fun getImage(context: Context, tag: String): String? {
        val reader = context.resources.openRawResource(R.raw.images).bufferedReader()
        val records = Gson().fromJson(reader, Array<ImageTags>::class.java)

        records.map { if (it.tags.contains(tag.lowercase())) return it.image }

        return null
    }

    data class ImageTags(
        @SerializedName("image")
        val image: String,
        @SerializedName("tags")
        val tags: Set<String>
    )
}