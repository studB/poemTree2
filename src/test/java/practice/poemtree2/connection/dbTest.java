package practice.poemtree2.connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import practice.poemtree2.DB.BodyDB;
import practice.poemtree2.DB.BodyDBImpl;
import practice.poemtree2.DB.DBConfig;
import practice.poemtree2.DB.FavorDB;
import practice.poemtree2.DB.FavorDBImpl;
import practice.poemtree2.DB.StarDB;
import practice.poemtree2.DB.StarDBImpl;
import practice.poemtree2.DB.TitleDB;
import practice.poemtree2.DB.TitleDBImpl;
import practice.poemtree2.domain.FavorInfo;
import practice.poemtree2.domain.ListInfo;
import practice.poemtree2.domain.favors.Form;
import practice.poemtree2.domain.favors.Genre;

@SpringBootTest
@Transactional
public class dbTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class);

    @Test
    void starDBTest() {

        StarDB s = ac.getBean(StarDBImpl.class);

        String cid = "cid";
        int star = 5;

        s.insertBody(cid, star);
        int getStar = s.findstarByCID(cid);

        Assertions.assertThat(getStar).isEqualTo(star);

        int updatedStar = 10;

        s.updateStar(cid, updatedStar);
        int getUpdatedStar = s.updateStar(cid, updatedStar);

        Assertions.assertThat(getUpdatedStar).isEqualTo(updatedStar);
    }

    @Test
    void dbUniqueErrorTest() {

        StarDB s = ac.getBean(StarDBImpl.class);

        String cid = "cid";
        int star = 5;

        s.insertBody(cid, star);

        org.junit.jupiter.api.Assertions.assertThrows(DuplicateKeyException.class, () -> s.insertBody(cid, star));
    }

    @Test
    void bodyDBTest() {

        String cid = "0";
        String body = "poem body";

        BodyDB b = ac.getBean(BodyDBImpl.class);

        String getCid = b.insertBody(cid, body);

        org.junit.jupiter.api.Assertions.assertThrows(DuplicateKeyException.class, () -> b.insertBody(getCid, body));

        String getBody = b.findBodyByCID(getCid);

        Assertions.assertThat(getBody).isEqualTo(body);
    }

    @Test
    void titleDBTest() {

        ListInfo lsi = new ListInfo();
        lsi.setCid("a");
        lsi.setAuthor("author");
        lsi.setTitle("title");
        lsi.setDate("date");

        TitleDB tdb = ac.getBean(TitleDBImpl.class);
        tdb.insertTitle(lsi);

        org.junit.jupiter.api.Assertions.assertThrows(DuplicateKeyException.class, () -> tdb.insertTitle(lsi));

        Assertions.assertThat(tdb.findTitleByCID("a")).isEqualTo(lsi);

        ListInfo lsi2 = new ListInfo();
        lsi2.setCid("b");
        lsi2.setAuthor("author");
        lsi2.setTitle("title");
        lsi2.setDate("date");
        tdb.insertTitle(lsi2);

        List<String> cids = new ArrayList<String>();
        cids.add("a");
        cids.add("b");

        Assertions.assertThat(tdb.getListInfobyCIDSet(cids).size()).isEqualTo(2);

    }

    @Test
    void favorDBTest1() {
        String cid = "0";
        FavorInfo fi = new FavorInfo();
        fi.setForm("form");
        fi.setGenre("genre");
        fi.setLanguage("language");

        FavorDB fdb = ac.getBean(FavorDBImpl.class);

        fdb.insertFavor(cid, fi);

        org.junit.jupiter.api.Assertions.assertThrows(DuplicateKeyException.class, () -> fdb.insertFavor(cid, fi));

        Assertions.assertThat(fdb.findFavorByCID(cid)).isEqualTo(fi);
    }

    @Test
    void favorDBTest2forcidList() {
        FavorInfo fi = new FavorInfo();
        fi.setForm(Form.FORMA.name());
        fi.setGenre(Genre.GENREC.name());

        Map<String, String> favorMap = fi.getFavorMap();
        /*
         * for (String s : favorMap.keySet()) { System.out.println("key : " + s +
         * ", values : " + favorMap.get(s)); }
         * 
         */

        FavorDB fdb = ac.getBean(FavorDBImpl.class);

        List<String> result = fdb.getListofCIDonFavor(favorMap);

        for (String s : result) {
            System.out.println(s);
        }

        Assertions.assertThat(result.size()).isGreaterThan(0);

    }

}
