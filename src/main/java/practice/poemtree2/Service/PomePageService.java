package practice.poemtree2.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practice.poemtree2.DB.BodyDB;
import practice.poemtree2.DB.StarDB;
import practice.poemtree2.DB.TitleDB;
import practice.poemtree2.domain.ListInfo;

@Service
public class PomePageService {

    TitleDB titleDB;
    BodyDB bodyDB;
    StarDB starDB;

    @Autowired
    public PomePageService(TitleDB titleDB, BodyDB bodyDB, StarDB starDB) {
        this.titleDB = titleDB;
        this.bodyDB = bodyDB;
        this.starDB = starDB;
    }

    public Map<String, String> onePoemInfo(String cid) {

        Map<String, String> info = new HashMap<String, String>();
        ListInfo listInfo = titleDB.findTitleByCID(cid);
        String title = listInfo.getTitle();
        String author = listInfo.getAuthor();
        String date = listInfo.getDate();
        String body = bodyDB.findBodyByCID(cid);
        String star = starDB.findstarByCID(cid) + "";
        info.put("title", title);
        info.put("author", author);
        info.put("date", date);
        info.put("body", body);
        info.put("star", star);

        return info;
    }

    public void plusStar(String cid, int star) {
        star += 1;
        starDB.updateStar(cid, star);
    }

}
