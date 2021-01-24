package practice.poemtree2.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FavorInfo {

    String genre;
    String form;
    String language;

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FavorInfo)) {
            return false;
        }
        FavorInfo favorInfo = (FavorInfo) o;
        return Objects.equals(genre, favorInfo.genre) && Objects.equals(form, favorInfo.form)
                && Objects.equals(language, favorInfo.language);
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getForm() {
        return this.form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Map<String, String> getFavorMap() {
        Map<String, String> favorMap = new HashMap<String, String>();

        if (this.genre != null) {
            favorMap.put("GENRE", this.genre);
        }

        if (this.form != null) {
            favorMap.put("FORM", this.form);
        }

        if (this.language != null) {
            favorMap.put("LANGUAGE", this.language);
        }

        return favorMap;

    }

}
