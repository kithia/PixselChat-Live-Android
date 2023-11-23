package com.example.pixselchat_live

import android.media.MediaPlayer
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import java.time.format.DateTimeFormatter

/**
 * A [Fragment] representing the translation page of
 * PixselChat Live
 */
class TranslationFragment : Fragment() {

    private val minFontSize = 10
    private val maxFontSize = 55
    private val fontChangeStep = 2

    private var originalMediaPlayer = MediaPlayer()
    private var translatedMediaPlayer = MediaPlayer()

    // Shared viewModel within TranslationActivity
    private val translationViewModel: TranslationViewModel by activityViewModels()

    companion object {
        fun newInstance() = TranslationFragment()
    }

    /**
     * Mutes an original or translated audio file
     */
    private fun muteAudio(button: ImageView, player: MediaPlayer? = null) {
        // Uncomment to mute media player
        // mediaPlayer.setVolume(0f, 0f)
        button.setImageResource(R.drawable.volume_mute)
    }

    /**
     * Un-mutes an original or translated audio file
     */
    private fun unMuteAudio(button: ImageView, volume: Int, player: MediaPlayer? = null) {
        // Uncomment to set media player volume
        // mediaPlayer.setVolume(volume.toFloat(), volume.toFloat())
        button.setImageResource(R.drawable.volume)
    }

    /**
     * Stops an original or translated audio file
     */
    private fun stopAudio(button: ImageView, volumeControl: SeekBar, mediaPlayer: MediaPlayer? = null) {
        // Uncomment to stop media player
        // mediaPlayer.stop()
        button.setImageResource(R.drawable.play_arrow)
        button.tag = R.drawable.play_arrow
        volumeControl.isEnabled = false
    }

    /**
     * Plays an original or translated audio file
     */
    private fun playAudio(button: ImageView, control: SeekBar, mediaPlayer: MediaPlayer? = null) {
        // Uncomment to start media player
        // mediaPlayer.start()
        button.setImageResource(R.drawable.stop)
        button.tag = R.drawable.stop
        control.isEnabled = true
    }

    /**
     * Converts duration (Milliseconds) to length (mm:ss)
     */
    private fun durationToString(duration: Int): String {
        val minutes = duration / 1000 / 60
        val seconds = duration / 1000 % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_translation, container, false)
        translationViewModel.session.observe(viewLifecycleOwner) { session ->
            /** Session meta data **/
            val backButton: ImageView = view.findViewById(R.id.fragmentTranslationBackButton)
            val presenterText: TextView = view.findViewById(R.id.presenterText)
            val titleText: TextView = view.findViewById(R.id.titleText)
            // Formats date object to dd/MM/yy
            val forumDateLocale: TextView = view.findViewById(R.id.forumDateLocalText)
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            val formattedDate = if (session.date != null) session.date!!.format(formatter) else ""

            backButton.setOnClickListener { requireActivity().supportFragmentManager.popBackStack() }
            presenterText.text = getString(R.string.presenter, session.presenter)
            titleText.text = getString(R.string.title, session.title)
            forumDateLocale.text =
                getString(R.string.forum_date_locale, session.forum, formattedDate, session.locale)

            /** Translation Area **/
            val translationText: TextView = view.findViewById(R.id.translationEditText)
            translationText.hint = null
            translationText.text = session.translation

            /** Controls area **/
            val fontPlusImage: ImageView = view.findViewById(R.id.fontPlusImage)
            val fontMinusImage: ImageView = view.findViewById(R.id.fontMinusImage)
            val listeningRecordingLight: View = view.findViewById(R.id.recordingLightView)
            val recordButton: ImageView = view.findViewById(R.id.recordButtonImage)
            val stopRecordButton: ImageView = view.findViewById(R.id.stopRecordButtonImage)

            fontPlusImage.setOnClickListener { _ ->
                val currentSpSize = translationText.textSize / resources.displayMetrics.density
                if (currentSpSize + fontChangeStep <= maxFontSize) {
                    translationText.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentSpSize + fontChangeStep)
                }
            }
            fontMinusImage.setOnClickListener { _ ->
                val currentSpSize = translationText.textSize / resources.displayMetrics.density
                if (currentSpSize - fontChangeStep >= minFontSize) {
                    translationText.setTextSize(TypedValue.COMPLEX_UNIT_SP, currentSpSize - fontChangeStep)
                }
            }
            recordButton.setOnClickListener { _ ->
                // TODO: Implement start recording functionality
                listeningRecordingLight.setBackgroundResource(R.drawable.recording_circle)
            }
            stopRecordButton.setOnClickListener { _ ->
                // TODO: Implement stop recording functionality
                listeningRecordingLight.setBackgroundResource(R.drawable.listening_circle)
            }

            /** Audio players area **/
            // Uncomment to create a media player object
            // originalMediaPlayer = MediaPlayer.create(requireContext(), R.raw.<audio_file_name>)
            val originalVolumeControl: SeekBar = view.findViewById(R.id.originalVolumeBar)
            val originalPlayStopButton: ImageView = view.findViewById(R.id.originalPlayStopButton)
            val originalVolumeButton: ImageView = view.findViewById(R.id.originalVolumeButton)

            // Uncomment to create a media player object
            // translatedMediaPlayer = MediaPlayer.create(requireContext(), R.raw.<audio_file_name>)
            val translatedAudioLength = view.findViewById<TextView>(R.id.translatedAudioLengthTextView)
            val translatedVolumeControl: SeekBar = view.findViewById(R.id.translatedVolumeBar)
            val translatedPlayStopButton: ImageView = view.findViewById(R.id.translatedPlayStopButton)
            val translatedVolumeButton: ImageView = view.findViewById(R.id.translatedVolumeButton)

            // Uncomment to assign audio length/ duration
            // originalAudioLength.text = durationToString(originalAudioLength.duration)
            originalPlayStopButton.tag = R.drawable.play_arrow
            originalPlayStopButton.setOnClickListener { _ ->
                if (originalPlayStopButton.tag == R.drawable.play_arrow) {
                    stopAudio(translatedPlayStopButton, translatedVolumeControl)
                    playAudio(originalPlayStopButton, originalVolumeControl)
                }

                else if (originalPlayStopButton.tag == R.drawable.stop) {
                    stopAudio(originalVolumeButton, originalVolumeControl)
                }
            }
            originalVolumeControl.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (progress == 0) { muteAudio(originalVolumeButton) }
                    else { unMuteAudio(originalVolumeButton, progress) }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            // Uncomment to assign audio length/ duration
            // translatedAudioLength.text = durationToString(translatedMediaPlayer.duration)
            translatedPlayStopButton.tag = R.drawable.play_arrow
            translatedPlayStopButton.setOnClickListener { _ ->
                if (translatedPlayStopButton.tag == R.drawable.play_arrow) {
                    stopAudio(originalPlayStopButton, originalVolumeControl)
                    playAudio(translatedPlayStopButton, translatedVolumeControl)
                }

                else if (translatedPlayStopButton.tag == R.drawable.stop) {
                    stopAudio(translatedPlayStopButton, translatedVolumeControl)
                }
            }
            translatedVolumeControl.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (progress == 0) { muteAudio(translatedVolumeButton) }
                    else { unMuteAudio(translatedVolumeButton, progress) }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })
        }

        return view
    }

    /**
     * Stops and releases all media players
     */
    override fun onDestroy() {
        super.onDestroy()

        originalMediaPlayer.stop()
        translatedMediaPlayer.stop()

        originalMediaPlayer.release()
        translatedMediaPlayer.release()
    }

}