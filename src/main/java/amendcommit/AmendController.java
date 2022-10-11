package amendcommit;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Slf4j
public class AmendController {

    private final AmendModel model;
    private final AmendView view;

    public AmendController(){

        this.model = new AmendModel();
        this.view = new AmendView();


        view.addActionListenerForAmendDialog(new ActionListenerForAmend());

        log.info("Wykonano konstruktor klasy AmendController");

    }

    class ActionListenerForAmend implements ActionListener{

        @SneakyThrows
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==view.getOkButton()){

                log.info("Przycisk OK w polu zmiany nazwy commita został aktywowany");

                model.setAmend(view.getTextFieldMessageForAmend().getText());

                view.dispose();

                log.info("Zamknięcie okna AmendView");

            }

        }

    }



}
