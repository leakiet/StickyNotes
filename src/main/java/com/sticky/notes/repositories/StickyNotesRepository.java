package com.sticky.notes.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sticky.notes.models.StickyNotes;

@Repository
public interface StickyNotesRepository extends CrudRepository<StickyNotes, Long> {
  public List<StickyNotes> findAll();
}
