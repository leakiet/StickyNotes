package com.sticky.notes.services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sticky.notes.models.StickyNotes;
import com.sticky.notes.repositories.StickyNotesRepository;

@Service
public class StickyNotesService {
  @Autowired
  private StickyNotesRepository notesRepo;

  public List<StickyNotes> findAll() {
    List<StickyNotes> notes = notesRepo.findAll();
    return notes;
  }

  public boolean saveNotes(StickyNotes note) {
    String content = note.getContent();
    if (content != null) {
      String lowerContent = content.toLowerCase();
      String[] forbiddenWords = {"kiet", "luc", "trung", "quyen", "huong", "cukcu"};
      for (String word : forbiddenWords) {
        if (lowerContent.contains(word)) {
          return false;
        }
      }
    }
    notesRepo.save(note);
    return true;
  }

  public void saveEditedNotes(StickyNotes note, Long id) {
    StickyNotes editNote = notesRepo.findById(id).get();
    editNote.setTitle(note.getTitle());
    editNote.setContent(note.getContent());
    notesRepo.save(editNote);
  }

  public void deleteNotes(StickyNotes notes) {
    notesRepo.delete(notes);
  }

  public List<StickyNotes> findByUserId(Long id) {
    List<StickyNotes> notes = notesRepo.findAll();
    notes = notes.stream().filter(note -> note.getUser().getId().equals(id)).collect(Collectors.toList());
    return notes;
  }
}
