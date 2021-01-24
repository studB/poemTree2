package practice.poemtree2.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import practice.poemtree2.domain.FavorInfo;

public class FavorDBImpl implements FavorDB {

    /*
     * TABLE CREATION SQL Query
     * 
     * CREATE TABLE FAVORDB (CID varchar(255) not null unique, GENRE varchar(255),
     * FORM varchar(255), LANGUAGE varchar(255))
     */

    private JdbcTemplate jdbcTemplate;

    public FavorDBImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String insertFavor(String cid, FavorInfo favorInfo) {
        jdbcTemplate.update("INSERT INTO FAVORDB VALUES(?, ?, ?, ?)", cid, favorInfo.getGenre(), favorInfo.getForm(),
                favorInfo.getLanguage());
        return cid;
    }

    @Override
    public FavorInfo findFavorByCID(String cid) {
        List<FavorInfo> result = jdbcTemplate.query("SELECT GENRE, FORM, LANGUAGE FROM FAVORDB WHERE CID = ?",
                favorInfoMapper(), cid);
        try {
            FavorInfo favorInfo = result.stream().findAny().get();
            return favorInfo;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private RowMapper<FavorInfo> favorInfoMapper() {
        return new RowMapper<FavorInfo>() {

            @Override
            public FavorInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                FavorInfo favorInfo = new FavorInfo();
                favorInfo.setGenre(rs.getString("genre"));
                favorInfo.setForm(rs.getString("form"));
                favorInfo.setLanguage(rs.getString("language"));
                return favorInfo;
            }

        };
    }

    @Override
    public List<String> getListofCIDonFavor(Map<String, String> favorMap) {
        String query = "SELECT CID FROM FAVORDB WHERE ";
        String keys = "(";
        String values = "(";
        for (String key : favorMap.keySet()) {
            keys += key + ",";
            values += "'" + favorMap.get(key) + "',";
        }
        keys = keys.substring(0, keys.length() - 1) + ")";
        values = values.substring(0, values.length() - 1) + ")";

        query = query + keys + " = " + values;

        System.out.println(query);

        return jdbcTemplate.queryForList(query, String.class);
    }

    @Override
    public List<String> getALLCID() {
        return jdbcTemplate.queryForList("SELECT CID FROM FAVORDB", String.class);
    }

}
