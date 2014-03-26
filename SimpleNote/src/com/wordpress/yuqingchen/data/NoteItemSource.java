package com.wordpress.yuqingchen.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import android.content.Context;
import android.content.SharedPreferences;


public class NoteItemSource {
	
	private static final String PREFKEY="notes";
	private SharedPreferences notePrefs;
	
	public NoteItemSource(Context context){
		notePrefs=context.getSharedPreferences(PREFKEY, Context.MODE_PRIVATE);
	}
	
	public List<NoteItem> findAll(){
		Map<String, ?> noteMap = notePrefs.getAll();
		
		SortedSet<String> keys = new TreeSet<String>(noteMap.keySet());
		
		
		List<NoteItem> notelist = new ArrayList<NoteItem>();
		for(String key : keys){
			NoteItem note = new NoteItem();
			note.setKey(key);
			note.setText((String) noteMap.get(key));
			notelist.add(note);
		}
		
		return notelist;
	}
	
	public boolean update(NoteItem note){
		
		SharedPreferences.Editor editor = notePrefs.edit();
		editor.putString(note.getKey(), note.getText());
		editor.commit();
		
		return true;
	}
	
	public boolean remove(NoteItem note){
		if (notePrefs.contains(note.getKey())) {
			SharedPreferences.Editor editor = notePrefs.edit();
			editor.remove(note.getKey());
			editor.commit();
		}
				
		
		return true;
	}
	
	

}
