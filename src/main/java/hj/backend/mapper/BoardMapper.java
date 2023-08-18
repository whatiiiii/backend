package hj.backend.mapper;

import hj.backend.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
//@Repository
public interface BoardMapper {
    List<Board> list();
    Board selectBySeq(long seq);
    void insertSelectKey(Board board);

    void update(Board board);

    void delete(long seq);

}
