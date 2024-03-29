/*
 This file is part of the application Privacy Friendly Notes.
 Privacy Friendly Notes is free software:
 you can redistribute it and/or modify it under the terms of the
 GNU General Public License as published by the Free Software Foundation,
 either version 3 of the License, or any later version.
 Privacy Friendly Notes is distributed in the hope
 that it will be useful, but WITHOUT ANY WARRANTY; without even
 the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 See the GNU General Public License for more details.
 You should have received a copy of the GNU General Public License
 along with Privacy Friendly Notes. If not, see <http://www.gnu.org/licenses/>.
 */
package org.secuso.privacyfriendlynotes.ui;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.secuso.privacyfriendlynotes.room.DbContract;
import org.secuso.privacyfriendlynotes.R;
import org.secuso.privacyfriendlynotes.room.model.Note;
import org.secuso.privacyfriendlynotes.ui.adapter.NoteAdapter;
import org.secuso.privacyfriendlynotes.ui.main.MainActivityViewModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity that allows to interact with trashed notes.
 */

public class RecycleActivity extends AppCompatActivity {

    MainActivityViewModel mainActivityViewModel;
    SearchView searchView;
    NoteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);


        RecyclerView recyclerView = findViewById(R.id.recyclerViewRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);
        searchView = findViewById(R.id.searchViewFilterRecycle);

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getTrashedNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                adapter.setNotes(notes);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String newText) {
                applyFilterTrashed(newText);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                applyFilterTrashed(query);
                return true;
            }
        });

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                new AlertDialog.Builder(RecycleActivity.this)
                        .setTitle(String.format(getString(R.string.dialog_restore_title), note.getName()))
                        .setMessage(String.format(getString(R.string.dialog_restore_message), note.getName()))
                        .setNegativeButton(R.string.dialog_option_delete, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mainActivityViewModel.delete(note);
                                if (note.getType() == DbContract.NoteEntry.TYPE_AUDIO) {
                                    new File(getFilesDir().getPath()+"/audio_notes"+note.getContent() ).delete();
                                } else if (note.getType() == DbContract.NoteEntry.TYPE_SKETCH) {
                                    new File(getFilesDir().getPath()+"/sketches"+note.getContent() ).delete();
                                    new File(getFilesDir().getPath()+"/sketches"+ note.getContent().substring(0, note.getContent().length()-3) + "jpg").delete();
                                }
                            }
                        })
                        .setNeutralButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //do nothing
                            }
                        })
                        .setPositiveButton(R.string.dialog_option_restore, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                note.setIn_trash(0);
                                mainActivityViewModel.update(note);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });
        PreferenceManager.setDefaultValues(this, R.xml.pref_settings, false);
    }

    private void applyFilterTrashed(String filter){
        mainActivityViewModel.getTrashedNotesFiltered(filter).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                // Filter checklist notes
                List<Note> filteredNotes = new ArrayList<>();
                for(Note note: notes){
                    Boolean add = false;
                    if(note.getType() == 3){
                        try {
                            JSONArray content = new JSONArray(note.getContent());
                            for (int i=0; i < content.length(); i++) {
                                JSONObject o = content.getJSONObject(i);
                                if (o.getString("name").contains(filter) || note.getName().contains(filter)){
                                    add = true;
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else{
                        add = true;
                    }
                    if(add){
                        filteredNotes.add(note);
                    }
                }
                adapter.setNotes(filteredNotes);
            }
        });
    }
}
