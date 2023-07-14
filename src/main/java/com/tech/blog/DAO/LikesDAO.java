package com.tech.blog.DAO;

import java.sql.*;

public class LikesDAO {

	Connection con;

	public LikesDAO(Connection con) {
		super();
		this.con = con;
	}

	public boolean insertLike(int uid, int pid) {
		boolean f = false;
		try {
			String q = "insert into likes(pid,uid)values(?,?)";
			PreparedStatement p = this.con.prepareStatement(q);
			p.setInt(1, pid);
			p.setInt(2, uid);
			p.executeUpdate();
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public int countLikeOnPost(int pid) {
		int count = 0;
		String q = "select count(*) as count from likes where pid=?";
		try {
			PreparedStatement p = this.con.prepareStatement(q);
			p.setInt(1, pid);
			ResultSet set = p.executeQuery();
			if (set.next()) {
				count = set.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public boolean isLikedByUser(int pid, int uid) {
		boolean f = false;
		String q = "select * from likes where pid=? and uid=? ";
		try {
			PreparedStatement p = this.con.prepareStatement(q);
			p.setInt(1, pid);
			p.setInt(2, uid);
			ResultSet set = p.executeQuery();
			if (set.next()) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	public boolean deleteLike(int pid, int uid) {
		boolean f = false;
		try {
			PreparedStatement p = this.con.prepareStatement("DELETE from likes where pid=? and uid=?");
			p.setInt(1, pid);
			p.setInt(2, uid);
			int ret = p.executeUpdate();
			System.out.println("deleted count:" + ret);
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Deleting like:" + f);
		return f;
	}
}
