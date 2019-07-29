package helpdesk;

import java.sql.*;

class MySQL {
    
    private Connection connection;
    private PreparedStatement query;
    private ResultSet data;
    
    public Connection connect(String host, String dbase, String user, String pass) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        return connection = DriverManager.getConnection("jdbc:mysql://"+host+"/"+dbase,user,pass);
    }
    
    public void query(String q) throws ClassNotFoundException, SQLException{
        query = connection.prepareStatement(q);
        data = query.executeQuery();   
    }
    
    public void query(String q, boolean update) throws SQLException{
        query = connection.prepareStatement(q);
        query.executeUpdate(q);
    }
    
    public int num_rows() throws SQLException{
        int x = 0;
        
        while(data.next()){
            x++;
        }
        
        return x;        
    }
    
    public ResultSet fetch_assoc() throws SQLException{
        return data;
    }
    
    public ResultSet result() throws SQLException{
        return query.getResultSet();
    }
    
    public void close() throws SQLException{
        connection.close();
        query.close();
    }
    
    public void close(ResultSet rs) throws SQLException{
        close();
        rs.close();
    }
}