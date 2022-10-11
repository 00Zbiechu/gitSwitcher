package controlversion;


import changebranch.Model;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

@Slf4j
public class ControlVersionController {

    private final ControlVersionModel model;
    private final ControlVersionView view;


    public ControlVersionController(String pathToRepo) throws IOException {

        this.model = new ControlVersionModel();
        this.view = new ControlVersionView();

        model.setPathToRepo(pathToRepo);
        log.info("Ustawienie ścieżki do repozytorium");
        model.readVersionFromFile();

        view.setVersionLabel(model.getVersion());
        view.addActionListener(new ActionListenerForVersionDialog());

        log.info("Wykonano konstruktor klasy ControlVersionController");

    }

    class ActionListenerForVersionDialog implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            if(actionEvent.getSource()==view.getOkButton()) {

                log.info("Wykonano wybranie przycisku OK w obiekcie klasy ControlVersionView");

                if (view.getChckbxHotfix().isSelected()) {

                    log.info("Sprawdzono, że pole hotfix jest zaznaczone");

                    model.writeIncrementedHotfixVersionInFile();

                }

                if (!(view.getCalendar().getDate() == null)) {

                    log.info("Sprawdzono, że data została wybrana");

                    model.writeNewDateInFile(view.getCalendar());

                }

            }

            try {
                Model.addChanges();
            } catch (GitAPIException e) {
                e.printStackTrace();
            }

            view.dispose();

            log.info("Zamknięto obiekt klasy ControlVersionView");

        }

    }

}
