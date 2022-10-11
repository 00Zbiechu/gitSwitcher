package amendcommit;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionListener;

@Slf4j
public class AmendView extends JDialog {

    @Getter
    private final JLabel lblMessage;
    @Getter
    private final JTextField textFieldMessageForAmend;
    @Getter
    private final JButton okButton;

    public AmendView(){

        // Kod wygenerowany przy pomocy WindowBuilder

        setVisible(true);
        setAlwaysOnTop(true);
        setFocusableWindowState(true);

        setBounds(100, 100, 450, 149);
        getContentPane().setLayout(null);
        {
            textFieldMessageForAmend = new JTextField();
            textFieldMessageForAmend.setBounds(26, 39, 379, 25);
            getContentPane().add(textFieldMessageForAmend);
            textFieldMessageForAmend.setColumns(10);
        }
        {
            lblMessage = new JLabel("Nazwa, która zastąpi nazwę ostatniego commita");
            lblMessage.setBounds(46, 12, 348, 15);
            getContentPane().add(lblMessage);
        }
        {

            okButton = new JButton("OK");
            okButton.setBounds(351, 76, 54, 25);
            getContentPane().add(okButton);
            okButton.setActionCommand("OK");
            getRootPane().setDefaultButton(okButton);
        }

        log.info("Wykonano konstruktor klasy AmendView");

    }


    public void addActionListenerForAmendDialog(ActionListener actionListener){

        okButton.addActionListener(actionListener);

        log.info("Dodanie action listenera do pola okButton w klasie AmendView");

            log.info("Dodanie action listenera do pola okButton w klasie AmendView");

    }

}



