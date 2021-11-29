package org.secuso.privacyfriendlynotes.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: NoteDatabase = NoteDatabase.getInstance(application)
    val allNotes: LiveData<List<Note>> = repository.noteDao().allNotes
    val activeNotes: LiveData<List<Note>> = repository.noteDao().allActiveNotes
    val trashedNotes: LiveData<List<Note>> = repository.noteDao().allTrashedNotes
    val allNotesAlphabetical: LiveData<List<Note>> = repository.noteDao().allNotesAlphabetical

    private var _notesFromCategoryLast: LiveData<List<Note?>?>? = null
    private var _notesFromCategory: MediatorLiveData<List<Note?>?> = MediatorLiveData<List<Note?>?>()


    fun insert(note: Note) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.noteDao().insert(note)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.noteDao().update(note)

        }
    }

    fun delete(note: Note) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.noteDao().delete(note)
        }
    }


    fun getNotesFromCategory(categoryID: Integer): LiveData<List<Note?>?>{
        viewModelScope.launch(Dispatchers.Default) {
            withContext(Dispatchers.Main) {
                if (_notesFromCategoryLast != null) {
                    _notesFromCategory.removeSource(_notesFromCategoryLast!!)
                }
            }
            _notesFromCategoryLast = repository.noteDao().notesFromCategory(categoryID)

            withContext(Dispatchers.Main) {
                _notesFromCategory.addSource(_notesFromCategoryLast!!) {
                    _notesFromCategory.postValue(it)
                }
            }
        }
            return _notesFromCategory
    }

}