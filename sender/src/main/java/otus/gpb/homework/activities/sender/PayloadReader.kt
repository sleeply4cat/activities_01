package otus.gpb.homework.activities.sender

import android.content.Context
import otus.gpb.homework.activities.api.Payload
import java.io.Closeable

class PayloadReader(val context: Context) {

    fun iterator(): PayloadIterator{
        return PayloadIterator()
    }

    inner class PayloadIterator:Iterator<Payload>, Closeable {
        private val reader = context.resources.openRawResource(R.raw.payload).bufferedReader()

        override fun hasNext(): Boolean {
            return reader.ready()
        }

        override fun next(): Payload {
            return Payload(
                reader.readLine(),
                reader.readLine(),
                reader.readLine()
            )
        }

        override fun close() {
            reader.close()
        }
    }
}