package changebranch;

import amendcommit.AmendController;
import controlversion.ControlVersionController;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@Slf4j
public class Controller {


    private final View view;

    private final Model model;

    private final ArrayList<JButton> branchButtonArray = new ArrayList<>();




    public Controller(){

        this.view = new View();
        this.model = new Model();


        view.addViewListener(new actionListenerForView());

        log.info("Wykonano konstruktor klasy Controller");



    }

    public class actionListenerForView implements ActionListener {

        @SneakyThrows
        @Override
        public void actionPerformed(ActionEvent actionEvent) {


            if (actionEvent.getSource() == view.getConfirmYourPathButton()) {

                log.info("Naciśnięto przycisk wybierz ścieżkę");

                try {

                    if (!view.getInsertYourPath().getText().isEmpty()) {

                        log.info("Podana ścieżka nie jest pusta");

                        model.choosePath(view.getInsertYourPath().getText());

                        model.showAllBranches();

                        //Usunięcie branchy przy wybraniu ścieżki, żeby buttony nie były duplikowane,
                        //po ponownym wybraniu ścieżki
                        view.getBranchPanel().removeAll();
                        view.getBranchPanel().revalidate();
                        view.getBranchPanel().repaint();
                        branchButtonArray.clear();

                        log.info("Usunięcie branchy przy wybraniu ścieżki, żeby buttony nie były duplikowane,\n" +
                                "po ponownym wybraniu ścieżki");

                        //Wyczyszczenie pola statusu
                        view.getAreaForStatus().setText("");

                        log.info("Wyczyszczenie pola statusu");


                        for (String name : model.getBranchList()) {

                            JButton branchButton = new JButton(name);
                            branchButton.addActionListener(new BranchActionListener());

                            view.getBranchPanel().add(branchButton);
                            branchButtonArray.add(branchButton);

                        }

                        log.info("Dodanie przycisków na podstawie branchListy z klasy Model");

                        view.getBranchPanel().revalidate();
                        view.getBranchPanel().repaint();

                        log.info("Wykonanie revalidate i repaint na panelu zawierającym gałęzie");

                        view.getConfirmYourPathButton().setBackground(Color.GREEN);

                        log.info("Ustawienie koloru zielonego na przycisk potwierdzenia wybrania ścieżki");

                        //Usunięcie branchy przy wybraniu ścieżki, żeby buttony nie były duplikowane,
                        //po ponownym wybraniu ścieżki
                        model.clearBranchList();

                        log.info("Usunięcie branchy przy wybraniu ścieżki, żeby buttony nie były duplikowane,\n" +
                                "po ponownym wybraniu ścieżki");


                        //Odblokowanie pozostałych funkcjonalności
                        view.getCheckStatusButton().setEnabled(true);
                        view.getAreaForStatus().setEnabled(true);
                        view.getButtonAddChanges().setEnabled(true);
                        view.getMessageForCommit().setEnabled(true);
                        view.getConfirmCommit().setEnabled(true);
                        view.getDoPushButton().setEnabled(true);
                        view.getDoAmend().setEnabled(true);

                        log.info("Odblokowanie innych przycisków zablokowanych przy starcie aplikacji");


                    } else {

                        JOptionPane.showMessageDialog(view, "Nie podano ścieżki do repozytorium!");
                        log.warn("Nie podano ścieżki do repozytorium");

                    }


                } catch (Exception e) {

                    JOptionPane.showMessageDialog(view, "Nie ma repozytorium GIT pod tą ścieżką!");
                    log.warn("Nie ma repozytorium GIT pod tą ścieżką");
                }


            } else if (actionEvent.getSource() == view.getCheckStatusButton()) {

                log.info("Wybrano przycisk pokaż status");

                view.getAreaForStatus().setText("");

                model.showStatus();

                for (String added : model.getAddedList()) {

                    view.getAreaForStatus().append(added + "\n");

                }

                log.info("Dodanie do pola zawierającego status elementy 'dodane'");

                for (String uncommitted : model.getUncommittedList()) {

                    view.getAreaForStatus().append(uncommitted + "\n");

                }

                log.info("Dodanie do pola zawierającego status elementy 'nie commitowane'");

                for (String untrack : model.getUntrackedList()) {

                    view.getAreaForStatus().append(untrack + "\n");

                }

                log.info("Dodanie do pola zawierającego status elementy 'nie śledzone'");

                model.getAddedList().clear();
                model.getUncommittedList().clear();
                model.getUntrackedList().clear();

            } else if (actionEvent.getSource() == view.getButtonAddChanges()) {

                log.info("Wybranie przycisku dodaj zmiany");

                new ControlVersionController(model.getPathToRepo());


            } else if (actionEvent.getSource() == view.getConfirmCommit()) {

                log.info("Wybranie przycisku wykonania commita");

                model.doCommit(view.getMessageForCommit().getText());


            } else if(actionEvent.getSource()==view.getDoAmend()){

                log.info("Wybranie przycisku wykonania zmiany nazwy commita");

                new AmendController();

            } else if(actionEvent.getSource()==view.getDoPushButton()){

                log.info("Wybranie przycisku wykonania push do Github");

                model.doPush();
            }

        }

        public class BranchActionListener implements ActionListener{


            @SneakyThrows
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                for(JButton button : branchButtonArray){

                    if(actionEvent.getSource()==button){

                        log.info("Wybrano przycisk z nazwą gałęzi");

                        model.changeBranch(button.getText());

                        log.info("Wykonanie metody zmiany gałęzi");

                        button.setBackground(Color.GREEN);

                        log.info("Zmiana koloru tła wybranej gałęzi na kolor zielony 'gałąź aktywna'");

                    }else{

                        button.setBackground(Color.RED);
                        button.setForeground(Color.WHITE);

                        log.info("Ustawienie nie wybranych gałęzi na kolor czerwony i czcionkę białą");



                    }

                }

            }
        }

    }

}
