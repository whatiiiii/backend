package hj.backend.service;

import hj.backend.domain.Board;
import hj.backend.mapper.BoardMapper;

import java.util.List;
//@Service
public class MybatisBoardService implements BoardService {

    private final BoardMapper mapper;
    //  @Autowired
    public MybatisBoardService(BoardMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Board> listS() {
        return mapper.list();
    }

    @Override
    public Board insertS(Board board) {
        pln("@insertS() by mapper");
        mapper.insertSelectKey(board);
        long seq = board.getSeq();
        pln("@insertS() board.getSeq(): "+seq);
        board = mapper.selectBySeq(seq);
        pln("@insertS() board: "+board);
        return board;
    }

    @Override
    public Board contentListS(long seq) {
        pln("@contentListS() by mapper");
        return mapper.selectBySeq(seq);
    }

    @Override
    public void updateS(Board board) {
        pln("@updateS() by mapper");
        mapper.update(board);
    }








































































































































































































































    @Override
    public boolean deleteS(long seq) {
        pln("@deleteS() by mapper");
        mapper.delete(seq);
        return false;
    }

    void pln(String str){
        System.out.println(str);
    }
}
