package edu.java.lab1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Class for work to data
 */
public class Data {

    /**
     * Method for add new position in table
     * @param model - model of table
     * @param frame - object type of window
     */
    public void Add(DefaultTableModel model,JFrame frame) {
        JDialog window = new JDialog(frame,"Add riders");
        window.setModal(true);
        window.setSize(500,500);
        window.setLocation(300,300);

        Container contentPane = window.getContentPane();

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        Component Team = new JLabel("Team");
        JTextField tTeam = new JTextField(15);

        contentPane.add(Team);
        contentPane.add(tTeam);

        layout.putConstraint(SpringLayout.WEST , Team, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Team, 25,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tTeam, 25,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tTeam, 55,
                SpringLayout.EAST , Team);
        

        Component Designer = new JLabel("Designer");
        JTextField tDesigner = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , Designer, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Designer, 50,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tDesigner, 50,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tDesigner, 34,
                SpringLayout.EAST , Designer);

        contentPane.add(Designer);
        contentPane.add(tDesigner);

        Component Chassis = new JLabel("Chassis");
        JTextField tChassis = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , Chassis, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Chassis, 75,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tChassis, 75,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tChassis, 41,
                SpringLayout.EAST ,Chassis);

        contentPane.add(Chassis);
        contentPane.add(tChassis);

        Component Track = new JLabel("Track");
        JTextField tTrack = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , Track, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Track, 100,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tTrack, 100,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tTrack, 54,
                SpringLayout.EAST , Track      );

        contentPane.add(Track);
        contentPane.add(tTrack);

        Component Power_plant = new JLabel("Power plant");
        JTextField tPower_plant = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , Power_plant, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Power_plant, 125,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tPower_plant, 125,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tPower_plant, 19,
                SpringLayout.EAST , Power_plant );

        contentPane.add(Power_plant);
        contentPane.add(tPower_plant);
        Component Driver_1 = new JLabel("Driver 1");
        JTextField tDriver_1 = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , Driver_1, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Driver_1, 150,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tDriver_1, 150,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tDriver_1, 40,
                SpringLayout.EAST ,Driver_1);

        contentPane.add(Driver_1);
        contentPane.add(tDriver_1);
        Component Driver_2 = new JLabel("Driver 2");
        JTextField tDriver_2 = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , Driver_2, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Driver_2, 175,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tDriver_2, 175,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tDriver_2, 40,
                SpringLayout.EAST , Driver_2);

        contentPane.add(Driver_2);
        contentPane.add( tDriver_2 );
        Component Status = new JLabel("Status");
        String S[] = {"Winer","Conpetitor"};
        JComboBox<String> comboAvailability = new JComboBox<>(S);

        layout.putConstraint(SpringLayout.WEST ,Status, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Status, 200,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, comboAvailability, 200,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , comboAvailability, 50,
                SpringLayout.EAST , Status);

        contentPane.add(Status);
        contentPane.add(comboAvailability);
      

        
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");

        layout.putConstraint(SpringLayout.WEST , ok, 60,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, ok, 250,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, cancel, 250,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , cancel, 85,
                SpringLayout.EAST , ok      );

        contentPane.add(ok);
        contentPane.add(cancel);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector <String> s = new Vector<>();
                s.add(tTeam.getText());
                s.add(tDesigner.getText());
                s.add(tChassis.getText());
                s.add(tTrack.getText());
                s.add(tPower_plant.getText());
                s.add(tDriver_1.getText());
                s.add(tDriver_2.getText());
                s.add(S[comboAvailability.getSelectedIndex()]);               
            
         
  
               

                int count = 0;
                for(String s1:s) {
                    if((s1.length() > 0) && (!s1.equals(" "))) {
                        count++;
                    }
                }

                if(count == 8) {
                    model.addRow(s);
                    window.dispose();
                }
                else {
                    JOptionPane.showMessageDialog (frame,"Not all fields are filled in!");
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(window,"Are you sure you want to close the window?","Message",JOptionPane.YES_NO_OPTION);
                if(n == JOptionPane.YES_OPTION) {
                    window.dispose();
                }
            }
        });

        window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        window.setVisible(true);
    }

    /**
     * method for delete position
     * @param model - model of table
     * @param frame - object type of window
     */
    public void Delete(DefaultTableModel model, JFrame frame) {
        try {
            model.removeRow(model.getRowCount()-1);
        }
        catch (IndexOutOfBoundsException ev) {
            JOptionPane.showMessageDialog (frame,"A mistake! The table is empty!","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
   

}
