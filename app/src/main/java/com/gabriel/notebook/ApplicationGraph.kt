package com.gabriel.notebook

import android.app.Application
import android.content.Context
import com.gabriel.data.RoomModule
import com.gabriel.data.datasources.NotesDao
import com.gabriel.data.datasources.impl.NotesLocalDataSource
import com.gabriel.data.repositories.NotesRepository
import com.gabriel.notebook.features.addNotes.AddNotesFragment
import com.gabriel.notebook.features.addNotes.AddNotesViewModel
import com.gabriel.notebook.features.notesList.NotesListFragment
import com.gabriel.notebook.features.viewNote.ViewNoteFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, RoomModule::class])
interface ApplicationGraph {

    fun inject(addNotesFragment: AddNotesFragment)
    fun inject(viewNoteFragment: ViewNoteFragment)
    fun inject(notesListFragment: NotesListFragment)

}

@Module
class ApplicationModule(private var notesApplication: NotesApplication) {
    @Provides
    fun provideApplication(): Application = notesApplication

    @Provides
    fun provideApplicationContext(): Context = notesApplication
}