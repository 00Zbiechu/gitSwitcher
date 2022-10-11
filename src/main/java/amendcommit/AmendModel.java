package amendcommit;

import changebranch.Model;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.errors.GitAPIException;

@Slf4j
public class AmendModel {

    public void setAmend(String message) throws GitAPIException {

        Model.getGit().commit().setAmend(true).setMessage(message).call();

        log.info("Wykonano podmianę nazwy commita na podstawie podanych danych przez usera");

    }

}
