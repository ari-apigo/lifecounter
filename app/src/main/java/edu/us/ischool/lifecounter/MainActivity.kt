package edu.us.ischool.lifecounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class Player(name: String) {
    val name = name
    var lifeTotal = 20
    var alive = lifeTotal > 0

    fun changeLifeTotal(change: Int) {
        lifeTotal += change
    }
}


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val players = arrayOf(Player("Player1"), Player ("Player2"), Player("Player3"), Player("Player4"))

        // access listView from XML file
        var mListView = findViewById<ListView>(R.id.playerList)

        // use custom adapter to layout player information
        val adapter = PlayerAdapter(this, players)
        mListView.adapter = adapter
    }
}