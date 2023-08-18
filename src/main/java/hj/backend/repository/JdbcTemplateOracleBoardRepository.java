package hj.backend.repository;

import hj.backend.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//@Repository
public class JdbcTemplateOracleBoardRepository implements BoardRepository {
    private final JdbcTemplate jdbcTemplate;
  //  @Autowired
    public JdbcTemplateOracleBoardRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Board> list() {
        String sql = "select * from BOARD order by SEQ desc";
        List<Board> list = jdbcTemplate.query(sql, boardRowMapper());
        return list;
    }

    private RowMapper<Board> boardRowMapper(){
        return new RowMapper<Board>() {
            @Override
            public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                Board board = new Board();
                board.setSeq(rs.getLong("seq"));
                board.setWriter(rs.getString("writer"));
                board.setEmail(rs.getString("email"));
                board.setSubject(rs.getString("subject"));
                board.setContent(rs.getString("content"));
                board.setRdate(rs.getDate("rdate"));
                board.setUdate(rs.getDate("udate"));
                return board;
            }
        };
    }

    @Override
    public Board insert(Board board) {
        String sql = "insert into BOARD values(BOARD_SEQ.nextval, ?,?,?,?,SYSDATE,SYSDATE)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement pstmt = con.prepareStatement(sql, new String[]{"seq"});
            pstmt.setString(1, board.getWriter());
            pstmt.setString(2,board.getEmail());
            pstmt.setString(3,board.getSubject());
            pstmt.setString(4,board.getContent());
            return pstmt;
        }, keyHolder);
        long key = keyHolder.getKey().longValue();
        board.setSeq(key);
        return board;
    }

    @Override
    public Board contentList(long seq) {
        String sql = "select * from BOARD where SEQ=?";
        Object[] args ={seq};
        Board board = jdbcTemplate.queryForObject(sql, args, boardRowMapper());
        return board;
    }

    @Override
    public void update(Board board) {
        String sql =  "Update BOARD Set EMAIL = ?, SUBJECT = ?, CONTENT =? Where SEQ = ?";
        Object[] args ={board.getEmail(), board.getSubject(), board.getContent(), board.getSeq()};
        int count = jdbcTemplate.update(sql, args);
        if(count>0) {System.out.println(count+"행이 업데이트 되었습니다");}
        else{ System.out.println("업데이트 실패했습니다");}
    }

    @Override
    public boolean delete(long seq) {
        String sql = "delete from Board where SEQ=?";
        int count = jdbcTemplate.update(sql, seq);
        if(count>0) return true;
        else return false;
    }
}
