package hj.backend.repository;

import hj.backend.domain.Board;

import java.util.List;

public interface BoardRepository {
    List<Board> list();
    Board insert(Board board);
    Board contentList(long seq);
    void update(Board board);
    boolean delete(long seq);
}
