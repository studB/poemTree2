package practice.poemtree2.DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class StarDBImpl implements StarDB {

    /*
     * TABLE CREATION SQL Query
     * 
     * CREATE TABLE STARDB (CID varchar(255) not null unique, STAR int)
     */

    private JdbcTemplate jdbcTemplate;

    public StarDBImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String insertBody(String cid, int star) {
        jdbcTemplate.update("INSERT INTO STARDB VALUES(?,?)", cid, star);
        return cid;
    }

    @Override
    public int findstarByCID(String cid) {
        List<StarRow> result = jdbcTemplate.query("SELECT * FROM STARDB WHERE CID = ?", starRowMapper(), cid);
        return result.stream().findAny().get().getStar();
    }

    @Override
    public int updateStar(String cid, int star) {
        jdbcTemplate.update("UPDATE STARDB SET STAR = ? WHERE CID = ?", star, cid);

        return star;
    }

    private RowMapper<StarRow> starRowMapper() {
        return new RowMapper<StarRow>() {
            @Override
            public StarRow mapRow(ResultSet rs, int rowNum) throws SQLException {
                StarRow starRow = new StarRow();
                starRow.setCid(rs.getString("cid"));
                starRow.setStar(rs.getInt("star"));
                return starRow;
            }
        };
    }

    private class StarRow {

        String cid;
        int star;

        public String getCid() {
            return this.cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public int getStar() {
            return this.star;
        }

        public void setStar(int star) {
            this.star = star;
        }

    }

}
