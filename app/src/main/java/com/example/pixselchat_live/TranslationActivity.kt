package com.example.pixselchat_live

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

/**
 * An Activity for [LanguageMenuFragment] and [TranslationFragment]
 */
class TranslationActivity : AppCompatActivity() {

    // Shared viewModel within TranslationActivity
    private val translationViewModel: TranslationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_translation)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.translationContainer, LanguageMenuFragment.newInstance())
                .commit()
        }
    }
}