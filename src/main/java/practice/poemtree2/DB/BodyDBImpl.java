package practice.poemtree2.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BodyDBImpl implements BodyDB {

    /*
     * TABLE CREATION SQL Query
     * 
     * CREATE TABLE BODYDB (CID varchar(255) not null unique, BODY varchar(255))
     */

    private JdbcTemplate jdbcTemplate;

    public BodyDBImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String insertBody(String cid, String body) {
        jdbcTemplate.update("INSERT INTO BODYDB VALUES(?,?)", cid, body);
        return cid;
    }

    @Override
    public String findBodyByCID(String cid) {
        List<String> result = jdbcTemplate.query("SELECT BODY FROM BODYDB WHERE CID = ?", bodyMapper(), cid);
        return result.stream().findAny().get();
    }

    private RowMapper<String> bodyMapper() {
        return new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("body");
            }
        };
    }

}
