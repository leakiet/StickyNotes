package com.sticky.notes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.sticky.notes.models.StickyNotes;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import com.sticky.notes.services.StickyNotesService;
import com.sticky.notes.services.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sticky.notes.models.User;

@Controller
public class StickyNotesController {

  @Autowired
  private StickyNotesService noteService;

  @Autowired
  private UserService userService;

  private String currentUser;

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/signup")
  public String signUp() {
    return "signup";
  }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("username", null);
    return "login";
  }

  @GetMapping("/dashboard")
  public String dashboard() {
    return "dashboard";
  }

  @GetMapping("/addnotes")
  public String addNotes() {
    return "addnotes";
  }

  @PostMapping("/saveUser")
  public String saveUser(@ModelAttribute User user, Model model) {
    try {
      userService.saveUser(user);
      model.addAttribute("successMessage", "User details saved successfully.");
    } catch (Exception e) {
      model.addAttribute("errorMessage", "Error saving user details. Please try again.");
    }
    return "signup";
  }

  @PostMapping("/loginuser")
  public String loginUser(String email, String password,
      Model model) {
    if (userService.isValidUser(email, password)) {
      this.currentUser = email;
      return "/dashboard";
    } else {
      this.currentUser = null;
      model.addAttribute("error", "Invalid email orpassword. Please try again.");
      return "login";
    }
  }

  @PostMapping("/saveNotes")
  public String submitNotes(@Valid StickyNotes notes, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      model.addAttribute("errorMessage", "Invalid format! The note title must be in the format <3 digits>_<10alphanumeric characters>. For example, 010_Note10");
      return "addnotes";
    } else {
      User user = userService.findByEmail(this.currentUser);
      notes.setUser(user);
      boolean isSaved = noteService.saveNotes(notes);
      if (!isSaved) {
        model.addAttribute("errorMessage", "Content is not allowed");
        return "addnotes";
      } else {
        return "addnotes";
      }
    }
  }

  @GetMapping("/viewnote")
  public String viewNotes(Model model) {
    // List<StickyNotes> notesList = noteService.findAll();
    User user = userService.findByEmail(this.currentUser);
    System.out.println("user id: " + user.getId());
    List<StickyNotes> notesList = noteService.findByUserId(user.getId());
    model.addAttribute("notes", notesList);
    System.out.println(notesList.toString());
    return "viewnote";
  }

  @PostMapping("/editnote/{id}")
  public String editNotes(@PathVariable("id") Long id, @ModelAttribute StickyNotes notes,
      RedirectAttributes redirectAttributes) {
    noteService.saveEditedNotes(notes, id);
    return "viewnote";
  }

  @PostMapping("/deletenote/{id}")
  public String deleteNotes(@PathVariable("id") Long id, @ModelAttribute StickyNotes notes) {
    notes.setId(id);
    noteService.deleteNotes(notes);
    return "addnotes";
  }

  @GetMapping("/after_login_dashboard") // New mapping for after login dashboard
  public String afterLoginDashboard() {
    return "dashboard";
  }
}
