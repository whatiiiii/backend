package hj.backend.service;

import hj.backend.domain.Board;
import hj.backend.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
public class JdbcOracleBoardService implements BoardService{
    private final BoardRepository repository;
  //  @Autowired
    public JdbcOracleBoardService(BoardRepository repository){
        this.repository = repository;
    }
    @Override
    public List<Board> listS() {
        return repository.list();
    }

    @Override
    public Board insertS(Board board) {
        return repository.insert(board);
    }

    @Override
    public Board contentListS(long seq) {
        return repository.contentList(seq);
    }

    @Override
    public void updateS(Board board) {
        repository.update(board);
    }

    @Override
    public boolean deleteS(long seq) {
        return repository.delete(seq);
    }
}
