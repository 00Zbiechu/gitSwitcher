package controlversion;

import com.toedter.calendar.JDateChooser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

@Slf4j
public class ControlVersionView extends JDialog {

    @Getter
    private final JDateChooser calendar;
    @Getter
    private final JCheckBox chckbxHotfix;
    @Getter
    private final JButton okButton;
    @Getter
    private final JLabel currentVersionLabel;




    public ControlVersionView() {

        // Kod wygenerowany przy pomocy WindowBuilder

        setVisible(true);
        setAlwaysOnTop(true);
        setFocusableWindowState(true);
        setBounds(80, 300, 450, 151);
        getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.WEST);

        JLabel lblProszePodaDat = new JLabel("Prosze podać datę nowej wersji");


        calendar = new JDateChooser();
        calendar.setDateFormatString("yyyy-MM-dd");

        JLabel lblData = new JLabel("Data:");


        chckbxHotfix = new JCheckBox("Wersja hotfix");
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addContainerGap(65, Short.MAX_VALUE)
                                .addComponent(lblData)
                                .addGap(12)
                                .addComponent(calendar)
                                .addGap(18)
                                .addComponent(chckbxHotfix)
                                .addGap(217))
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addGap(103)
                                .addComponent(lblProszePodaDat)
                                .addContainerGap(257, Short.MAX_VALUE))
        );
        gl_contentPanel.setVerticalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addComponent(lblProszePodaDat)
                                .addGap(18)
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(calendar)
                                        .addComponent(lblData)
                                        .addComponent(chckbxHotfix))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                currentVersionLabel = new JLabel("Brak poprzedniej wersji");
                buttonPane.add(currentVersionLabel);
                okButton = new JButton("OK");
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }

        log.info("Wykonano konstruktor klasy ControlVersionView");

    }

    public void setVersionLabel(String version){

        getCurrentVersionLabel().setText("Poprzednia wersja to: "+version);

        log.info("Ustawienie wersji aktualnej w obiekcie ControlVersionView");

    }

    public void addActionListener(ActionListener actionListener){

        okButton.addActionListener(actionListener);

        log.info("Dodanie actionListenera do klasy ControlVersionView");

    }

}

