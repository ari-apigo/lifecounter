package edu.us.ischool.lifecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast

class Player(name: String) {
    val name = name
    var lifeTotal = 20

    fun changeLifeTotal(change: Int) {
        lifeTotal += change
    }

    fun lifeStatus(): Boolean { return lifeTotal > 0 }
}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val players = arrayOf(Player("Fred"), Player ("Wilma"),
            Player("Barney"), Player("Betty"))

        // access listView from XML file
        var mListView = findViewById<ListView>(R.id.playerList)
        val loserTextView = findViewById<TextView>(R.id.lose_msg)

        // use custom adapter to layout player information
        val adapter = PlayerAdapter(this, players, loserTextView)
        mListView.adapter = adapter
    }

    fun checkAlive(player: Player) {
        if (!player.lifeStatus()) {
            // get lose message element
            val loserTextView = findViewById<TextView>(R.id.lose_msg)
            loserTextView.visibility = View.VISIBLE
            loserTextView.text = getString(R.string.lose_msg, player.name)

            // use a handler to delay hiding the lose message
            val handler = Handler()
            handler.postDelayed({
                loserTextView.visibility = View.INVISIBLE
            }, 2000)
        }
    }
}