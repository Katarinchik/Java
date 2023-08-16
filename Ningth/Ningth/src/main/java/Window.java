import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 * @author Mihail Nemtsov
 */

/** class for create main window */
public class Window {
    JFrame frame;
    final Vector<String> columns = new Vector<>();
    final Vector <Vector<String>> data = new Vector<>();
    DefaultTableModel model;

    /**
     * method create Window
     */
    public void createWindow(){

        //Создание окна
        frame = new JFrame("Gun Shop");

        frame.setSize(1200,600);
        frame.setLocation(100,100);
        frame.setResizable(false);

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


        toolBar.add(save);
        toolBar.add(database);
        toolBar.add(add);
        toolBar.add(delete);
        toolBar.add(print);
 
        toolBar.add(info);
  
        //Размещение панели инструментов в окне
        frame.setLayout(new BorderLayout());
        frame.add(toolBar,BorderLayout.NORTH);

        //Создание таблицы
        final String column[] = {"Team", "Designer", "Chassis","Track","Power plant","Driver 1", "Driver 2","Status"};

        for(int i = 0;i < column.length;i++) {
            columns.add(column[i]);
        }

        //DefaultTableModel model= new DefaultTableModel(data, columns);
        model = new DefaultTableModel(data,columns);
        final JTable weapon = new JTable(model);

        JScrollPane scroll = new JScrollPane(weapon);

        // Размещение таблицы в окне
        frame.add(scroll, BorderLayout.CENTER);
        weapon.setEnabled(false);

        //Создание панели поиска
        JTextField weaponName = new JTextField("Team");
        JButton search = new JButton("Search");

        JPanel filterPanel = new JPanel();
        filterPanel.add(weaponName);
        filterPanel.add(search);

        // Размещение панели поиска внизу окна
        frame.add(filterPanel, BorderLayout.SOUTH);

        //Блок обработки событий
        final Data d = new Data();
        // Обработка нажатия кнопки загрузки базы данных
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                d.SaveFile(data);
            }
        });
        database.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                d.LoadFile(data);
                model.setDataVector(data,columns);
            }
        });

        // Обработка нажатия кнопки поиск
        search.addActionListener (new ActionListener()
        {
            public void actionPerformed (ActionEvent event)
            {
                JOptionPane.showMessageDialog (frame, "Проверка нажатия на кнопку");
            }
        });



        // Обработка нажатия кнопки информация о программе
        info.addActionListener (new ActionListener()
        {
            public void actionPerformed (ActionEvent event)
            {
                JOptionPane.showMessageDialog (frame, "Rasing information\nAuthor: Bryzgalova Ekaterina\nVersion 9.1");
            }
        });

        // Обработка нажатия кнопки добавления новой позиции
        add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                d.AddWeapon(model,frame);
            }
        });

        // Обработка нажатия кнопки удаление
        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        //Обработка нажатия кнопки сохранения
        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        print.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    MessageFormat headerFormat = new MessageFormat("Page {0}");
                    MessageFormat footerFormat = new MessageFormat("- {0} -");
                    weapon.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
                } catch (PrinterException pe) {
                    System.err.println("Error printing: " + pe.getMessage());
                }
            }
        });

        //Показать окно
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Window wd = new Window();
        wd.createWindow();
    }
}
