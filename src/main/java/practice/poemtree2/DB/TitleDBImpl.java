package practice.poemtree2.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import practice.poemtree2.domain.ListInfo;

public class TitleDBImpl implements TitleDB {

    /*
     * TABLE CREATION SQL Query
     * 
     * CREATE TABLE TITLEDB (CID varchar(255) not null unique, TITLE varchar(255),
     * AUTHOR varchar(255), DATE varchar(255))
     */

    private JdbcTemplate jdbcTemplate;

    public TitleDBImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String insertTitle(ListInfo listInfo) {
        jdbcTemplate.update("INSERT INTO TITLEDB VALUES(?,?,?,?)", listInfo.getCid(), listInfo.getTitle(),
                listInfo.getAuthor(), listInfo.getDate());
        return listInfo.getCid();
    }

    @Override
    public ListInfo findTitleByCID(String cid) {
        List<ListInfo> result = jdbcTemplate.query("SELECT * FROM TITLEDB WHERE CID = ?", listInfoMapper(), cid);
        try {
            ListInfo myList = result.stream().findAny().get();
            return myList;
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public List<ListInfo> getListInfobyCIDSet(List<String> CID) {
        String conditionString = "(";
        for (String cid : CID) {
            conditionString += "'" + cid + "',";
        }
        conditionString = conditionString.substring(0, conditionString.length() - 1);
        conditionString += ")";

        String query = "SELECT * FROM TITLEDB WHERE CID IN " + conditionString;

        System.out.println(query);

        return jdbcTemplate.query(query, listInfoMapper());
    }

    private RowMapper<ListInfo> listInfoMapper() {
        return new RowMapper<ListInfo>() {

            @Override
            public ListInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
                ListInfo li = new ListInfo();
                li.setCid(rs.getString("cid"));
                li.setTitle(rs.getString("title"));
                li.setAuthor(rs.getString("author"));
                li.setDate(rs.getString("date"));
                return li;
            }

        };
    }

}
