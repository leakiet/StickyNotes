package com.sticky.notes;

import javax.swing.*;

public class Backgroundimage extends JFrame {
  JFrame frame;
  JLabel displayField;

  public Backgroundimage() {
    frame = new JFrame("Sticky-Notes App");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    try {
      ImageIcon BGimage = new ImageIcon("/Users/leakiet/Images/StickyNotes.png");
      displayField = new JLabel(BGimage);
      frame.add(displayField);
    } catch (Exception e) {
      System.out.println("Image cannot be found");
    }
    frame.setSize(1600, 1600);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    Backgroundimage i = new Backgroundimage();
  }
}
