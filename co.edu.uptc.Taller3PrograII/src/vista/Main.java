package vista;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.UIManager;

public class Main {

	static {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Mini Encuesta");
        frame.setSize(250, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
        JLabel operatingSystemLabel = new JLabel("Elige un sistema operativo");
        JRadioButton radioButton1 = new JRadioButton("Windows");
        JRadioButton radioButton2 = new JRadioButton("Linux");
        JRadioButton radioButton3 = new JRadioButton("Mac");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);

        JSeparator separator1 = new JSeparator(JSeparator.HORIZONTAL);
        separator1.setMaximumSize(new Dimension(500, 5));

        JLabel specialtyLabel = new JLabel("Elije tu especialidad");
        JCheckBox programming = new JCheckBox("Programacion");
        JCheckBox graphicDesing = new JCheckBox("Diseño Gráfico");
        JCheckBox administration = new JCheckBox("Administracion");

        JSeparator separator2 = new JSeparator(JSeparator.HORIZONTAL);
        separator2.setMaximumSize(new Dimension(500, 5));

        JLabel hoursLabel = new JLabel("Horas que dedicas en el ordenador");
        JSlider hoursSlider= new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
        hoursSlider.setMajorTickSpacing(1);
        hoursSlider.setPaintLabels(true);
        hoursSlider.setPaintTicks(true);

        JButton btn = new JButton("Generar");
        btn.addActionListener(e -> {
            String os = radioButton1.isSelected() ?
                    radioButton1.getText() : radioButton2.isSelected() ?
                    radioButton2.getText() : radioButton3.getText();

            int counter = 0;
            StringBuilder stringBuilder = new StringBuilder();
            for (JCheckBox cb : List.of(programming, graphicDesing, administration)){
                if (cb.isSelected()) {
                    counter++;
                    stringBuilder.append(cb.getText()).append(", ");
                }
            }
            if (!stringBuilder.isEmpty()){
                stringBuilder.delete(stringBuilder.lastIndexOf(","), stringBuilder.length());
                if (counter > 1){
                    int idx = stringBuilder.lastIndexOf(",");
                    stringBuilder.replace(idx, idx + 1, " y");
                }
            }

            int hours = hoursSlider.getValue();

            String msg = "Tu sistema operativo preferido es " + os   + "\n" +
                    (counter > 0 ? counter > 1 ? "Tus especialidades son " : "Tu especialidad es ": "No tiene especialidad") +
                    stringBuilder + "\nEl numero de horas dedicadas al ordenador son " + hours + " horas";

            JOptionPane.showMessageDialog(frame, msg, "Muestra de datos", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(operatingSystemLabel);
        panel.add(radioButton1);
        panel.add(radioButton2);
        panel.add(radioButton3);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(separator1);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(specialtyLabel);
        panel.add(programming);
        panel.add(graphicDesing);
        panel.add(administration);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(separator2);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(hoursLabel);
        panel.add(hoursSlider);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(btn);

        frame.setContentPane(panel);
        frame.setVisible(true);
		
	}

}
