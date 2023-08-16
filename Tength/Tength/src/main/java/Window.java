import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/** class for create main window */
public class Window {
    final static public Object shared = new Object();
    final JFrame frame;
    JTable rasing;
    DefaultTableModel model;
    ImageIcon icon;
    JButton save;
    JButton database;
    JButton add;
    JButton delete;
    JButton print;
    JButton info;
    JButton report;
    /**
     * Constructor class Window
     */
    public Window(){
        frame = new JFrame("Rasing information");
    }

    void createWindow() {

        //Создание окна
        frame.setSize(1200,600);
        frame.setLocation(100,100);

        //Просим программу закрыться при закрытии окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Установка иконки приложения
        final ImageIcon icon = new ImageIcon("./pictures_OOP/car1.png");
        frame.setIconImage(icon.getImage());

        // Создание панели инструментов
        JToolBar toolBar = new JToolBar("Панель инструментов");

        JButton save = new JButton(new ImageIcon("./pictures_OOP/save.png"));
        save.setToolTipText("Save");

        JButton database = new JButton(new ImageIcon("./pictures_OOP/load.png"));
        database.setToolTipText("Load data");

        JButton add = new JButton(new ImageIcon("./pictures_OOP/add.png"));
        add.setToolTipText("Add position");

        JButton delete = new JButton(new ImageIcon("./pictures_OOP/delete.png"));
        delete.setToolTipText("Delete position");

        JButton print = new JButton(new ImageIcon("./pictures_OOP/print.png"));
        print.setToolTipText("Print");

        JButton info = new JButton(new ImageIcon("./pictures_OOP/info.png"));
        info.setToolTipText("Information");

        JButton report = new JButton(new ImageIcon("./pictures_OOP/report.png"));
        report.setToolTipText("Report");

        toolBar.add(save);
        toolBar.add(database);
        toolBar.add(add);
        toolBar.add(delete);
        toolBar.add(print);
        toolBar.add(report);
        toolBar.add(info);
        
        //Размещение панели инструментов в окне
        frame.setLayout(new BorderLayout());
        frame.add(toolBar,BorderLayout.NORTH);

        //Создание таблицы
        final String column[] = {"Team", "Designer", "Chassis","Track","Power plant","Driver 1", "Driver 2","Status"};
        Vector<String> columns = new Vector<String>();

        for(int i = 0;i < column.length;i++) {
            columns.add(column[i]);
        }

        Vector <Vector<String>> data = new Vector<Vector<String>>();

        model = new DefaultTableModel(data,columns);
        final JTable rasing = new JTable(model);
        final TableRowSorter <TableModel> sorter = new TableRowSorter<TableModel>(model);
        rasing.setRowSorter(sorter);
        JScrollPane scroll = new JScrollPane(rasing);

        // Размещение таблицы в окне
        frame.add(scroll, BorderLayout.CENTER);
        rasing.setEnabled(false);

        //Создание панели поиска
        final JTextField Name = new JTextField("Название производителя");
        JButton search = new JButton("Поиск");

        JPanel filterPanel = new JPanel();
        filterPanel.add(Name);
        filterPanel.add(search);

        // Размещение панели поиска внизу окна
        frame.add(filterPanel, BorderLayout.SOUTH);

        //Блок обработки событий
        final Data dat = new Data();
        final Xml_file fl = new Xml_file();

        // Обработка нажатия кнопки загрузки базы данных
        database.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                fl.xmlOpen(model);
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

        // Обработка нажатия кнопки информация о программе
        info.addActionListener (new ActionListener()
        {
            public void actionPerformed (ActionEvent event)
            {
                JOptionPane.showMessageDialog (frame, "Program name: Rasing information\nAuthor: Bryzgalova Ekaterina\nVersion 8.1");
            }
        });

        // Обработка нажатия кнопки добавления новой позиции
        add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dat.Add(model,frame);
            }
        });

        // Обработка нажатия кнопки удаление
        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dat.Delete(model,frame);
            }
        });

        //Обработка нажатия кнопки сохранения
        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    fl.xmlSave(model);
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

        report.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s[] = {"PDF", "HTML"};

                Object result = JOptionPane.showInputDialog(
                        frame,
                        "Choose the format of report:",
                        "Report",
                        JOptionPane.QUESTION_MESSAGE,
                        icon, s, s[0]);
                if(result.equals(s[0])) {
                    Report rp = new Report();

                    try {
                        rp.createPDF(model);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                }
                else if(result.equals(s[1])) {
                    Report rp = new Report();
                    rp.createHTML(model);
                }

            }
        });
        class tthread extends Thread {


            private int type;

            public tthread(int i) {
                type = i;
            }

            public void run() {

                if (type == 1) {
                    synchronized (shared) {
                        try {
                            shared.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                      Xml_file fl = new Xml_file();
                        fl.xmlOpen(model);
                        System.out.println("potok 1");
                    }
                }

                if (type == 2) {
                    synchronized (shared) {
                        shared.notifyAll();
                        try {
                            shared.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        model.addRow(new String[]{"Williams Racing","Williams","FW43B","2","Mercedes","Nicholas Latifi", "George Russell","Winer"});
                        System.out.println("potok 2");
                        shared.notifyAll();
                    }
                }

                if (type == 3) {
                    synchronized (shared) {
                        shared.notifyAll();
                        try {
                            shared.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        Report rp = new Report();
                        rp.createHTML(model);
                        try {
                            rp.createPDF(model);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        System.out.println("potok 3");

                    }
                }


            }
        }
        new tthread(1).start();
        new tthread(2).start();
        new tthread(3).start();
        //Показать окно
        frame.setVisible(true);
    }

}
