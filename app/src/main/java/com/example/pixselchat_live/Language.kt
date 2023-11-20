package com.example.pixselchat_live

/**
 * An [Enum] representing the supported languages
 * of PixselChat Live
 */
enum class Language {
    ENGLISH,
    FRENCH,
    DUTCH,
    SPANISH,
    ITALIAN,
    CHINESE,
    JAPANESE,
    RUSSIAN,
    ARABIC,
    FARSI;

    /**
     * Gets the string of the native noun
     * for a language, respectively
     */
    fun getNative(): String {
        return when (this) {
            ENGLISH -> "English"
            FRENCH -> "Français"
            DUTCH -> "Deutsch"
            SPANISH -> "Español"
            ITALIAN -> "Italiano"
            CHINESE -> "中國人"
            JAPANESE -> "日本語"
            RUSSIAN -> "Русский"
            ARABIC -> "عربي"
            FARSI -> "فارسی"
        }
    }

    /**
     * Gets the string of the English noun
     * for a language, respectively
     */
    fun getString(): String {
        return when (this) {
            ENGLISH -> "English"
            FRENCH -> "French"
            DUTCH -> "Dutch"
            SPANISH -> "Spanish"
            ITALIAN -> "Italian"
            CHINESE -> "Chinese"
            JAPANESE -> "Japanese"
            RUSSIAN -> "Russian"
            ARABIC -> "Arabic"
            FARSI -> "Farsi"
        }
    }
}