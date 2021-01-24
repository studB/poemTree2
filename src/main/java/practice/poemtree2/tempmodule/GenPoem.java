package practice.poemtree2.tempmodule;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
import practice.poemtree2.domain.favors.Language;

public class GenPoem {

    public void genPoem(int startNum, int endNum) throws PoorSettingException {

        ApplicationContext ac = new AnnotationConfigApplicationContext(DBConfig.class);
        FavorDB favorDB = ac.getBean(FavorDBImpl.class);
        TitleDB titleDB = ac.getBean(TitleDBImpl.class);
        BodyDB bodyDB = ac.getBean(BodyDBImpl.class);
        StarDB starDB = ac.getBean(StarDBImpl.class);

        if (endNum <= startNum) {
            throw new PoorSettingException("Reset numbers so that endNum is greater than startNum");
        }

        for (int i = startNum; i < endNum; i++) {
            setGenCount(i);
            setValues();

            FavorInfo fi = new FavorInfo();
            fi.setForm(this.form);
            fi.setGenre(this.genre);
            fi.setLanguage(this.language);

            ListInfo li = new ListInfo();
            li.setCid(this.cid);
            li.setAuthor(this.author);
            li.setTitle(this.title);
            li.setDate(this.date);
            favorDB.insertFavor(cid, fi);
            titleDB.insertTitle(li);
            bodyDB.insertBody(cid, body);
            starDB.insertBody(cid, star);
        }
    }

    @Override
    public String toString() {
        return "{" + ", cid='" + getCid() + "'" + ", genre='" + getGenre() + "'" + ", form='" + getForm() + "'"
                + ", language='" + getLanguage() + "'" + ", title='" + getTitle() + "'" + ", author='" + getAuthor()
                + "'" + ", body='" + getBody() + "'" + ", date='" + getDate() + "'" + ", star='" + getStar() + "'"
                + "}";
    }

    int genCount;
    Random rand = new Random();
    String cid;
    String genre;
    String form;
    String language;
    String title;
    String author;
    String body;
    String date;
    int star = 0;

    public int getGenCount() {
        return this.genCount;
    }

    public void setGenCount(int genCount) {
        this.genCount = genCount;
    }

    public String getCid() {
        return this.cid;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getForm() {
        return this.form;
    }

    public String getLanguage() {
        return this.language;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getBody() {
        return this.body;
    }

    public String getDate() {
        return this.date;
    }

    public int getStar() {
        return this.star;
    }

    private void setValues() {
        this.cid = fakecid();
        this.genre = fakeGenre();
        this.form = fakeForm();
        this.language = fakeLanguage();
        this.author = fakeAuthor();
        this.title = fakeTitle();
        this.body = fakeBody();
        this.date = fakeDate();
    }

    private String fakecid() {
        return UUID.randomUUID().toString();
    }

    private String fakeGenre() {
        int len = Genre.values().length;
        int rindex = this.rand.nextInt(len);
        return Genre.values()[rindex].toString();
    }

    private String fakeForm() {
        int len = Form.values().length;
        int rindex = this.rand.nextInt(len);
        return Form.values()[rindex].toString();
    }

    private String fakeLanguage() {
        int len = Language.values().length;
        int rindex = this.rand.nextInt(len);
        return Language.values()[rindex].toString();
    }

    private String fakeTitle() {
        return "title" + getGenCount();
    }

    private String fakeAuthor() {
        return "author" + getGenCount();
    }

    private String fakeBody() {
        return "body" + getGenCount();
    }

    private String fakeDate() {
        Date dateObject = new Date();
        return dateObject.toString();
    }

    public class PoorSettingException extends Exception {

        PoorSettingException(String msg) {
            super(msg);
        }

    }

}
