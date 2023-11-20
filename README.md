# PixselChat-Live (Android)
PixselChat Live (Android) UI Implementation

## Please find the following screens
- MainActivity.kt
- LanguageMenuFragment.kt
- TranslationFragment.kt

### MainActivity.kt
The initial page which displays the PixselChat Live logo and title

<img src="./images/MainActivity.png" alt="MainActivity.kt" width="300"/>

### LanguageFragment.kt
The PixselChat Live page which the end user can use to select their language of choice

<img src="./images/LanguageFragment.png" alt="LanguageFragment.kt" width="300"/>
<img src="./images/LanguageFragment-1.png" alt="LanguageFragment.kt-1" width="300"/>

The list of available languages can be edited programmatically, in Kotlin,
by either adding, removing or editing a value in the ```Language``` enum.

### TranslationFragment.kt
The PixselChat Live page for the live translation of the conference.

<img src="./images/TranslationFragment.png" alt="TranslationFragment.kt" width="300"/>
<img src="./images/TranslationFragment-1.png" alt="TranslationFragment.kt-1" width="300"/>

The meta data for end user can be edited programmatically, in Kotlin,
by setting the value of the fields of the ```Session``` object in ```LanguageRecyclerViewAdapter```.

For example,  ```Session(
                    ...
                    view.context.getString(R.string.<string_variable_name>),
                    ...
                ) ```
