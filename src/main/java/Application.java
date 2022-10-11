import changebranch.Controller;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Application {

    public static void main(String[] args) {

        log.info("Uruchomienie aplikacji");

        new Controller();


    }

}
