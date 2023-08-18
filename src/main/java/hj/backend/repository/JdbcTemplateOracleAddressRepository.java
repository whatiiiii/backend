package hj.backend.repository;

import hj.backend.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class JdbcTemplateOracleAddressRepository implements AddressRepository {
    private final JdbcTemplate jdbcTemplate; //내부적으로 이름은 클래스 명에서 제일앞글자를 소문자로 바꿔주는것으로 쓴다
   // @Autowired
    public JdbcTemplateOracleAddressRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Address> list() {
        String sql = "select * from ADDRESS order by SEQ desc";
        List<Address> list = jdbcTemplate.query(sql, addressRowMapper());
        return list;
    }

    private RowMapper<Address> addressRowMapper(){
        return new RowMapper<Address>() {
            @Override
            public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
                Address address = new Address();
                address.setSeq(rs.getLong("seq"));
                address.setName(rs.getString("name"));
                address.setAddr(rs.getString("addr"));
                address.setRdate(rs.getDate("rdate"));
                return address;
            }
        };
    }
/*
    @Override
    public Address insert(Address address) {
        long seq = jdbcTemplate.queryForObject("select ADDRESS_SEQ.nextval from dual", Long.class);
        String sql = "insert into ADDRESS values(?, ?, ?, SYSDATE)";
        jdbcTemplate.update(sql, seq, address.getName(), address.getAddr());
        address.setSeq(seq);

        return address;
    }
 */
    @Override
    public Address insert(Address address){
        String sql = "insert into ADDRESS values(ADDRESS_SEQ.nextval, ?,?, SYSDATE)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"seq"});
            ps.setString(1, address.getName());
            ps.setString(2, address.getAddr());
            return ps;
        }, keyHolder);
        long key = keyHolder.getKey().longValue();
        address.setSeq(key);
        return address;
    }


    /*
    public boolean insert(Address address){ //boolean을 리턴할 경우...
        String sql ="insert into ADDRESS values(ADDRESS_SEQ.nextval, ?,?, SYSDATE)";
        int count = jdbcTemplate.update(sql, address.getName(), address.getAddr(), address.getRdate());
        if(count>0) return  true;
        else return false;
    }
    */
    @Override
    public boolean delete(long seq) {
        String sql = "delete from ADDRESS where SEQ=?";
        int count = jdbcTemplate.update(sql, seq); //삭제된 row의 갯수 return
        if(count>0) return true;
        else return false;
    }
}
