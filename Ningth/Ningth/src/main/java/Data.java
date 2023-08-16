import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * class for work to data
 */
public class Data {

    /**
     * method for add new position in table
     * @param model - model of table
     * @param frame - object type of window
     */
    public void AddWeapon(DefaultTableModel model,JFrame frame) {
    	 final JDialog window = new JDialog(frame,"Add position");
         window.setModal(true);
         window.setSize(350,325);
         window.setLocation(300,300);
         window.setResizable(false);

         Container contentPane = window.getContentPane();

         SpringLayout layout = new SpringLayout();
         contentPane.setLayout(layout);

         Component Team = new JLabel("Team");
         final JTextField tTeam = new JTextField(15);

         contentPane.add(Team);
         contentPane.add(tTeam);

         layout.putConstraint(SpringLayout.WEST , Team, 10,
                 SpringLayout.WEST , contentPane);
         layout.putConstraint(SpringLayout.NORTH, Team, 25,
                 SpringLayout.NORTH, contentPane);

         layout.putConstraint(SpringLayout.NORTH, tTeam, 25,
                 SpringLayout.NORTH, contentPane);
         layout.putConstraint(SpringLayout.WEST , tTeam, 60,
                 SpringLayout.EAST , Team      );

         Component Desiner = new JLabel("Desiner");
         final JTextField tDesiner = new JTextField(15);

         layout.putConstraint(SpringLayout.WEST , Desiner, 10,
                 SpringLayout.WEST , contentPane);
         layout.putConstraint(SpringLayout.NORTH, Desiner, 50,
                 SpringLayout.NORTH, contentPane);

         layout.putConstraint(SpringLayout.NORTH, tDesiner, 50,
                 SpringLayout.NORTH, contentPane);
         layout.putConstraint(SpringLayout.WEST , tDesiner, 60,
                 SpringLayout.EAST , Team      );

         contentPane.add(Desiner);
         contentPane.add(tDesiner);

         Component Chassis = new JLabel("Chassis");
         final JTextField tChassis = new JTextField(15);

         layout.putConstraint(SpringLayout.WEST , Chassis, 10,
                 SpringLayout.WEST , contentPane);
         layout.putConstraint(SpringLayout.NORTH, Chassis, 75,
                 SpringLayout.NORTH, contentPane);

         layout.putConstraint(SpringLayout.NORTH, tChassis, 75,
                 SpringLayout.NORTH, contentPane);
         layout.putConstraint(SpringLayout.WEST , tChassis, 47,
                 SpringLayout.EAST , Chassis      );

         contentPane.add(Chassis);
         contentPane.add(tChassis);

         Component Track = new JLabel("Track");
         final JTextField tTrack = new JTextField(15);

         layout.putConstraint(SpringLayout.WEST , Track, 10,
                 SpringLayout.WEST , contentPane);
         layout.putConstraint(SpringLayout.NORTH, Track, 100,
                 SpringLayout.NORTH, contentPane);

         layout.putConstraint(SpringLayout.NORTH, tTrack, 100,
                 SpringLayout.NORTH, contentPane);
         layout.putConstraint(SpringLayout.WEST , tTrack, 47,
                 SpringLayout.EAST , Chassis      );

         contentPane.add(Track);
         contentPane.add(tTrack);

         Component Power_plant = new JLabel("Power plant");
         final JTextField tPower_plant = new JTextField(15);

         layout.putConstraint(SpringLayout.WEST , Power_plant, 10,
                 SpringLayout.WEST , contentPane);
         layout.putConstraint(SpringLayout.NORTH, Power_plant, 125,
                 SpringLayout.NORTH, contentPane);

         layout.putConstraint(SpringLayout.NORTH, tPower_plant, 125,
                 SpringLayout.NORTH, contentPane);
         layout.putConstraint(SpringLayout.WEST , tPower_plant, 25,
                 SpringLayout.EAST , Power_plant);

         contentPane.add(Power_plant);
         contentPane.add(tPower_plant);

         Component Driver_1 = new JLabel("Driver 1");
         final JTextField tDriver_1 = new JTextField(15);

         layout.putConstraint(SpringLayout.WEST , Driver_1, 10,
                 SpringLayout.WEST , contentPane);
         layout.putConstraint(SpringLayout.NORTH, Driver_1, 150,
                 SpringLayout.NORTH, contentPane);

         layout.putConstraint(SpringLayout.NORTH, tDriver_1, 150,
                 SpringLayout.NORTH, contentPane);
         layout.putConstraint(SpringLayout.WEST , tDriver_1,47,
                 SpringLayout.EAST , Driver_1      );

         contentPane.add(Driver_1);
         contentPane.add(tDriver_1);

         Component Driver_2 = new JLabel("Driver 2");
         final JTextField tDriver_2 = new JTextField(15);

         layout.putConstraint(SpringLayout.WEST , Driver_2, 10,
                 SpringLayout.WEST , contentPane);
         layout.putConstraint(SpringLayout.NORTH, Driver_2, 175,
                 SpringLayout.NORTH, contentPane);

         layout.putConstraint(SpringLayout.NORTH, tDriver_2, 175,
                 SpringLayout.NORTH, contentPane);
         layout.putConstraint(SpringLayout.WEST , tDriver_2, 47,
                 SpringLayout.EAST , Driver_2     );

         contentPane.add(Driver_2);
         contentPane.add(tDriver_2);

         Component Status = new JLabel("Status");
         final String Av[] = {"Winer","Competitor"};
         final JComboBox comboStatus = new JComboBox<>(Av);

         layout.putConstraint(SpringLayout.WEST , Status, 10,
                 SpringLayout.WEST , contentPane);
         layout.putConstraint(SpringLayout.NORTH, Status, 200,
                 SpringLayout.NORTH, contentPane);

         layout.putConstraint(SpringLayout.NORTH, comboStatus, 200,
                 SpringLayout.NORTH, contentPane);
         layout.putConstraint(SpringLayout.WEST , comboStatus, 57,
                 SpringLayout.EAST , Status     );

         contentPane.add(Status);
         contentPane.add(comboStatus);

         JButton ok = new JButton("OK");
         JButton cancel = new JButton("Cancel");

         layout.putConstraint(SpringLayout.WEST , ok, 60,
                 SpringLayout.WEST , contentPane);
         layout.putConstraint(SpringLayout.NORTH, ok, 250,
                 SpringLayout.NORTH, contentPane);

         layout.putConstraint(SpringLayout.NORTH, cancel, 250,
                 SpringLayout.NORTH, contentPane);
         layout.putConstraint(SpringLayout.WEST , cancel, 25,
                 SpringLayout.EAST , ok      );

         contentPane.add(ok);
         contentPane.add(cancel);

         ok.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Vector <String> s = new Vector<>();
                 s.add(tTeam.getText());
                 s.add(tDesiner.getText());
                 s.add(tChassis.getText());
                 s.add(tTrack.getText());
                 s.add(tPower_plant.getText());
                 s.add(tDriver_1.getText());
                 s.add(tDriver_2.getText());
                 s.add(Av[comboStatus.getSelectedIndex()]);


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
                    JOptionPane.showMessageDialog (frame,"Заполнены не все поля!");
                }
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(window,"Вы уверены, что хотите закрыть окно?","Message",JOptionPane.YES_NO_OPTION);
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
    public void DeleteWeapon(DefaultTableModel model, JFrame frame) {

        try {
            model.removeRow(model.getRowCount()-1);

        }
        catch (IndexOutOfBoundsException ev) {
            JOptionPane.showMessageDialog (frame,"Ошибка! Таблица пустая!","Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    /**
     * method for load data from file
     * @param data - data table
     * @return int count posicion load from file
     */
    public int LoadFile(Vector <Vector<String>> data) {
        int count = 0;

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("C:\\example.txt"))) {
            data.clear();
            String line;
            String s[];


            while ((line = reader.readLine()) != null) {
                s = line.split(";");
                Vector <String> row = new Vector<>();
                for(int i = 0;i < s.length;i++) {
                    row.add(s[i]);
                }
                row.add(0,String.valueOf(count+1));
                data.add(row);
                count++;
            }

        } catch (IOException ev) {
            ev.printStackTrace();
        }

        return count;
    }

    /**
     * method for save data in file
     * @param data - data table
     * @return file save successful
     */
    public boolean SaveFile(Vector <Vector<String>> data) {
        String fileName = "save.txt";
        boolean flag;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            for(int i = 0; i < data.size();i++) {
                Vector <String> s = data.elementAt(i);
                for(int j = 0;j < s.size();j++) {
                    if (j != s.size()-1) {
                        writer.write(s.elementAt(j)+ ";");

                    }
                    else {
                        writer.write(s.elementAt(j));
                    }
                }
                writer.write("\n");
            }
            writer.close();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

}
