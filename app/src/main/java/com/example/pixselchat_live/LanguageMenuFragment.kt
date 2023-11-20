package com.example.pixselchat_live

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels

/**
 * A [Fragment] representing a list of [Language]
 * items.
 */
class LanguageMenuFragment : Fragment() {

    // Shared viewModel within TranslationActivity
    private val translationViewModel: TranslationViewModel by activityViewModels()

    companion object {
        @JvmStatic
        fun newInstance() = LanguageMenuFragment()
    }

    /**
     * Launches TranslationFragment
     */
    private fun launchTranslationFragment() {
        val translationFragment = TranslationFragment.newInstance()
        parentFragmentManager.beginTransaction()
            .replace(R.id.translationContainer, translationFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_language_menu, container, false)

        val backButton: ImageView = view.findViewById(R.id.fragmentLanguageBackButton)
        backButton.setOnClickListener { activity?.finish() }

        // Sets the recyclerView adapter
        val recycler: RecyclerView = view.findViewById(R.id.languageRecyclerView)
        with(recycler) {
            layoutManager = LinearLayoutManager(context)

            // Language items to be displayed
            val languageArray = Language.values()
            adapter = LanguageRecyclerViewAdapter(languageArray) { session ->
                /** Callback function to set the global session object and
                 * launch new Fragment with the session meta data */
                translationViewModel.setSession(session)
                launchTranslationFragment()
            }
        }
        return view
    }

}