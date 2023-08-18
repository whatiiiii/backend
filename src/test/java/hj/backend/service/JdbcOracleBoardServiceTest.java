package hj.backend.service;

import hj.backend.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class JdbcOracleBoardServiceTest {
    @Autowired
    BoardService service;
    @Test
    void listS() {
        List<Board> list = service.listS();
        pln("@list: "+list);
    }

    @Test
    void insertS() {
        Board board = new Board(-1L, "수경", "ss@naver.com","제목1","제목2", null, null);
        Board boardDb = service.insertS(board);
        pln("@insert: "+boardDb);
    }

    @Test
    void contentListS() {
        Board board = service.contentListS(17);
        pln("@contentList: "+board);
    }

    @Test
    void updateS() {
        //Board board = new Board(17, null,"1234@naver.com","ㅇㅇ","ㅇㅇ",null,null);
        //service.updateS(board);
    }

    @Test
    void deleteS() {
        boolean flag = service.deleteS(2);
        pln("@flag: "+flag);
    }

    void pln(String str){
        System.out.println(str);
    }
}