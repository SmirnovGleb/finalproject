package by.epam.roulette.pool;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import by.epam.roulette.converter.MD5Converter;
import by.epam.roulette.dao.BetDao;
import by.epam.roulette.dao.LockListDao;
import by.epam.roulette.dao.CasinoDao;
import by.epam.roulette.dao.CreditDao;
import by.epam.roulette.dao.MessageDao;
import by.epam.roulette.dao.UserTransaction;
import by.epam.roulette.dao.UserDao;
import by.epam.roulette.entity.User;
import by.epam.roulette.exception.DaoException;
import by.epam.roulette.service.ServiceAdmin;
import by.epam.roulette.validator.UserParametersValidator;




public class TestDB {
	
	   
	public static void main(String[] args) {
		
		/*
		UserDao d = new UserDao();
		//System.out.println(d.updatePassword(6, "morty"));
		System.out.println(UserParametersValidator.validatePassword("ssf"));
*/
		
		/*
		CasinoDao dao = new CasinoDao();
		try {
			System.out.println(dao.getPercent());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		MessageDao dao = new MessageDao();
		try {
			for(String g:dao.findAllMessages()){
				System.out.println(g);
			}
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*
		MessageDao dao = new MessageDao();
		try {
			System.out.println(dao.addMessage("TEST DAO MESSAGE", new UserDao().findUserById(5)));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		BlockListDao dao= new BlockListDao();
		try {
			System.out.println(dao.isBlocked(5));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		UserTransaction t = new UserTransaction();
		try {
			System.out.println(t.fromUserToCasino(2, new BigDecimal("2")));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		CreditDao dao = new CreditDao();
		try {
			int ii = 4;
			System.out.println(dao.isUserOverdueDebtor(ii));
			System.out.println(dao.isUserDebtor(ii));
			System.out.println(dao.findDebtAmount(ii));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		BetDao dao = new BetDao();
		try {
			System.out.println(dao.addBet(5, "1,7", new BigDecimal(50), "Win", new BigDecimal(50)));
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		HashMap<User, Timestamp> players = new ServiceAdmin().findBlockedPlayers();
		for(Map.Entry<User, Timestamp> entry:players.entrySet()){
			System.out.println(entry.getKey().toString() + "    " + entry.getValue());
		}
		
		ArrayList<User> users = ServiceAdmin.findPlayers();
		for(User u : users){
			System.out.println(u.toString());
		}
		*/
		/*
		
		ConnectionPool pool = ConnectionPool.getInstance();
		
		System.out.println(pool.pool.size());
		
		ArrayList<User> users = new ArrayList<>();
		
		ConnectionWrapper con = null;
		Statement st = null;
		try {
			con = ConnectionPool.getInstance().receiveConnection();
			System.out.println(ConnectionPool.getInstance().pool.size());
			st = con.createStatement();
			ResultSet resultSet = st.executeQuery("SELECT * FROM user");
			while(resultSet.next()){
				int id = resultSet.getInt("u_id");
				String name = resultSet.getString("u_name");
				String login = resultSet.getString("u_login");
				String password = resultSet.getString("u_password");
				BigDecimal money =  resultSet.getBigDecimal("u_money");
				String email = resultSet.getString("u_email");
				boolean isAdmin =  resultSet.getBoolean("u_is_admin");
				if(!isAdmin){
					users.add(new User(id, name, login, password, money, email, isAdmin));
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} 
		System.out.println(users);
		System.out.println(ConnectionPool.getInstance().pool.size());
		System.out.println(con.toString());
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(con.toString());
		System.out.println(ConnectionPool.getInstance().pool.size());
		
		*/
		String password = "testt";
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        byte[] textBytes = password.getBytes();
        md.reset();
        byte[] digested = md.digest(textBytes);
        StringBuffer sb1 = new StringBuffer();
        for(int i=0;i<digested.length;i++){
            sb1.append(Integer.toHexString(0xff & digested[i]));
        }
       
        //System.out.println("Digest(in hex format):: " + sb.toString());
        System.out.println("Digest(in hex format):: " + new MD5Converter().convert("testt"));
	    
	   
		
	}//end main
}
