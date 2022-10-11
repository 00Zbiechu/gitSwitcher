package changebranch;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.Git;

import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
public class Model {

    @Getter
    @Setter
    private String pathToRepo;

    @Getter
    private static Git git;

    @Getter
    public ArrayList<String> branchList = new ArrayList<>();

    //Listy do sprawdzenia statusu
    @Getter
    private ArrayList<String> addedList = new ArrayList<>();
    @Getter
    private ArrayList<String> uncommittedList = new ArrayList<>();
    @Getter
    private ArrayList<String> untrackedList = new ArrayList<>();

    public Model(){

        log.info("Wykonano konstruktor klasy model");

    }

    public void setGitRepoWithPathFromUser(String pathToRepo) throws IOException {

        git = Git.open(new File(pathToRepo));
        log.info("Utworzenie obiektu git na podstawie ścieżki podanej przez użytkownika");

    }

    public void choosePath(String pathToRepoFromUser) throws IOException {

        setPathToRepo(pathToRepoFromUser);
        setGitRepoWithPathFromUser(pathToRepoFromUser);
        log.info("Wybranie ścieżki repozytorium");

    }



    public void showAllBranches() throws GitAPIException {

        List<Ref> call = git.branchList().call();
        for (Ref ref : call) {
            branchList.add(ref.getName());
        }

        log.info("Pobranie branchy z obiektu git i dodanie ich do tablicy w klasie Model");

    }

    public void clearBranchList(){

        this.branchList.clear();
        log.info("Czyszczenie tablicy branchy");

    }

    public void changeBranch(String branchName) throws GitAPIException {

        git.checkout().setName(branchName).call();
        git.pull();
        log.info("Wywołanie metody zmieniającej aktualny branch na podstawie wyboru użytkownika");

    }

    public void showStatus() throws GitAPIException {

        addedList.clear();
        uncommittedList.clear();
        untrackedList.clear();
        log.info("Czyszczenie list zawierających status, żeby nie zawierały poprzednich wartości");

        Status status = git.status().call();

        Set<String> added = status.getAdded();
        for (String add : added) {
            addedList.add("Added: " + add);
        }

        Set<String> uncommittedChanges = status.getUncommittedChanges();
        for (String uncommit : uncommittedChanges) {
            uncommittedList.add("Uncommitted: " + uncommit);
        }

        Set<String> untracked = status.getUntracked();
        for (String untrack : untracked) {
            untrackedList.add("Untracked: " + untrack);
        }

        log.info("Pobranie statusu Git z obiektu git");

    }

    public static void addChanges() throws GitAPIException {

        git.add().addFilepattern(".").call();
        log.info("Dodanie zmian");

    }

    public void doCommit(String message) throws GitAPIException {


        git.commit().setMessage(message).call();
        log.info("Wysłanie commita na podstawie ustawionej wiadomości");

    }


    public void doPush() throws IOException {

        if(System.getProperty("os.name").contains("Linux")){

            String[] command = { "xterm", "-e", "git", "push" };
            new ProcessBuilder(command).start();

            log.info("Wykonanie push");

        }else{

            Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"cd /d "+pathToRepo+" && git push && Taskkill /im cmd.exe /f \"");
            log.info("Wykonanie push");

        }

    }


}
