package edu.java.lab1;
// Подключение графических библиотек
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 * @author Bryzgalova Ekaterina 9307
 * @version 5.1
 */
public class First_lab {
// Объявления графических компонентов
private JFrame bookList;
private DefaultTableModel model;
private JButton delete;
private JButton load;
private JButton save;
private JButton add;
private JButton print;
private JButton info;
private JButton winners;
private JToolBar toolBar;
private JTable books;
private JTextField Name;
private JButton filter;
//Создание окна
public void show() {
// Создание окна
bookList = new JFrame("Гонки");
bookList.setSize(1300, 700);
bookList.setLocation(100, 100);
ImageIcon icon = new ImageIcon("./pictures_OOP/car1.png");
bookList.setIconImage(icon.getImage());
bookList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// Создание кнопок и прикрепление иконок
add = new JButton(new ImageIcon("./pictures_OOP/add.png"));
save = new JButton(new ImageIcon("./pictures_OOP/save.png"));
load = new JButton(new ImageIcon("./pictures_OOP/load.png"));
print = new JButton(new ImageIcon("./pictures_OOP/print.png"));
delete = new JButton(new ImageIcon("./pictures_OOP/delete.png"));
info = new JButton(new ImageIcon("./pictures_OOP/info.png"));
winners=new JButton(new ImageIcon("./pictures_OOP/rating.png"));
filter = new JButton("Search");
// Настройка подсказок для кнопок
add.setToolTipText("Edd position");
load.setToolTipText("Download the list of riders");
save.setToolTipText("Save the list of riders");
print.setToolTipText("Print");
winners.setToolTipText("List of winners");
delete.setToolTipText("Delete a position");
info.setToolTipText("Program information");
// Добавление кнопок на панель инструментов
toolBar = new JToolBar("Toolbar");
toolBar.add(add);
toolBar.add(delete);
toolBar.add(save);
toolBar.add(load);
toolBar.add(print);
toolBar.add(winners);
toolBar.add(info);
// Размещение панели инструментов
bookList.setLayout(new BorderLayout());
bookList.add(toolBar, BorderLayout.NORTH);
// Создание таблицы с данными
final String [] column = {"Team", "Designer", "Chassis","Track","Power plant","Driver 1", "Driver 2","Status"};
Vector<String> columns = new Vector<>();

for(int i = 0;i < column.length;i++) {
    columns.add(column[i]);
}
Vector <Vector<String>> data = new Vector<>();
model= new DefaultTableModel(data, columns);

books = new JTable(model);
final TableRowSorter <TableModel> sorter = new TableRowSorter<>(model);
books.setRowSorter(sorter);
JScrollPane scroll = new JScrollPane(books);
scroll = new JScrollPane(books);
Data d = new Data();
// Обработка нажатия кнопки загрузки базы данных
load.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e) {
        d.LoadFile(bookList,data);
        model.setDataVector(data,columns);
    }
});

// Обработка нажатия кнопки поиск
filter.addActionListener (new ActionListener()
{
    public void actionPerformed (ActionEvent event)
    {
        String text =Name.getText();
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter(text));
        }
    }
});

winners.addActionListener (new ActionListener()
{
	int f=1;
    public void actionPerformed (ActionEvent event)
    {
    	if(f==1) {
    	sorter.setRowFilter(RowFilter.regexFilter("Winer"));
    	f=0;
    	}
    	else {
    		sorter.setRowFilter(null);
    		f=1;
    	}
    	
    }
    });
    

// Обработка нажатия кнопки информация о программе
info.addActionListener (new ActionListener()
{
    public void actionPerformed (ActionEvent event)
    {
        JOptionPane.showMessageDialog (bookList, "Author: Bryzgalova E.A.\nVersion 5.1");
    }
});

// Обработка нажатия кнопки добавления новой позиции
add.addActionListener(new ActionListener()
{
    public void actionPerformed(ActionEvent e)
    {
        d.Add(model,bookList);
    }
});

// Обработка нажатия кнопки удаление
delete.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e) {
        d.Delete(model,bookList);
    }
});

//Обработка нажатия кнопки сохранения
save.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e) {
        d.SaveFile(model);
    }
});
print.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            MessageFormat headerFormat = new MessageFormat("Page {0}");
            MessageFormat footerFormat = new MessageFormat("- {0} -");
            books.print(JTable.PrintMode.FIT_WIDTH, headerFormat, footerFormat);
        } catch (PrinterException pe) {
            System.err.println("Error printing: " + pe.getMessage());
        }
    }
});
/////////////////////////////////////////////////////////////////////////
// Размещение таблицы с данными
bookList.add(scroll, BorderLayout.CENTER);
// Подготовка компонентов поиска


Name = new JTextField("Team");

// Добавление компонентов на панель
JPanel filterPanel = new JPanel();
filterPanel.add(Name);
filterPanel.add(filter);
// Размещение панели поиска внизу окна
bookList.add(filterPanel, BorderLayout.SOUTH);
books.setEnabled(false);
// Визуализация экранной формы
bookList.setVisible(true);
}
public static void main(String[] args) {
// Создание и отображение экранной формы
new First_lab().show();
}
}