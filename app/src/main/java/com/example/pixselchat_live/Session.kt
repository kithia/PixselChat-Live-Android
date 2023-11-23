package com.example.pixselchat_live

import java.time.LocalDate

/**
 * Data [Class] representing a PixselChat Live session
 */
data class Session(
    var language:Language,
    var forum:String,
    var date:LocalDate?,
    var locale: String,
    var presenter: String,
    var title: String,
    var translation: String,
    var originalAudioPath: String?,
    var translatedAudioPath: String?
)
