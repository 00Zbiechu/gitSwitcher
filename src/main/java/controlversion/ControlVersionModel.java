package controlversion;

import com.toedter.calendar.JDateChooser;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Scanner;

@Slf4j
public class ControlVersionModel {

    @Setter
    @Getter
    private String version;

    @Getter
    @Setter
    private String pathToRepo;

    public ControlVersionModel(){

        log.info("Wykonano konstruktor klasy ControlVersionModel");

    }

    public void readVersionFromFile() throws IOException {

        File myObj = new File(getPathToRepo()+"/version.properties");

        if (myObj.exists()){

            Scanner myReader = new Scanner(myObj);
            String data = null;

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }


            //version= -- usuwamy substringiem, interesuje nas tylko nr wersji
            setVersion(data.substring(8));

        }else {

            myObj.createNewFile();

            try (PrintWriter out = new PrintWriter(myObj)) {

                out.println("version=00.00.0");

                log.info("Zapisanie zerowej wersji w pliku");


            } catch (FileNotFoundException e) {

                log.warn("Błąd zapisu zerowej wersji do pliku");

            }

            //version= -- usuwamy substringiem, interesuje nas tylko nr wersji
            setVersion("00.00.0");

        }
        log.info("Odczytanie wersji programu z pliku");


    }

    public String getHotfixVersion(){

        log.info("Zwrócenie samego nr wersji");

        return version.substring(6);

    }

    public String getIncrementedHotfixVersion(){

        int oldHotfixVersion = Integer.parseInt(getHotfixVersion());

        int incrementedOldVersion = ++oldHotfixVersion;

        String newVersion = getVersion().substring(0,6)+incrementedOldVersion;

        log.info("Zwrócenie zinkrementowanej wersji hotfix");

        return newVersion;

    }

    public void writeIncrementedHotfixVersionInFile(){


        try (PrintWriter out = new PrintWriter(getPathToRepo()+"/version.properties")) {

            out.println("version="+getIncrementedHotfixVersion());

            setVersion(getIncrementedHotfixVersion());

            log.info("Zapisanie zinkrementowanej wersji hotfix w pliku");


        } catch (FileNotFoundException e) {


            log.warn("Błąd zapisu nowej wersji do pliku");

        }

    }

    public void writeNewDateInFile(JDateChooser dateChooser){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(dateChooser.getDate());

        String month = date.substring(5,7);
        String day = date.substring(8,10);
        String hotfix = getHotfixVersion();

        //Jeśli data jest inna niż poprzednia, to wyzeruj hotfix
        if(!(version.contains(day+"."+month))){

            hotfix="0";

            log.info("Data inna niż poprzednia, wyzerowano hotfix");

        }


        String newVersionWithDate = "version="+day+"."+month+"."+hotfix;


        try (PrintWriter out = new PrintWriter(getPathToRepo()+"/version.properties")) {

            out.println(newVersionWithDate);

            setVersion(day+"."+month+"."+hotfix);

            log.info("Zapisanie nowej wersji w pliku");


        } catch (FileNotFoundException e) {


            log.warn("Błąd zapisu nowej wersji do pliku");

        }



    }

}