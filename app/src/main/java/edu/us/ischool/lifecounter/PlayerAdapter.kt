package edu.us.ischool.lifecounter

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible

// consulted https://www.raywenderlich.com/155-android-listview-tutorial-with-kotlin
class PlayerAdapter(private val context: Context,
                    private val dataSource: Array<Player>,
                    val loserTV: TextView) : BaseAdapter() {
    private val inflater: LayoutInflater
        = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    // tells ListView how many items to display
    override fun getCount(): Int {
        return dataSource.size
    }

    // returns an item (a player) from the data source to be placed in a given position
    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    // defines unique ID (position of the item) for each list row
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // creates a view to be used as a row in the list
        // define what information displays and where it sits on the ListView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Get view for row item
        val rowView = inflater.inflate(R.layout.list_item_player, parent, false)

        // Get name element
        val nameTextView = rowView.findViewById(R.id.player_name) as TextView

        // Get life total label element
        val lifeTotalLabelTextView = rowView.findViewById(R.id.player_lifeTotal_label) as TextView

        // Get life total element
        val lifeTotalTextView = rowView.findViewById(R.id.player_lifeTotal) as TextView

        // Get "-" button element
        val minusBtn = rowView.findViewById(R.id.btn_minus) as View

        // Get "+" button element
        val plusBtn = rowView.findViewById(R.id.btn_plus) as View

        // Get "-5" button element
        val minus5Btn = rowView.findViewById(R.id.btn_minus5) as View

        // Get "+5" button element
        val plus5Btn = rowView.findViewById(R.id.btn_plus5) as View

        // get corresponding player for current row
        val player = getItem(position) as Player

        // update row view's text views to display player info
        nameTextView.text = player.name
        lifeTotalLabelTextView.text = context.getString(R.string.life_total_label)
        lifeTotalTextView.text = player.lifeTotal.toString()

        // set up buttons
        minusBtn.setOnClickListener {
            player.changeLifeTotal(-1)
            // update life total display
            lifeTotalTextView.text = player.lifeTotal.toString()
            // check if player is still alive after life total change
            checkAlive(player, loserTV)
        }

        plusBtn.setOnClickListener {
            player.changeLifeTotal(1)
            // update life total display
            lifeTotalTextView.text = player.lifeTotal.toString()
            // check if player is still alive after life total change
            checkAlive(player, loserTV)
        }

        minus5Btn.setOnClickListener {
            player.changeLifeTotal(-5)
            // update life total display
            lifeTotalTextView.text = player.lifeTotal.toString()
            // check if player is still alive after life total change
            checkAlive(player, loserTV)
        }

        plus5Btn.setOnClickListener {
            player.changeLifeTotal(5)
            // update life total display
            lifeTotalTextView.text = player.lifeTotal.toString()
            // check if player is still alive after life total change
            checkAlive(player, loserTV)
        }

        return rowView
    }

    // if player is no longer alive, show lose message
    fun checkAlive(player: Player, loserTV: TextView) {
        if (!player.lifeStatus()) {
            // get lose message element
            loserTV.visibility = View.VISIBLE
            loserTV.text = context.getString(R.string.lose_msg, player.name)

            // use a handler to delay hiding the lose message
            val handler = Handler()
            handler.postDelayed({
                loserTV.visibility = View.INVISIBLE
            }, 2000)
        }
    }
}