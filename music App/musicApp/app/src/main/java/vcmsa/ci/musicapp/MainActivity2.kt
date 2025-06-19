package vcmsa.ci.musicapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


@Suppress("UNUSED_EXPRESSION")
class MainActivity2 : AppCompatActivity() {
    private lateinit var txtPlaylist: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        txtPlaylist = findViewById(R.id.txtPlaylist)

        val showAllSongs = findViewById<Button>(R.id.btnShowAllSongs)
        val showTopRatedSongs = findViewById<Button>(R.id.btnShowTopRatedSongs)
        val back = findViewById<Button>(R.id.btnBack)


        showAllSongs.setOnClickListener {
            showAllSongs()
        }
        findViewById<Button>(R.id.btnShowTopRatedSongs).setOnClickListener {
            showTopRatedSongs()
        }
        back.setOnClickListener {
            finish()
        }
    }

    private fun showAllSongs() {
        val builder = StringBuilder()
        for (i in MainActivity.songTitles.indices) {
            builder.append("${MainActivity.songTitles[i]}-${MainActivity.artistNames[i]}- ratings${MainActivity.songRating[i]} - comments${MainActivity.songComments[i]}")
        }
        txtPlaylist.text = builder.toString().ifEmpty { "no song " }
    }


    private fun showTopRatedSongs() {
        val builder = StringBuilder()
        for (i in MainActivity.songRating.indices) {
            val song = null
            if (MainActivity.songRating[i] >= 4.toString()) {
                builder.run {("${MainActivity.artistNames[i]} - ${MainActivity.songRating[i]} - ${MainActivity.songComments[i]}") }
            }

            txtPlaylist.text = builder.toString().ifEmpty { "no top rated song yet" }
        }
    }
}










