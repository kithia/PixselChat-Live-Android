# PixselChat-Live (Android)
PixselChat Live (Android) UI Implementation

## Please find the following screens
- MainActivity.kt
- LanguageMenuFragment.kt
- TranslationFragment.kt

### MainActivity.kt
The initial page which displays the PixselChat Live logo and title

![MainActivity.kt UI](./images/MainActivity.png)

### LanguageFragment.kt
The PixselChat Live page which the end user can use to select their language of choice

![LanguageFragment.kt UI](./images/LanguageFragment.png)
![LanguageFragment.kt UI](./images/LanguageFragment-1.png)

The list of available languages can be edited programmatically, in Kotlin,
by either adding, removing or editing a value in the ```Language``` enum.

### TranslationFragment.kt
The PixselChat Live page for the live translation of the conference.

![TranslationFragment.kt UI](./images/TranslationFragment.png)
![TranslationFragment.kt UI](./images/TranslationFragment-1.png)

The meta data for end user can be edited programmatically, in Kotlin,
by setting the value of the fields of the ```Session``` object in ```LanguageRecyclerViewAdapter```,
For example,  ```Session(
                    ...
                    view.context.getString(R.string.<string_variable_name>),
                    ...
                ) ```
