package hj.backend.service;

import hj.backend.domain.Board;
import hj.backend.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//@Service
@Transactional
public class JpaBoardService implements BoardService {
  //  @Autowired
    private final BoardRepository repository;
  //  @Autowired
    public JpaBoardService(BoardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Board> listS() {
        pln("@list@() by jpa");
        return repository.list();
    }

    @Override
    public Board insertS(Board board) {
        pln("@insertS() by jpa: (Before) board: "+board);
        board = repository.insert(board);
        pln("@insertS() by jpa: (After) board: "+board);
        pln("@insertS() by jpa: seq: "+board.getSeq());
        pln("@insertS() by jpa: rdate: "+board.getRdate());
        return board;
    }

    @Override
    public Board contentListS(long seq) {
        Board board = repository.contentList(seq);
        return board;
    }

    @Override
    public void updateS(Board board) {
        pln("@updateS() by jpa: ");
        repository.update(board);
    }

    @Override
    public boolean deleteS(long seq) {
        pln("@deleteS() by jpa: ");
        return repository.delete(seq);
    }

    void pln(String str){
        System.out.println(str);
    }
}
