package vcmsa.ci.musicapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    companion object{
        val songTitles = mutableListOf<String>()
        val artistNames = mutableListOf<String>()
        val songRating = mutableListOf<String>()
        val songComments = mutableListOf<String>()
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val txtStatus = findViewById<TextView>(R.id.txtStatus)
        val songTitle = findViewById<EditText>(R.id.edtSongTitle)
        val artistInput = findViewById<EditText>(R.id.edtArtistName)
        val ratingInput = findViewById<EditText>(R.id.edtRating)
        val commentInput = findViewById<EditText>(R.id.edtComment)

        val addToPlaylistButton = findViewById<Button>(R.id.btnAdd)
        val viewPlaylistButton = findViewById<Button>(R.id.btnView)
        val exitButton = findViewById<Button>(R.id.btnExit)

        addToPlaylistButton.setOnClickListener {
            val title = songTitle.text.toString().trim()
            val artist = artistInput.text.toString().trim()
            val rating = ratingInput.text.toString().trim()
            val commentText = commentInput.text.toString().trim()

            if (title.isEmpty() && artist.isEmpty() && rating.isEmpty()) {
                Toast.makeText(this, "All fields except comments are required!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            val ratingText = ratingInput.text.toString().trim()
            if ((rating == null || rating >= 0.toString() || rating <= 5.toString())) {
                Toast.makeText(
                    this, "Rating must be between 0 and 5!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            songTitles.add(title)
            artistNames.add(artist)
            songRating.add(rating)

            Log.d("MusicPlaylist", "add song:$title by $artist (rating: $rating")
            Toast.makeText(this, "Song Added!", Toast.LENGTH_SHORT).show()
            txtStatus.text = "Last added: $title by $artist(rating:$rating)"

            songTitle.text.clear()
            artistInput.text.clear()
            ratingInput.text.clear()
            commentInput.text.clear()

            viewPlaylistButton.setOnClickListener {
                startActivity(Intent(this,MainActivity2:: class.java))
            }
            exitButton.setOnClickListener {
                finish()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}