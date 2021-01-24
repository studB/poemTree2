package practice.poemtree2.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practice.poemtree2.DB.TitleDB;
import practice.poemtree2.domain.ListInfo;
import practice.poemtree2.repository.ListRepo;
import practice.poemtree2.repository.SessionRepo;

@Service
public class RepoControll {

    TitleDB titleDB;
    SessionRepo sessionRepo;

    @Autowired
    public RepoControll(TitleDB titleDB, SessionRepo sessionRepo) {
        this.titleDB = titleDB;
        this.sessionRepo = sessionRepo;
    }

    /*
     * Test Completed
     */
    public List<ListInfo> refresh(String sessionKey) {
        ListRepo listRepo = sessionRepo.getListRepo(sessionKey);
        List<String> newCIDs = listRepo.getRepoPart();
        return titleDB.getListInfobyCIDSet(newCIDs);
    }

    public void eraseSession(String sessionKey) {
        sessionRepo.eraseKeyAndRepo(sessionKey);
    }

}
