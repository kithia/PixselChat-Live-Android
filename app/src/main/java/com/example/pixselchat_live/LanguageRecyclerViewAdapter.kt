package com.example.pixselchat_live

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.pixselchat_live.databinding.FragmentLanguageMenuItemBinding
import java.time.LocalDate

/**
 * [RecyclerView.Adapter] that can display a [Language] item.
 */
class LanguageRecyclerViewAdapter(
    private val values: Array<Language>, private val onRadioSelected: (Session) -> Unit
) : RecyclerView.Adapter<LanguageRecyclerViewAdapter.ViewHolder>() {

    private var lastSelectedRadio: RadioButton? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentLanguageMenuItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val language = values[position]
        with(holder.radioButton) {
            text = language.getNative()

            // Necessary for RTL languages to display LTR
            textDirection = View.TEXT_DIRECTION_LTR

            setOnCheckedChangeListener { view, isChecked ->
                /** Removes other checked radioButton (if applicable)
                    when this radioButton is selected */
                if (isChecked && lastSelectedRadio != this) {
                    lastSelectedRadio?.isChecked = false
                }
                lastSelectedRadio = this

                /** Creates new session to be given to
                    the shared, global viewModel */
                val session = Session(
                    language,
                    view.context.getString(R.string.default_forum),
                    LocalDate.now(),
                    view.context.getString(R.string.default_locale),
                    view.context.getString(R.string.default_presenter),
                    view.context.getString(R.string.default_title),
                    view.context.getString(R.string.default_translation),
                    null,
                    null
                )
                onRadioSelected(session)
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentLanguageMenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val radioButton: RadioButton = binding.languageRadioButton
    }

}