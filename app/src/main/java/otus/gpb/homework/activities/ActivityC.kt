package otus.gpb.homework.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ActivityC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)
        findViewById<Button>(R.id.action_open_activity_a)
            .setOnClickListener { openActivityA() }
        findViewById<Button>(R.id.action_open_activity_b)
            .setOnClickListener { openActivityB() }
        findViewById<Button>(R.id.action_close_activity_c)
            .setOnClickListener { closeActivityC() }
        findViewById<Button>(R.id.action_close_stack)
            .setOnClickListener { closeStack() }
    }

    private fun openActivityA(){
        //TODO write stuff
    }

    private fun openActivityB(){
        //TODO write stuff
    }

    private fun closeActivityC(){
        //TODO write stuff
    }

    private fun closeStack(){
        //TODO write stuff
    }
}