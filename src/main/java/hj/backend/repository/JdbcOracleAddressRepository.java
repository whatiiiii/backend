package hj.backend.repository;

import hj.backend.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//@Repository
public class JdbcOracleAddressRepository implements AddressRepository{
    //@Autowired
    private final DataSource dataSource;
    //@Autowired
    public JdbcOracleAddressRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    Connection getConnection(){
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection con) throws SQLException{
        DataSourceUtils.releaseConnection(con, dataSource);
    }

    @Override
    public List<Address> list() {
        ArrayList<Address> list = new ArrayList<>();
        String sql = "select * from ADDRESS order by Name";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            stmt = con.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next()){
                long seq = rs.getLong(1);
                String name = rs.getString(2);
                String addr = rs.getString(3);
                Date rdate = rs.getDate(4);
                list.add(new Address(seq, name, addr, rdate));
            }
            return list;
        }catch(SQLException se){
            return null;
        }finally{
            close(con, stmt, rs);
        }

    }

    private void close(Connection con, Statement stmt, ResultSet rs){
            try{
                if(rs != null) rs.close();
                if(con != null) con.close();
                if(stmt != null) stmt.close();
            }catch(SQLException se){
        }
    }

    @Override
    public Address insert(Address address) {
        String sql = "insert into ADDRESS values(ADDRESS_SEQ.nextval, ?,?, SYSDATE)";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //insert문에만 가능?
            pstmt.setString(1, address.getName());
            pstmt.setString(2, address.getAddr());
            pstmt.executeUpdate();

            rs = pstmt.getGeneratedKeys();
            if(rs.next()){
              //  address.setSeq(rs.getLong("SEQ"));
                String rowid = rs.getString(1);
                Address addressDb = getAddress(rowid);
                return addressDb;
            }else {
                return null;
            }
        }catch(SQLException se){
                return null;
        }finally {
                close(con, pstmt, rs);
        }
    }

    private Address getAddress(String rowid){
        String sql ="select * from ADDRESS where ROWID=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, rowid);
            rs= pstmt.executeQuery();
            if(rs.next()){
                long seq = rs.getLong(1);
                String name = rs.getString(2);
                String addr = rs.getString(3);
                Date rdate = rs.getDate(4);
                return new Address(seq, name, addr, rdate);
            }else{
                return null;
            }
        }catch(SQLException se){
            return null;
        }finally {
            close(con, pstmt, rs);
        }
    }


    @Override
    public boolean delete(long seq) {
        String sql = "delete from ADDRESS where SEQ=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setLong(1,seq);
            int i = pstmt.executeUpdate();
            if(i>0) return true;
            else return false;
        }catch(SQLException se){
            return false;
        }finally {
            close(con, pstmt, rs);
        }
    }


}
