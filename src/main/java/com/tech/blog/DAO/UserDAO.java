package com.tech.blog.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tech.blog.entities.Usermaster;
import com.tech.blog.helper.ConnectionProvider;

public class UserDAO {

	private Connection con = ConnectionProvider.getConnection();

	public UserDAO(Connection con) {

	}

	public boolean saveUser(Usermaster user) {

		boolean f = false;

		try {

			// user-->database
			String query = "insert into usermaster(name,email,password,gender,about) values(?,?,?,?,?)";
			PreparedStatement pstmt = this.con.prepareStatement(query);
			System.out.println("PSTMT : " + pstmt);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getGender());
			pstmt.setString(5, user.getAbout());

			pstmt.executeUpdate();
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	// get User by useremail and userpassword
	public Usermaster getUserByEmailAndPassword(String email, String password) {
		Usermaster user = null;
		try {

			String query = "select * from usermaster where email=? and password=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, password);

			ResultSet set = pstmt.executeQuery();

			if (set.next()) {

				// fetch data from db
				// String name = set.getString("name");
				user = new Usermaster();
				user.setId(set.getInt("id"));

				user.setName(set.getString("name"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setGender(set.getString("gender"));
				user.setAbout(set.getString("about"));
				user.setRdate(set.getTimestamp("rdate"));
				user.setProfile(set.getString("profile"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public boolean updateUser(Usermaster user) {
		boolean f = false;
		try {
			String query = "UPDATE usermaster SET name=?, email=?, password=?, gender=?, about=?,  profile=? WHERE id=?";
			PreparedStatement p = con.prepareStatement(query);
			p.setString(1, user.getName());
			p.setString(2, user.getEmail());
			p.setString(3, user.getPassword());
			p.setString(4, user.getGender());
			p.setString(5, user.getAbout());
			p.setString(6, user.getProfile());
			p.setInt(7, user.getId());
			p.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
