import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Class for work to data
 */
public class DataBase {

    /**
     * Method for add new position in table
     * @param model - model of table
     * @param frame - object type of window
     */
    public void AddWeapon(final DefaultTableModel model, final JFrame frame) {
        final JDialog window = new JDialog(frame,"Add weapon");
        window.setModal(true);
        window.setSize(350,325);
        window.setLocation(300,300);
        window.setResizable(false);

        Container contentPane = window.getContentPane();

        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);

        Component labelCountry = new JLabel("Страна");
        final JTextField textCountry = new JTextField(15);

        contentPane.add(labelCountry);
        contentPane.add(textCountry);

        layout.putConstraint(SpringLayout.WEST , labelCountry, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelCountry, 25,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, textCountry, 25,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , textCountry, 74,
                SpringLayout.EAST , labelCountry      );

        Component labelManufactor = new JLabel("Производитель");
        final JTextField textManufactor = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , labelManufactor, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelManufactor, 50,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, textManufactor, 50,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , textManufactor, 74,
                SpringLayout.EAST , labelCountry      );

        contentPane.add(labelManufactor);
        contentPane.add(textManufactor);

        Component labelCalibr = new JLabel("Калибр");
        final JTextField textCalibr = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , labelCalibr, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelCalibr, 75,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, textCalibr, 75,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , textCalibr, 72,
                SpringLayout.EAST , labelCalibr      );

        contentPane.add(labelCalibr);
        contentPane.add(textCalibr);

        Component labelGunType = new JLabel("Тип ствола");
        final String gunType[] = {"Нарезной","Гладкий"};
        final JComboBox comboGunType = new JComboBox<>(gunType);

        layout.putConstraint(SpringLayout.WEST , labelGunType, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelGunType, 100,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, comboGunType, 100,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , comboGunType, 50,
                SpringLayout.EAST , labelGunType      );

        contentPane.add(labelGunType);
        contentPane.add(comboGunType);

        Component labelCountAmmo = new JLabel("Ёмкость магазина");
        final JTextField textCountAmmo = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , labelCountAmmo, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelCountAmmo, 125,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, textCountAmmo, 125,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , textCountAmmo, 11,
                SpringLayout.EAST , labelCountAmmo      );

        contentPane.add(labelCountAmmo);
        contentPane.add(textCountAmmo);

        Component labelWeight = new JLabel("Вес");
        final JTextField textWeight = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , labelWeight, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelWeight, 150,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, textWeight, 150,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , textWeight, 93,
                SpringLayout.EAST , labelWeight      );

        contentPane.add(labelWeight);
        contentPane.add(textWeight);

        Component labelCost = new JLabel("Цена");
        final JTextField textCost = new JTextField(15);

        layout.putConstraint(SpringLayout.WEST , labelCost, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelCost, 175,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, textCost, 175,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , textCost, 85,
                SpringLayout.EAST , labelCost      );

        contentPane.add(labelCost);
        contentPane.add(textCost);

        Component labelAvailability = new JLabel("Наличие");
        final String Av[] = {"Есть","Нет","Под заказ"};
        final JComboBox comboAvailability = new JComboBox<>(Av);

        layout.putConstraint(SpringLayout.WEST , labelAvailability, 10,
                SpringLayout.WEST , contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelAvailability, 200,
                SpringLayout.NORTH, contentPane);

        layout.putConstraint(SpringLayout.NORTH, comboAvailability, 200,
                SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.WEST , comboAvailability, 64,
                SpringLayout.EAST , labelAvailability      );

        contentPane.add(labelAvailability);
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

            public void actionPerformed(ActionEvent e) {
                Vector <String> s = new Vector<>();
                s.add(textCountry.getText());
                s.add(textManufactor.getText());
                s.add(textCalibr.getText());
                s.add(gunType[comboGunType.getSelectedIndex()]);
                s.add(textCountAmmo.getText());
                s.add(textWeight.getText());
                s.add(textCost.getText());
                s.add(Av[comboAvailability.getSelectedIndex()]);

                int count = 0;
                for(String s1:s) {
                    if((s1.length() > 0) && (!s1.equals(" "))) {
                        count++;
                    }
                }

                if(count == 8) {
                    //s.add(0,String.valueOf(model.getRowCount()+1));
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

}

