import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Vector;

/**
 * class for create interface this program
 */
public class Window extends JFrame {

    /**
     * method for create second window
     */
    private void createSecondWindow() {
        JFrame frame = new JFrame("Rasing");

        frame.setSize(500,400);
        frame.setLocation(100,100);
        frame.setResizable(false);

        //Просим программу закрыться при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Установка иконки приложения
        ImageIcon icon = new ImageIcon("./pictures_OOP/car1.png");
        frame.setIconImage(icon.getImage());

        // Создание панели инструментов
        JToolBar toolBar = new JToolBar("Панель инструментов");

        JButton save = new JButton(new ImageIcon("./pictures_OOP/save.png"));
        save.setToolTipText("Сохранить");

        JButton add = new JButton(new ImageIcon("./pictures_OOP/add.png"));
        add.setToolTipText("Добавить");

        JButton delete = new JButton(new ImageIcon("./pictures_OOP/delete.png"));
        delete.setToolTipText("Удалить");

        JButton print = new JButton(new ImageIcon("./pictures_OOP/print.png"));
        print.setToolTipText("Печать");

        JButton back = new JButton("К меню");

        JButton report = new JButton(new ImageIcon("./pictures_OOP/report.png"));
        report.setToolTipText("Отчёт");

        JButton edit = new JButton(new ImageIcon("./pictures_OOP/edit.png"));
        report.setToolTipText("Редактирование");

        toolBar.add(save);
        toolBar.add(add);
        toolBar.add(edit);
        toolBar.add(delete);
        toolBar.add(print);
        toolBar.add(report);
        toolBar.add(back);

        //Размещение панели инструментов в окне
        frame.setLayout(new BorderLayout());
        frame.add(toolBar,BorderLayout.NORTH);

        //Создание таблицы
        final String column[] = {"ID", "Driver", "Team"};
        Vector<String> columns = new Vector<>();

        for(int i = 0;i < column.length;i++) {
            columns.add(column[i]);
        }

        Vector <Vector<String>> data = new Vector<>();

        new Data().loadData(data,2,0);
        DefaultTableModel model = new DefaultTableModel(data,columns);
        JTable rasing = new JTable(model);

        final TableRowSorter <TableModel> sorter = new TableRowSorter<>(model);
        rasing.setRowSorter(sorter);
        JScrollPane scroll = new JScrollPane(rasing);

        // Размещение таблицы в окне
        frame.add(scroll, BorderLayout.CENTER);
        rasing.setEnabled(false);

        //Создание панели поиска
        JTextField weaponName = new JTextField("Team");
        JButton search = new JButton("Search");

        JPanel filterPanel = new JPanel();
        filterPanel.add(weaponName);
        filterPanel.add(search);

        // Размещение панели поиска внизу окна
        frame.add(filterPanel, BorderLayout.SOUTH);

        //Блок обработки событий
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Data().editD(model,frame);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                createMenuWindow();
            }
        });

        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s[] = {"PDF", "HTML"};

                Object result = JOptionPane.showInputDialog(
                        frame,
                        "В каком формате хотите сохранить отчёт:",
                        "Отчёт",
                        JOptionPane.QUESTION_MESSAGE,
                        icon, s, s[0]);
                if(result.equals(s[0])) {
                    Report rp = new Report();

                    try {
                        rp.createPDF(model,column,2);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else if(result.equals(s[1])) {
                    Report rp = new Report();
                    rp.createPersonalHTML(model);
                }
            }
        });


        // Обработка нажатия кнопки поиск
        search.addActionListener (new ActionListener()
        {
            public void actionPerformed (ActionEvent event)
            {
                //JOptionPane.showMessageDialog (frame, "Проверка нажатия на кнопку");
                String text = weaponName.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });

        // Обработка нажатия кнопки добавления новой позиции
        add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new Data().addD(model,frame);
            }
        });

        // Обработка нажатия кнопки удаление
        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new Data().DeletePosision(model,frame,"reserv_drivers");
            }
        });

        //Обработка нажатия кнопки сохранения
        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    new Xml_file(2).xmlSaveDriver(model,frame);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MessageFormat headerFormat = new MessageFormat("Page {0}");
                    MessageFormat footerFormat = new MessageFormat("- {0} -");
                    rasing.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
                } catch (PrinterException pe) {
                    System.err.println("Error printing: " + pe.getMessage());
                }
            }
        });

        //Показать окно
        frame.setVisible(true);
    }

    /**
     * method for create first window
     */
    private void createfirstWindow() {
        JFrame frame = new JFrame("Rasing");

        frame.setSize(1200,600);
        frame.setLocation(100,100);
        frame.setResizable(false);

        //Просим программу закрыться при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Установка иконки приложения
        ImageIcon icon = new ImageIcon("./pictures_OOP/car1.png");
        frame.setIconImage(icon.getImage());

        // Создание панели инструментов
        JToolBar toolBar = new JToolBar("Instrument panel");

        JButton save = new JButton(new ImageIcon("./pictures_OOP/save.png"));
        save.setToolTipText("Сохранить");

        JButton add = new JButton(new ImageIcon("./pictures_OOP/add.png"));
        add.setToolTipText("Добавить");

        JButton delete = new JButton(new ImageIcon("./pictures_OOP/delete.png"));
        delete.setToolTipText("Удалить");

        JButton print = new JButton(new ImageIcon("./pictures_OOP/print.png"));
        print.setToolTipText("Печать");

        JButton report = new JButton(new ImageIcon("./pictures_OOP/report.png"));
        report.setToolTipText("Отчёт");
////////////////////////////////////////////////////////////////////////////
        JButton back = new JButton("К меню");
/////////////////////////////////////////////////////////////////////////////
        JButton edit = new JButton(new ImageIcon("./pictures_OOP/edit.png"));
        edit.setToolTipText("Редактирование");

        toolBar.add(save);
        toolBar.add(add);
        toolBar.add(edit);
        toolBar.add(delete);
        toolBar.add(print);
        toolBar.add(report);
        toolBar.add(back);

        //Размещение панели инструментов в окне
        frame.setLayout(new BorderLayout());
        frame.add(toolBar,BorderLayout.NORTH);

        //Создание таблицы
        String column[] = {"ID","Team", "Designer", "Chassis", "Power_plant", "Driver_1","Driver_2", "Status"};
        Vector<String> columns = new Vector<>();

        for(int i = 0;i < column.length;i++) {
            columns.add(column[i]);
        }

        Vector <Vector<String>> data = new Vector<>();
        new Data().loadData(data,1,0);
        DefaultTableModel model = new DefaultTableModel(data,columns);
        model.setDataVector(data,columns);
        JTable rasing = new JTable(model);

        final TableRowSorter <TableModel> sorter = new TableRowSorter<>(model);
        rasing.setRowSorter(sorter);
        JScrollPane scroll = new JScrollPane(rasing);

        // Размещение таблицы в окне
        frame.add(scroll, BorderLayout.CENTER);
        rasing.setEnabled(false);

        //Создание панели поиска
        JTextField Name = new JTextField("Team");
        JButton search = new JButton("Search");

        JPanel filterPanel = new JPanel();
        filterPanel.add(Name);
        filterPanel.add(search);

        // Размещение панели поиска внизу окна
        frame.add(filterPanel, BorderLayout.SOUTH);

        //Блок обработки событий
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Data().editI(model,frame);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                createMenuWindow();
            }
        });

        // Обработка нажатия кнопки поиск
        search.addActionListener (new ActionListener()
        {
            public void actionPerformed (ActionEvent event)
            {
                String text = Name.getText();
                if (text.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(text));
                }
            }
        });

        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s[] = {"PDF", "HTML"};

                Object result = JOptionPane.showInputDialog(
                        frame,
                        "В каком формате хотите сохранить отчёт:",
                        "Отчёт",
                        JOptionPane.QUESTION_MESSAGE,
                        icon, s, s[0]);
                if(result.equals(s[0])) {
                    Report rp = new Report();

                    try {
                        rp.createPDF(model,column,1);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else if(result.equals(s[1])) {
                    Report rp = new Report();
                    rp.createPriceHTML(model);
                }
            }
        });

        // Обработка нажатия кнопки добавления новой позиции
        add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new Data().AddI(model,frame);
            }
        });

        // Обработка нажатия кнопки удаление
        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               new Data().DeletePosision(model,frame,"info");
            }
        });

        //Обработка нажатия кнопки сохранения
        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    new Xml_file(1).xmlSaveInfo(model,frame);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MessageFormat headerFormat = new MessageFormat("Page {0}");
                    MessageFormat footerFormat = new MessageFormat("- {0} -");
                    rasing.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
                } catch (PrinterException pe) {
                    System.err.println("Error printing: " + pe.getMessage());
                }
            }
        });

        //Показать окно
        frame.setVisible(true);
    }

    /**
     * method for create menu window
     */
    private void createMenuWindow() {
        JFrame frame = new JFrame("Menu");
        frame.setSize(225,170);
        frame.setLocation(600,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        ImageIcon icon = new ImageIcon("./pictures_OOP/car1.png");
        frame.setIconImage(icon.getImage());

        Container container = new Container();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JButton price = new JButton("    Information  ");
        price.setAlignmentX(Component.CENTER_ALIGNMENT);
        price.setSize(20,20);
        container.add(price);
        price.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                createfirstWindow();
            }
        });

        JButton list = new JButton("Reserv drivers");
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        list.setSize(20,20);
        container.add(list);
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                createSecondWindow();
            }
        });

        JButton report = new JButton("Grand prix");
        report.setSize(20,20);
        report.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(report);

        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                createLastWindow();
            }
        });

        JButton quant = new JButton("Results");
        quant.setSize(20,20);
        quant.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(quant);

        quant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                createThirdWindow();
            }
        });

        frame.setContentPane(container);
        frame.setVisible(true);
    }

    /**
     * method for create third window
     */
    private void createLastWindow() {
        JFrame frame = new JFrame("Rasing");

        frame.setSize(700,400);
        frame.setLocation(100,100);
        frame.setResizable(false);

        //Установка иконки приложения
        ImageIcon icon = new ImageIcon("./pictures_OOP/car1.png");
        frame.setIconImage(icon.getImage());

        // Создание панели инструментов
        JToolBar toolBar = new JToolBar("Панель инструментов");

        JButton add = new JButton(new ImageIcon("./pictures_OOP/add.png"));
        add.setToolTipText("Добавить");

        JButton delete = new JButton(new ImageIcon("./pictures_OOP/delete.png"));
        delete.setToolTipText("Удалить");

        JButton print = new JButton(new ImageIcon("./pictures_OOP/print.png"));
        print.setToolTipText("Печать");
///////////////////////////////////////////////////////
        JButton back = new JButton("К меню");
/////////////////////////////////////////////////////////////
        JButton edit = new JButton(new ImageIcon("./pictures_OOP/edit.png"));
        edit.setToolTipText("Редактирование");

        toolBar.add(add);
        toolBar.add(edit);
        toolBar.add(delete);
        toolBar.add(print);
        toolBar.add(back);

        //Размещение панели инструментов в окне
        frame.setLayout(new BorderLayout());
        frame.add(toolBar,BorderLayout.NORTH);

        String column[] = { "ID","Competition","Contru","Location","Date"};
        Vector<String> columns = new Vector<>();

        for(int i = 0;i < column.length;i++) {
            columns.add(column[i]);
        }

        Vector <Vector<String>> data = new Vector<>();

        //DefaultTableModel model= new DefaultTableModel(data, columns);
        new Data().loadData(data,3,0);
        DefaultTableModel model = new DefaultTableModel(data,columns);
        JTable grand_prix = new JTable(model);

        final TableRowSorter <TableModel> sorter = new TableRowSorter<>(model);
        grand_prix.setRowSorter(sorter);
        JScrollPane scroll = new JScrollPane(grand_prix);

        // Размещение таблицы в окне
        frame.add(scroll, BorderLayout.CENTER);
        grand_prix.setEnabled(false);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Data().addC(model,frame);
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Data().editC(model,frame);
            }
        });
        
     // Обработка нажатия кнопки удаление
        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               new Data().DeletePosision(model,frame,"grand_prix");
            }
        });


        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                createMenuWindow();
            }
        });

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MessageFormat headerFormat = new MessageFormat("Page {0}");
                    MessageFormat footerFormat = new MessageFormat("- {0} -");
                    grand_prix.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
                } catch (PrinterException pe) {
                    System.err.println("Error printing: " + pe.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }

    /**
     * method for create last window
     */
    private void createThirdWindow() {
        JFrame frame = new JFrame("Rasing");

        frame.setSize(700,400);
        frame.setLocation(100,100);
        frame.setResizable(false);

        //Установка иконки приложения
        ImageIcon icon = new ImageIcon("./pictures_OOP/car1.png");
        frame.setIconImage(icon.getImage());

        // Создание панели инструментов
        JToolBar toolBar = new JToolBar("Панель инструментов");

        Vector <String> Competition = new Data().getData1(2);
        Vector <String> ID = new Data().getData1(1);
        JComboBox Comp = new JComboBox(Competition);

        JButton select = new JButton("Выбрать");
        JButton back = new JButton("К меню");
        JButton print = new JButton("Печать");

        toolBar.add(Comp);
        toolBar.add(select);
        toolBar.add(print);
        toolBar.add(back);

        frame.setLayout(new BorderLayout());
        frame.add(toolBar,BorderLayout.NORTH);

        String column[] = { "ID","Team","Score"};
        Vector<String> columns = new Vector<>();

        for(int i = 0;i < column.length;i++) {
            columns.add(column[i]);
        }

        Vector <Vector<String>> data = new Vector<>();

        DefaultTableModel model = new DefaultTableModel(data,columns);
        JTable statistic = new JTable(model);

        final TableRowSorter <TableModel> sorter = new TableRowSorter<>(model);
        statistic.setRowSorter(sorter);
        JScrollPane scroll = new JScrollPane(statistic);

        // Размещение таблицы в окне
        frame.add(scroll, BorderLayout.CENTER);
        statistic.setEnabled(false);

        select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Data().loadData(data,4,Integer.valueOf(ID .get(Comp.getSelectedIndex())));
                model.setDataVector(data,columns);
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                createMenuWindow();
            }
        });

        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MessageFormat headerFormat = new MessageFormat("Page {0}");
                    MessageFormat footerFormat = new MessageFormat("- {0} -");
                    statistic.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
                } catch (PrinterException pe) {
                    System.err.println("Error printing: " + pe.getMessage());
                }
            }
        });

        frame.setVisible(true);

    }

    /**
     * method for create login window
     */
    public void createLoginWindow() {
        JFrame frame = new JFrame("Login");
        frame.setSize(300,175);
        frame.setLocation(600,300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("./pictures_OOP/car1.png");
        frame.setIconImage(icon.getImage());

        Container contentPane = frame.getContentPane();

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        Component labelLogin = new JLabel("Login");
        JTextField loginField = new JTextField(15);
        contentPane.add(labelLogin);
        contentPane.add(loginField);

        layout.putConstraint(SpringLayout.WEST , labelLogin, 25,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelLogin, 25,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, loginField, 25,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , loginField, 22,
                SpringLayout.EAST , labelLogin      );

        Component pass = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField(15);
        contentPane.add(pass);
        contentPane.add(passwordField);

        layout.putConstraint(SpringLayout.WEST , pass, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, pass, 55,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, passwordField, 55,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , passwordField, 10,
                SpringLayout.EAST , pass      );

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        contentPane.add(ok);
        contentPane.add(cancel);

        layout.putConstraint(SpringLayout.WEST , ok, 60,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, ok, 85,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, cancel, 85,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , cancel, 80,
                SpringLayout.EAST , ok      );

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if((new Password().checkPassword(passwordField.getText())) && (loginField.getText().equals("12345"))) {

                        frame.setVisible(false);
                        createMenuWindow();
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,"Неверный пароль или логин");
                    }
                } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                    noSuchAlgorithmException.printStackTrace();
                }

            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.setVisible(true);
    }

}
