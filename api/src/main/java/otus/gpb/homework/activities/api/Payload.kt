package otus.gpb.homework.activities.api

import com.google.gson.annotations.SerializedName

data class Payload(
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: String,
    @SerializedName("description")
    val description: String
)