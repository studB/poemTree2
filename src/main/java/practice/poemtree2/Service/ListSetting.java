package practice.poemtree2.Service;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import practice.poemtree2.DB.FavorDB;
import practice.poemtree2.DB.TitleDB;
import practice.poemtree2.domain.FavorInfo;
import practice.poemtree2.domain.ListInfo;
import practice.poemtree2.repository.ListRepo;
import practice.poemtree2.repository.SessionRepo;

@Scope("prototype")
@Service
public class ListSetting {

    FavorDB favorDB;
    TitleDB titleDB;
    SessionRepo sessionRepo;
    String sessionKey;
    ListRepo listRepo;

    @Autowired
    public ListSetting(FavorDB favorDB, TitleDB titleDB, SessionRepo sessionRepo) {
        this.favorDB = favorDB;
        this.titleDB = titleDB;
        this.sessionRepo = sessionRepo;
    }

    @PostConstruct
    public void init() {
        sessionKey = UUID.randomUUID().toString();
        listRepo = new ListRepo();
        System.out.println("Initailization!!!");
    }

    public void setRepo(List<String> CIDs) {
        listRepo.setRepo(CIDs);
        sessionRepo.saveList(this.sessionKey, CIDs);
    }

    public String getSessionKey() {
        sessionRepo.addKey(this.sessionKey);
        return this.sessionKey;
    }

    /*
     * Test Completed
     */
    public List<ListInfo> initCallList(FavorInfo favorInfo) {

        if (favorInfo.getFavorMap().isEmpty()) {
            setRepo(favorDB.getALLCID());
        } else {
            setRepo(favorDB.getListofCIDonFavor(favorInfo.getFavorMap()));
        }

        List<String> partCIDS = listRepo.getRepoPart();

        return titleDB.getListInfobyCIDSet(partCIDS);
    }

}
