package otus.gpb.homework.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        findViewById<Button>(R.id.action_open_activity_b)
            .setOnClickListener { openActivityB() }
    }

    private fun openActivityB(){
        //TODO write stuff
    }
}