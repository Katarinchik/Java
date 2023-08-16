import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import java.sql.*;

/**
 * Class for work to data
 */
public class Data {
    private String url = "jdbc:mysql://localhost:3306/rasing?serverTimezone=Europe/Moscow";
    private String username = "root";
    private String password = "1234567890";

    /**
     * Auxiliary method
     * @param flag
     * @return string
     */
    Vector <String> getData(int flag) {
        Vector <String> s = new Vector<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM rasing.info order by info.Team");

                while(resultSet.next()){
                    if(flag == 1) {
                        s.add(String.valueOf(resultSet.getInt(1)));
                    }
                    else if(flag == 2) {
                        s.add(resultSet.getNString(2));
                    }

                }

                System.out.println("Connection sucdefull!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

        return s;
    }

    
    
    Vector <String> getData1(int flag) {
        Vector <String> s = new Vector<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM rasing.grand_prix order by grand_prix.Competition");

                while(resultSet.next()){
                    if(flag == 1) {
                        s.add(String.valueOf(resultSet.getInt(1)));
                    }
                    else if(flag == 2) {
                        s.add(resultSet.getNString(2));
                    }

                }

                System.out.println("Connection sucdefull!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

        return s;
    }
    /**
     * method for search number row in table
     * @param model
     * @param number
     * @return number row
     */
    private int findRow(DefaultTableModel model,String number) {
        int num = -1;
        for(int i = 0;i< model.getRowCount();i++) {
            if(model.getValueAt(i,0).equals(number)) {
                num = i;
                System.out.println(num);
                break;
            }
        }

        return num;
    }

    /**
     * method for search in table db
     * @param s
     * @return ID
     */
    private int maxID(String s) {
        int id = 0;

         try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT MAX(ID) FROM " + s);

                while(resultSet.next()){
                    id = resultSet.getInt(1);
                }

                System.out.println("Connection sucdefull!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
         System.out.println(id);
        return id;
    }

    /**
     * method for add position
     * @param model
     * @param frame
     */
    public void AddI(final DefaultTableModel model, final JFrame frame) {
        final JDialog window = new JDialog(frame,"Add");

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
        layout.putConstraint(SpringLayout.WEST , tTeam, 23,
                SpringLayout.EAST , Team      );

        Component Designer = new JLabel("Designer");
        final JTextField tDesigner = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , Designer, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Designer, 50,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tDesigner, 50,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tDesigner, 23,
                SpringLayout.EAST , Team);

        contentPane.add(Designer);
        contentPane.add(tDesigner);

        Component Chassis = new JLabel("Chassis");
        final JTextField tChassis= new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , Chassis, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Chassis, 75,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tChassis, 75,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tChassis, 23,
                SpringLayout.EAST , Chassis);

        contentPane.add(Chassis);
        contentPane.add(tChassis);

       

        Component Power_plant = new JLabel("Power_plant");
        final JTextField tPower_plant = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , Power_plant, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Power_plant, 100,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tPower_plant, 100,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tPower_plant, 11,
                SpringLayout.EAST , Power_plant);

        contentPane.add(Power_plant);
        contentPane.add(tPower_plant);

        Component Driver_1 = new JLabel("Driver_1");
        final JTextField tDriver_1 = new JTextField(15);

        

        layout.putConstraint(SpringLayout.WEST , Driver_1, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Driver_1, 125,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tDriver_1, 125,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tDriver_1, 23,
                SpringLayout.EAST , Driver_1);
        contentPane.add(Driver_1);
        contentPane.add(tDriver_1);

        
        Component Driver_2 = new JLabel("Driver_2");
        final JTextField tDriver_2 = new JTextField(15);
        
        
        layout.putConstraint(SpringLayout.WEST , Driver_2, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Driver_2, 150,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tDriver_2, 150,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tDriver_2, 23,
                SpringLayout.EAST , Driver_2      );
        contentPane.add(Driver_2);
        contentPane.add(tDriver_2);


      
        
        Component Status = new JLabel("Status");
        final String tStatus[] = {"Winner","Competitor"};
        final JComboBox comboStatus = new JComboBox<>(tStatus);

        layout.putConstraint(SpringLayout.WEST , Status, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Status, 175,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, comboStatus, 175,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , comboStatus, 25,
                SpringLayout.EAST,Status );

        contentPane.add(Status);
        contentPane.add(comboStatus);

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");

        layout.putConstraint(SpringLayout.WEST , ok, 60,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, ok, 200,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, cancel, 200,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , cancel, 85,
                SpringLayout.EAST , ok      );

        contentPane.add(ok);
        contentPane.add(cancel);

        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Vector <String> s = new Vector<>();
                s.add(tTeam.getText());
                s.add(tDesigner.getText());
                s.add(tChassis.getText());
                s.add(tPower_plant.getText());
                s.add(tDriver_1.getText());
                s.add(tDriver_2.getText());
                s.add(tStatus[comboStatus.getSelectedIndex()]);

                int count = 0;
                for(String s1:s) {
                    if((s1.length() > 0) && (!s1.equals(" "))) {
                        count++;
                    }
                }

                if(count == 7) {
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                        try (Connection conn = DriverManager.getConnection(url, username, password)){
                            try {
                                PreparedStatement st = conn.prepareStatement("INSERT INTO `rasing`.`info` (Team, Designer, Chassis, Power_plant, Driver_1, Driver_2, Status) VALUES (?, ?, ?, ?, ?, ?, ?)");
                                st.setString(1,s.get(0));
                                st.setString(2, s.get(1));
                                st.setString(3, s.get(2));
                                st.setString(4, s.get(3));
                                st.setString(5, s.get(4));
                                st.setString(6, s.get(5));
                                st.setString(7, s.get(6));
                                st.execute();
                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                            System.out.println("Connection sucdefull!");
                        }
                    }
                    catch(Exception ex){
                        System.out.println("Connection failed...");
                        System.out.println(ex);
                    }
                    s.add(0,String.valueOf(maxID("info")));
                    
                    model.addRow(s);
                    window.dispose();
                }
                else {
                    JOptionPane.showMessageDialog (frame,"Заполнены не все поля!");
                }
            }
        });

        cancel.addActionListener(new ActionListener() {

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
     * method for edit position
     * @param model
     * @param frame
     */
    public void editI(DefaultTableModel model, JFrame frame) {
        String number = JOptionPane.showInputDialog("Введите ID для редактирование");
        int num = findRow(model,number);

        if(num >= 0) {
            final JDialog window = new JDialog(frame,"Edit");
            window.setModal(true);
            window.setSize(350,325);
            window.setLocation(300,300);
            window.setResizable(false);

            Container contentPane = window.getContentPane();

            SpringLayout layout = new SpringLayout();
            contentPane.setLayout(layout);

            Component Team = new JLabel("Team");
            final JTextField tTeam = new JTextField(String.valueOf(model.getValueAt(num,1)),15);

            contentPane.add(Team);
            contentPane.add(tTeam);

            layout.putConstraint(SpringLayout.WEST , Team, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Team, 25,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, tTeam, 25,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , tTeam, 23,
                    SpringLayout.EAST , Team      );

            Component Designer = new JLabel("Designer");
            final JTextField tDesigner = new JTextField(String.valueOf(model.getValueAt(num,2)),15);

            layout.putConstraint(SpringLayout.WEST , Designer, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Designer, 50,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, tDesigner, 50,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , tDesigner, 23,
                    SpringLayout.EAST , Team);

            contentPane.add(Designer);
            contentPane.add(tDesigner);

            Component Chassis = new JLabel("Chassis");
            final JTextField tChassis = new JTextField(String.valueOf(model.getValueAt(num,3)),15);

            layout.putConstraint(SpringLayout.WEST , Chassis, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Chassis, 75,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, tChassis, 75,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , tChassis, 23,
                    SpringLayout.EAST , Chassis);

            contentPane.add(Chassis);
            contentPane.add(tChassis);

           

            Component Power_plant = new JLabel("Power_plant");
            final JTextField tPower_plant = new JTextField(String.valueOf(model.getValueAt(num,4)),15);

            layout.putConstraint(SpringLayout.WEST , Power_plant, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Power_plant, 100,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, tPower_plant, 100,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , tPower_plant, 11,
                    SpringLayout.EAST , Power_plant);

            contentPane.add(Power_plant);
            contentPane.add(tPower_plant);

            Component Driver_1 = new JLabel("Driver_1");
            final JTextField tDriver_1 = new JTextField(String.valueOf(model.getValueAt(num,5)),15);

            layout.putConstraint(SpringLayout.WEST , Driver_1, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Driver_1, 125,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, tDriver_1, 125,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , tDriver_1, 23,
                    SpringLayout.EAST , Driver_1);

            contentPane.add(Driver_1);
            contentPane.add(tDriver_1);

            Component Driver_2 = new JLabel("Driver_2");
            final JTextField tDriver_2 = new JTextField(String.valueOf(model.getValueAt(num,6)),15);

            layout.putConstraint(SpringLayout.WEST , Driver_2, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Driver_2, 150,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, tDriver_2, 150,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , tDriver_2, 23,
                    SpringLayout.EAST , Driver_2      );

            contentPane.add(Driver_2);
            contentPane.add(tDriver_2);
            
            Component Status = new JLabel("Status");
            final String gunType[] = {"Winner","Competitor"};
            final JComboBox comboGunType = new JComboBox<>(gunType);

            layout.putConstraint(SpringLayout.WEST , Status, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Status, 175,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, comboGunType, 175,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , comboGunType, 25,
                    SpringLayout.EAST,Status );

            contentPane.add(Status);
            contentPane.add(comboGunType);

            JButton ok = new JButton("OK");
            JButton cancel = new JButton("Cancel");

            layout.putConstraint(SpringLayout.WEST , ok, 60,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, ok, 200,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, cancel, 200,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , cancel, 85,
                    SpringLayout.EAST , ok      );

            contentPane.add(ok);
            contentPane.add(cancel);

            ok.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Vector <String> s = new Vector<>();
                    s.add(tTeam.getText());
                    s.add(tDesigner.getText());
                    s.add(tChassis.getText());
                    s.add(tPower_plant.getText());
                    s.add(tDriver_1.getText());
                    s.add(tDriver_2.getText());
                    s.add(gunType[comboGunType.getSelectedIndex()]);

                    int count = 0;
                    for(String s1:s) {
                        if((s1.length() > 0) && (!s1.equals(" "))) {
                            count++;
                        }
                    }

                    if(count == 7) {
                        s.add(0, number);

                        try{
                            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                            try (Connection conn = DriverManager.getConnection(url, username, password)){
                                Statement statement = conn.createStatement();
                                  
                                statement.executeUpdate("UPDATE info SET   Team = '" + s.get(1) + "' WHERE ID = " + number);
                                statement.executeUpdate("UPDATE info SET Designer = '" + s.get(2) + "' WHERE ID = " + number);
                                statement.executeUpdate("UPDATE info SET Chassis = '" + s.get(3) + "' WHERE ID = " + number);
                                statement.executeUpdate("UPDATE info SET Power_plant = '" + s.get(4) + "' WHERE ID = " + number);
                                statement.executeUpdate("UPDATE info SET Driver_1 = " + s.get(5) + " WHERE ID = " + number);
                                statement.executeUpdate("UPDATE info SET  Driver_2 = " + s.get(6) + " WHERE ID = " + number);
                                statement.executeUpdate("UPDATE info SET  Status = " + s.get(7) + " WHERE ID = " + number);
                                System.out.println("Connection sucdefull!");
                            }
                        }
                        catch(Exception ex){
                            System.out.println("Connection failed...");
                            System.out.println(ex);
                        }

                        for(int i = 1;i < s.size();i++) {
                            model.setValueAt(s.get(i),num,i);
                        }
                        window.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog (frame,"Заполнены не все поля!");
                    }
                }
            });

            cancel.addActionListener(new ActionListener() {

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
        else {
            JOptionPane.showMessageDialog (frame,"Ошибка! Несуществующий ID!","Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * method for add new personal
     * @param model
     * @param frame
     */
    public void addD(DefaultTableModel model, JFrame frame) {
        final JDialog window = new JDialog(frame,"Add");
        window.setModal(true);
        window.setSize(400,200);
        window.setLocation(300,300);
        window.setResizable(false);

        Container contentPane = window.getContentPane();

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        Component Driver = new JLabel("Driver");
        final JTextField tDriver = new JTextField(12);

        

        layout.putConstraint(SpringLayout.WEST , Driver, 100,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Driver, 100,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tDriver, 25,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tDriver, 100,
                SpringLayout.EAST , Driver);
        contentPane.add(Driver);
        contentPane.add(tDriver);

        Component Team = new JLabel("Team");
        Vector <String> aTeam = getData(2);
        JComboBox Teaml = new JComboBox(aTeam);

        layout.putConstraint(SpringLayout.WEST , Team, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Team, 50,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, Teaml, 50,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , Teaml, 20,
                SpringLayout.EAST , Driver     );

        contentPane.add(Driver);
        contentPane.add(Teaml);

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");

        layout.putConstraint(SpringLayout.WEST , ok, 25,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, ok, 100,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, cancel, 100,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , cancel, 45,
                SpringLayout.EAST , ok      );

        contentPane.add(ok);
        contentPane.add(cancel);

        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Vector <String> s = new Vector<>();
                Vector <String> DriverID = getData(1);

                int num = Teaml.getSelectedIndex();
                s.add( DriverID.get(num));
                s.add(tDriver.getText());

                int count = 0;
                for(String s1:s) {
                    if((s1.length() > 0) && (!s1.equals(" "))) {
                        count++;
                    }
                }

                if(count == 2) {
                    System.out.println(s);
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                        try (Connection conn = DriverManager.getConnection(url, username, password)){
                            try {
                                PreparedStatement st = conn.prepareStatement("INSERT INTO `rasing`.`reserv_drivers` (`Driver`, `Team`) VALUES (?, ?)");
                                st.setInt(1,Integer.parseInt(s.get(0)));
                                st.setString(2,s.get(1));
                                st.execute();
                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                            System.out.println("Connection succefull!");
                        }
                    }
                    catch(Exception ex){
                        System.out.println("Connection failed...");
                        System.out.println(ex);
                    }
                    s.add(0,String.valueOf(maxID("reserv_drivers")));
                    s.remove(1);
                    s.add(aTeam.get(Teaml.getSelectedIndex()));
                    model.addRow(s);
                    window.dispose();
                }
                else {
                    JOptionPane.showMessageDialog (frame,"Заполнены не все поля!");
                }
            }
        });

        cancel.addActionListener(new ActionListener() {

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
     * method for edit personal
     * @param model
     * @param frame
     */
    public void editD(DefaultTableModel model, JFrame frame) {
        String number = JOptionPane.showInputDialog("Введите ID для редактирование");

        int num = findRow(model,number);
        if(num >= 0) {

            final JDialog window = new JDialog(frame,"Edit");
            window.setModal(true);
            window.setSize(400,250);
            window.setLocation(300,300);
            window.setResizable(false);

            Container contentPane = window.getContentPane();

            SpringLayout layout = new SpringLayout();
            contentPane.setLayout(layout);

            Component Driver = new JLabel("Driver");
            final JTextField tDriver = new JTextField(String.valueOf(model.getValueAt(num,1)),15);

            contentPane.add(Driver);
            contentPane.add(tDriver);

            layout.putConstraint(SpringLayout.WEST , Driver, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Driver, 45,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, tDriver, 45,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , tDriver, 85,
                    SpringLayout.EAST , Driver);

            Component Team = new JLabel("Team");
            Vector <String> aTeam = getData(2);
            JComboBox Teaml = new JComboBox(aTeam);

            layout.putConstraint(SpringLayout.WEST , Team, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Team, 80,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, Teaml, 80,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , Teaml, 13,
                    SpringLayout.EAST , Driver     );

            contentPane.add(Driver);
            contentPane.add(Teaml);
            
            JButton ok = new JButton("OK");
            JButton cancel = new JButton("Cancel");

            layout.putConstraint(SpringLayout.WEST , ok, 85,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, ok, 150,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, cancel, 150,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , cancel, 125,
                    SpringLayout.EAST , ok      );

            contentPane.add(ok);
            contentPane.add(cancel);

            ok.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Vector <String> s = new Vector<>();
                    Vector <String> DriverID = getData(1);

                    int num = Teaml.getSelectedIndex();
                    s.add( DriverID.get(num));
                    s.add(tDriver.getText());

                    int count = 0;
                    for(String s1:s) {
                        if((s1.length() > 0) && (!s1.equals(" "))) {
                            count++;
                        }
                    }

                    if(count == 2) {
                        s.add(0, number);
                        try{
                            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                            try (Connection conn = DriverManager.getConnection(url, username, password)){
                                Statement statement = conn.createStatement();

                                statement.executeUpdate("UPDATE reserv_drivers SET Driver = '" + s.get(1) + "' WHERE ID = " + number);
                                statement.executeUpdate("UPDATE reserv_drivers SET Team = '" + s.get(2) + "' WHERE ID = " + number);
                                System.out.println("Connection sucdefull!");

                            }
                        }
                        catch(Exception ex){
                            System.out.println("Connection failed...");
                            System.out.println(ex);
                        }

                        s.setElementAt(aTeam.get(Teaml.getSelectedIndex()),2);


                        for(int i = 1;i < 3;i++) {
                            System.out.println(s.get(i));
                            model.setValueAt(s.get(i),num,i);
                        }

                        window.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog (frame,"Заполнены не все поля!");
                    }
                }
            });

            cancel.addActionListener(new ActionListener() {

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
        else {
            JOptionPane.showMessageDialog (frame,"Ошибка! Несуществующий ID!","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * method for delete
     * @param model
     * @param frame
     * @param s
     */
    public void DeletePosision(DefaultTableModel model, JFrame frame, String s) {
        String number = JOptionPane.showInputDialog("Введите ID для удаления");

        int num = findRow(model,number);


        if(num > 0) {
            try {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                    try (Connection conn = DriverManager.getConnection(url, username, password)){
                        System.out.println("Connection sucсessfully!");
                        Statement statement = conn.createStatement();

                        String sqlCommand  = "DELETE FROM "+ s + " WHERE ID = " + number;
                        System.out.println(sqlCommand);
                        int rows = statement.executeUpdate(sqlCommand);

                        model.removeRow(num);
                    }
                }
                catch(Exception ex){
                    System.out.println("Connection failed...");
                    System.out.println(ex);
                }
            }
            catch (IndexOutOfBoundsException ev) {
                JOptionPane.showMessageDialog (frame,"Ошибка! Таблица пустая!","Error", JOptionPane.ERROR_MESSAGE);
            }
        }



    }

    /**
     * method for add store
     * @param model
     * @param frame
     */
    public void addC(DefaultTableModel model, JFrame frame) {
        final JDialog window = new JDialog(frame,"Add");

        window.setModal(true);
        window.setSize(350,200);
        window.setLocation(300,300);
        window.setResizable(false);

        Container contentPane = window.getContentPane();

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        Component Competition = new JLabel("Competition");
        final JTextField tCompetition = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST ,Competition, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Competition, 25,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tCompetition, 25,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tCompetition, 41,
                SpringLayout.EAST , Competition     );

        contentPane.add(Competition);
        contentPane.add(tCompetition);

        Component Country = new JLabel("Country");
        final JTextField tCountry = new JTextField(15);

        contentPane.add(Country);
        contentPane.add(tCountry);

        layout.putConstraint(SpringLayout.WEST , Country, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Country, 50,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tCountry, 50,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tCountry, 22,
                SpringLayout.EAST , Country  );

        Component Location = new JLabel("Location");
        final JTextField tLocation = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , Location, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Location, 75,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tLocation, 75,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tLocation, 23,
                SpringLayout.EAST , Location    );

        contentPane.add(Location);
        contentPane.add(tLocation);

       

        Component Date = new JLabel("Date");
        final JTextField tDate = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , Date, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, Date, 100,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, tDate, 100,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , tDate, 41,
                SpringLayout.EAST ,Date);

        contentPane.add(Date);
        contentPane.add(tDate);
        
        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");

        layout.putConstraint(SpringLayout.WEST , ok, 60,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, ok, 125,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, cancel, 125,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , cancel, 85,
                SpringLayout.EAST , ok      );

        contentPane.add(ok);
        contentPane.add(cancel);

        ok.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Vector <String> s = new Vector<>();
                s.add(tCompetition.getText());
                s.add(tCountry.getText());
                s.add(tLocation.getText());
                s.add(tDate.getText());

                int count = 0;
                for(String s1:s) {
                    if((s1.length() > 0) && (!s1.equals(" "))) {
                        count++;
                    }
                }

                if(count == 4) {
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                        try (Connection conn = DriverManager.getConnection(url, username, password)){
                            try {
                                PreparedStatement st = conn.prepareStatement("INSERT INTO grand_prix (Competition, Country, Location, Date) VALUES (?, ?, ?, ?)");
                                st.setString(1,s.get(0));
                                st.setString(2, s.get(1));
                                st.setString(3, s.get(2));
                                st.setString(4, s.get(3));
                                st.execute();
                            } catch (SQLException exception) {
                                exception.printStackTrace();
                            }
                            System.out.println("Connection sucdefull!");
                        }
                    }
                    catch(Exception ex){
                        System.out.println("Connection failed...");
                        System.out.println(ex);
                    }
                    s.add(0,String.valueOf(maxID("grand_prix")));
                    model.addRow(s);
                    window.dispose();
                }
                else {
                    JOptionPane.showMessageDialog (frame,"Заполнены не все поля!");
                }
            }
        });

        cancel.addActionListener(new ActionListener() {

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
     * method for edit store
     * @param model
     * @param frame
     */
    public void editC(DefaultTableModel model, JFrame frame) {
        String number = JOptionPane.showInputDialog("Введите ID для редактирование");
        int num = findRow(model,number);

        if(num >= 0) {
            final JDialog window = new JDialog(frame,"Edit");
            window.setModal(true);
            window.setSize(350,200);
            window.setLocation(300,300);
            window.setResizable(false);

            Container contentPane = window.getContentPane();

            SpringLayout layout = new SpringLayout();
            contentPane.setLayout(layout);

            Component Country = new JLabel("Competition");
            final JTextField tCountry = new JTextField(String.valueOf(model.getValueAt(num,1)),15);

            contentPane.add(Country);
            contentPane.add(tCountry);

            layout.putConstraint(SpringLayout.WEST , Country, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Country, 25,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, tCountry, 25,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , tCountry, 22,
                    SpringLayout.EAST , Country      );

            Component Location = new JLabel("Country");
            final JTextField tLocation = new JTextField(String.valueOf(model.getValueAt(num,2)),15);

            layout.putConstraint(SpringLayout.WEST , Location, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Location, 50,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, tLocation, 50,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , tLocation, 23,
                    SpringLayout.EAST , Location  );

            contentPane.add(Location);
            contentPane.add(tLocation);

            Component Track = new JLabel("Location");
            final JTextField tTrack = new JTextField(String.valueOf(model.getValueAt(num,3)),15);

            layout.putConstraint(SpringLayout.WEST , Track, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Track, 75,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, tTrack, 75,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , tTrack, 41,
                    SpringLayout.EAST , Track );

            contentPane.add(Track);
            contentPane.add(tTrack);
            
            Component Date = new JLabel("Date");
            final JTextField tDate = new JTextField(String.valueOf(model.getValueAt(num,4)),15);

            layout.putConstraint(SpringLayout.WEST , Date, 10,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, Date, 100,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, tDate, 100,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , tDate, 41,
                    SpringLayout.EAST , Date );

            contentPane.add(Date);
            contentPane.add(tDate);

            JButton ok = new JButton("OK");
            JButton cancel = new JButton("Cancel");

            layout.putConstraint(SpringLayout.WEST , ok, 60,
                    SpringLayout.WEST , contentPane);
            layout.putConstraint(SpringLayout.NORTH, ok, 125,
                    SpringLayout.NORTH, contentPane);

            layout.putConstraint(SpringLayout.NORTH, cancel, 125,
                    SpringLayout.NORTH, contentPane);
            layout.putConstraint(SpringLayout.WEST , cancel, 85,
                    SpringLayout.EAST , ok      );

            contentPane.add(ok);
            contentPane.add(cancel);

            ok.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Vector <String> s = new Vector<>();
                    s.add(tCountry.getText());
                    s.add(tLocation.getText());
                    s.add(tTrack.getText());
                    
                    s.add(tDate.getText());
                    int count = 0;
                    for(String s1:s) {
                        if((s1.length() > 0) && (!s1.equals(" "))) {
                            count++;
                        }
                    }

                    if(count == 4) {
                    	s.add(0, number);
                        try{
                            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

                            try (Connection conn = DriverManager.getConnection(url, username, password)){
                                Statement statement = conn.createStatement();

                                statement.executeUpdate("UPDATE grand_prix SET Country = '" + s.get(0) + "' WHERE ID = " + number);
                                statement.executeUpdate("UPDATE grand_prix SET Location = '" + s.get(1) + "' WHERE ID = " + number);
                                statement.executeUpdate("UPDATE grand_prix SET Track = '" + s.get(2) + "' WHERE ID = " + number);
                                statement.executeUpdate("UPDATE grand_prix SET Date= '" + s.get(3) + "' WHERE ID = " + number);
                                System.out.println("Connection sucdefull!");
                            }
                        }
                        catch(Exception ex){
                            System.out.println("Connection failed...");
                            System.out.println(ex);
                        }
                        model.removeRow(num);
                        model.insertRow(num,s);
                        window.dispose();
                    }
                    else {
                        JOptionPane.showMessageDialog (frame,"Заполнены не все поля!");
                    }
                }
            });

            cancel.addActionListener(new ActionListener() {

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
        else {
            JOptionPane.showMessageDialog (frame,"Ошибка! Несуществующий ID!","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * method for load data from data base
     * @param data
     * @param flag
     * @param id
     */
    public void loadData(Vector <Vector<String>> data, int flag, int id) {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet;
                switch (flag) {
                    case 1:
                        resultSet = statement.executeQuery("SELECT * FROM info");
                        while(resultSet.next()){
                            Vector <String> row = new Vector<>();
                            row.add(String.valueOf(resultSet.getInt(1)));
                            row.add(resultSet.getNString(2));
                            row.add(resultSet.getNString(3));
                            row.add(resultSet.getNString(4));
                            row.add(resultSet.getNString(5));
                            row.add(resultSet.getNString(6));
                            row.add(resultSet.getNString(7));
                            row.add(resultSet.getNString(8));
                            data.add(row);
                        }
                        break;
                    case 2:
                        resultSet = statement.executeQuery("SELECT * FROM reserv_drivers");//("select reserv_drivers.ID, reserv_drivers.Driver, grand_prix.Country from reserv_drivers join grand_prix on reserv_drivers.ID = grand_prix.ID order by ID");
                        while(resultSet.next()){
                            Vector <String> row = new Vector<>();
                            row.add(String.valueOf(resultSet.getInt(1)));
                            row.add(resultSet.getNString(2));
                            row.add(resultSet.getNString(3));
                            data.add(row);
                        }
                        break;
                    case 3:
                        resultSet = statement.executeQuery("SELECT * FROM grand_prix");
                        while(resultSet.next()){
                            Vector <String> row = new Vector<>();
                            row.add(String.valueOf(resultSet.getInt(1)));
                            row.add(resultSet.getNString(2));
                            row.add(resultSet.getNString(3));
                            row.add(resultSet.getNString(4));
                            row.add(resultSet.getNString(5));
                            data.add(row);
                        }
                        break;
                    case 4:
                        data.clear();
                        resultSet = statement.executeQuery("select info.ID,info.Team,coalesce(statistic.Score,0)\n" +
                                "from info \n" +
                                "left join statistic on info.ID = statistic.ID \n" +		//and statistic.ID = " + String.valueOf(id) + "
                                "order by info.Team");
                        while(resultSet.next()){
                            Vector <String> row = new Vector<>();
                            row.add(String.valueOf(resultSet.getInt(1)));
                            row.add(resultSet.getString(2));
                            row.add(resultSet.getString(3));
                            data.add(row);
                        }
                        break;
                }

            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
}

