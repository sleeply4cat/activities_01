package otus.gpb.homework.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        findViewById<Button>(R.id.action_open_activity_c)
            .setOnClickListener { openActivityC() }
    }

    private fun openActivityC(){
        //TODO write stuff
    }
}