package com.example.lab_week_11

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class SettingsViewModel(private val settingsStore: SettingsStore) : ViewModel() {

    // The text live data is used to notify the activity when the text changes
    private val _textLiveData = MutableLiveData<String>()
    val textLiveData: LiveData<String> = _textLiveData

    init {
        // Launch a coroutine to get the text from the data store asynchronously
        viewModelScope.launch {
            settingsStore.text.collect { value ->
                _textLiveData.postValue(value)
            }
        }
    }

    // Save the text to the data store
    fun saveText(text: String) {
        // Launch a coroutine to save the text asynchronously
        viewModelScope.launch {
            settingsStore.saveText(text)
        }
    }
}