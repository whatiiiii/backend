package hj.backend.repository;

import hj.backend.domain.Address;
import hj.backend.domain.Board;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
//@Repository
public class JpaOracleBoardRepository implements BoardRepository {
    //@Autowired
    private final EntityManager em;

    public JpaOracleBoardRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Board> list() {
        List<Board > list = em.createQuery("select a from Board a order by a.seq desc", Board.class).getResultList();
        return list;
    }

    @Override
    public Board insert(Board board) {
        em.persist(board);
        return board;
    }

    @Override
    public Board contentList(long seq) {
        Board board = em.find(Board.class,seq);
        return board;
    }

   // public List<Address> findByName(String name){
    //    List<Address> list = em.createQuery("select a.writer, a.email, a.subject, a.content from Board a where a.writer=:writer, a.email=:email, a.subject=:subject, a.content=:content", Board.class).setParameter("writer", name).getResultList();
    //    return list;
    //}

    @Override
    public void update(Board board) {
      //      em.createQuery("UPDATE Board b SET b.email = :email, b.subject = :subject, b.content = :content WHERE b.seq = :seq")
      //              .setParameter("email", board.getEmail())
      //              .setParameter("subject", board.getSubject())
      //              .setParameter("content", board.getContent())
      //              .setParameter("seq", board.getSeq())
      //              .executeUpdate();
     //   em.merge(board);

        Board board1 = em.find(Board.class, board.getSeq());
        board1.setEmail(board.getEmail());
        board1.setSubject(board.getSubject());
        board1.setContent(board.getContent());

    }

    @Override
    public boolean delete(long seq) {
        Board board = contentList(seq);
        em.remove(board);
        return true;
    }
}
