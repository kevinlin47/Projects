/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texttospeech;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Kevin Lin
 */
public class TextToSpeech {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TextToSpeech textToSpeech=new TextToSpeech();
        textToSpeech.go();
    }
    
    public void go()
    {
        buildGUI();
    }
    
    public void buildGUI()
    {
        JFrame mainFrame=new JFrame("Text to Speech");
        mainFrame.setSize(500,500);
        JPanel framePanel=new JPanel();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        
        JTextArea textArea=new JTextArea(10,25);
        JScrollPane scrollPane=new JScrollPane(textArea);
        framePanel.add(scrollPane);
        JButton speakButton=new JButton("Speak");
        JButton clearButton=new JButton("Clear");
        speakButton.addActionListener(new SpeakButtonListener());
        clearButton.addActionListener(new ClearButtonListener(textArea));
        framePanel.add(speakButton);
        framePanel.add(clearButton);
        

        mainFrame.getContentPane().add(BorderLayout.CENTER,framePanel);
        mainFrame.setVisible(true);
        
    }
    
    public class ClearButtonListener implements ActionListener
    {   
        JTextArea textArea;
        
        ClearButtonListener(JTextArea textArea)
        {
            this.textArea=textArea;
        }
        
        public void actionPerformed(ActionEvent ev)
        {
            textArea.setText("");
        }
    }
    
    public class SpeakButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ev)
        {
            
        }
    }

    
}
