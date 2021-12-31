package com.aplikasi.uas_rpl_android

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException


class SpeakerAccess: AppCompatActivity() {
    // creating a variable for
    // button and media player
    lateinit var playBtn: Button
    lateinit var pauseBtn: Button
    var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speaker_access)

        // initializing our buttons
        playBtn = findViewById(R.id.idBtnPlay)
        pauseBtn = findViewById(R.id.idBtnPause)

        // setting on click listener for our play and pause buttons.
        playBtn.setOnClickListener(View.OnClickListener { // calling method to play audio.
            playAudio()
        })
        pauseBtn.setOnClickListener(View.OnClickListener {
            // checking the media player
            // if the audio is playing or not.
            if (mediaPlayer!!.isPlaying) {
                // pausing the media player if media player
                // is playing we are calling below line to
                // stop our media player.
                mediaPlayer!!.stop()
                mediaPlayer!!.reset()
                mediaPlayer!!.release()

                // below line is to display a message
                // when media player is paused.
                Toast.makeText(this@SpeakerAccess, "Audio has been paused", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // this method is called when media
                // player is not playing.
                Toast.makeText(this@SpeakerAccess, "Audio has not played", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun playAudio() {
        val audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

        // initializing media player
        mediaPlayer = MediaPlayer()

        // below line is use to set the audio
        // stream type for our media player.
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)

        // below line is use to set our
        // url to our media player.
        try {
            mediaPlayer!!.setDataSource(audioUrl)
            // below line is use to prepare
            // and start our media player.
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        // below line is use to display a toast message.
        Toast.makeText(this, "Audio started playing..", Toast.LENGTH_SHORT).show()
    }
}