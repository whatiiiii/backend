package hj.backend.service;

import hj.backend.domain.Board;
import hj.backend.repository.SpringDataJpaOracleBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardAjaxServiceImpl implements BoardAjaxService {

    @Autowired
    private final SpringDataJpaOracleBoardRepository repository;
    @Autowired
    public BoardAjaxServiceImpl(SpringDataJpaOracleBoardRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Board> listS() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "seq"));
    }

    @Override
    public Board insertS(Board board) {
        board = repository.save(board);
        return board;
    }

    @Override
    public Board contentListS(long seq) {
        Board board = repository.getReferenceById(seq);
        return board;
    }

    @Override
    public void updateS(Board board) {
        Board board1 = repository.getReferenceById(board.getSeq());
        board1.setSeq(board.getSeq());
        board1.setEmail(board.getEmail());
        board1.setSubject(board.getSubject());
        board1.setContent(board.getContent());
        //board1.setRdate(board.getRdate());
        repository.save(board1);
    }

    @Override
    public boolean deleteS(long seq) {
        repository.deleteById(seq);
        return false;
    }

    @Override
    public Board getBySeqS(long seq) {
        Board board = repository.findById(seq).get();
        return board;
    }

}
