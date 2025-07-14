package dao;
import bean.LoginBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBConnection;
/**
 *
 * @author Acer
 */
public class LoginDao {
    public LoginBean authenticateUser(LoginBean loginBean){
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();

        Connection con = null;
        ResultSet resultSet = null;
        
        try{
            con = DBConnection.createConnection();
            
            //Check in Customer Table
            String Sql = "SELECT * FROM CUSTOMER WHERE username = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(Sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                loginBean.setRole("customer");
                //
                loginBean.setID(rs.getInt("CUSTOMERID"));
                loginBean.setName(rs.getString("FULLNAME"));
                loginBean.setEmail(rs.getString("EMAIL"));
                loginBean.setUsername(rs.getString("USERNAME"));
                loginBean.setPassword(rs.getString("PASSWORD"));
                loginBean.setAddress(rs.getString("ADDRESS"));
                //
                return loginBean;
            }
            //Check in Admin Table
            Sql = "SELECT * FROM ADMIN WHERE username = ? AND password = ?";
            ps = con.prepareStatement(Sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while(rs.next()){
                loginBean.setRole("admin");
                
                loginBean.setID(rs.getInt("ADMINID"));               
                loginBean.setName(rs.getString("NAME"));
                loginBean.setUsername(rs.getString("USERNAME"));
                loginBean.setPassword(rs.getString("PASSWORD"));

                return loginBean;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

