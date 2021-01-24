package practice.poemtree2.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class SessionRepo {

    private Set<String> sessionKeys;
    private Map<String, ListRepo> listCIDBySession;

    private SessionRepo() {
        this.sessionKeys = new HashSet<String>();
        this.listCIDBySession = new HashMap<String, ListRepo>();
    }

    public void addKey(String sessionKey) {
        this.sessionKeys.add(sessionKey);
    }

    public void saveList(String sessionKey, List<String> CIDs) {
        ListRepo listRepo = new ListRepo();
        listRepo.setRepo(CIDs);
        this.listCIDBySession.put(sessionKey, listRepo);
    }

    public ListRepo getListRepo(String sessionKey) {
        return this.listCIDBySession.get(sessionKey);
    }

    public void eraseKeyAndRepo(String sessionKey) {
        this.sessionKeys.remove(sessionKey);
        this.listCIDBySession.remove(sessionKey);
    }
}
