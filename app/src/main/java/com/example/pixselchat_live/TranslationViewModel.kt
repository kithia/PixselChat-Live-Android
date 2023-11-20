package com.example.pixselchat_live

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * A [ViewModel] representing a session of PixselChat Live
 */
class TranslationViewModel : ViewModel() {
    private val _session = MutableLiveData<Session>()
    val session: LiveData<Session> get() = _session

    fun setSession(session: Session) {
        _session.value = session
    }
}