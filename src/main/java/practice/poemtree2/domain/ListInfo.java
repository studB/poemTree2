package practice.poemtree2.domain;

import java.util.Objects;

public class ListInfo {

    String cid;
    String title;
    String author;
    String date;

    public String getCid() {
        return this.cid;
    }

    @Override
    public String toString() {
        return "{" + " cid='" + getCid() + "'" + ", title='" + getTitle() + "'" + ", author='" + getAuthor() + "'"
                + ", date='" + getDate() + "'" + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ListInfo)) {
            return false;
        }
        ListInfo listInfo = (ListInfo) o;
        return Objects.equals(cid, listInfo.cid) && Objects.equals(title, listInfo.title)
                && Objects.equals(author, listInfo.author) && Objects.equals(date, listInfo.date);
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
