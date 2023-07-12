package com.tech.blog.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tech.blog.entities.Category;
import com.tech.blog.entities.Post;

public class PostDAO {

	Connection con;

	public PostDAO(Connection con) {
		super();
		this.con = con;
	}

	public ArrayList<Category> getAllCategories() {
		ArrayList<Category> list = new ArrayList<>();
		try {
			String q = "select * from categories";
			Statement st = this.con.createStatement();
			ResultSet set = st.executeQuery(q);
			while (set.next()) {
				int cid = set.getInt("cid");
				String name = set.getString("name");
				String description = set.getString("description");
				Category c = new Category(cid, name, description);
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean savePost(Post p) {
		boolean f = false;
		try {
			String q = "insert into posts(title,pcontent,pcode,ppic,catid, userid) values(?,?,?,?,?,?)";

			PreparedStatement pstmt = con.prepareStatement(q);
			pstmt.setString(1, p.getpTitle());
			pstmt.setString(2, p.getpContent());
			pstmt.setString(3, p.getpCode());
			pstmt.setString(4, p.getpPic());
			pstmt.setInt(5, p.getCatId());
			pstmt.setInt(6, p.getUserId());

			pstmt.executeUpdate();
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}

	// get all posts
	public List<Post> getAllPosts() {
		List<Post> list = new ArrayList<>();
		// fetch all the posts
		try {
			PreparedStatement p = con.prepareStatement("select * from posts order by pid desc");
			ResultSet set = p.executeQuery();
			while (set.next()) {
				int pid = set.getInt("pid");
				String ptitle = set.getString("title");
				String pcontent = set.getString("pcontent");
				String pcode = set.getString("pcode");
				String ppic = set.getString("ppic");
				Timestamp date = set.getTimestamp("pdate");
				int catId = set.getInt("catid");
				int userId = set.getInt("userid");
				Post post = new Post(pid, ptitle, pcontent, pcode, ppic, date, catId, userId);
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Post> getPostbyCatId(int catid) {
		List<Post> list = new ArrayList<>();
		// fetch all the posts by Id
		try {
			PreparedStatement p = con.prepareStatement("select * from posts where catid = ?");
			p.setInt(1, catid);
			ResultSet set = p.executeQuery();
			while (set.next()) {
				int pid = set.getInt("pid");
				String ptitle = set.getString("title");
				String pcontent = set.getString("pcontent");
				String pcode = set.getString("pcode");
				String ppic = set.getString("ppic");
				Timestamp date = set.getTimestamp("pdate");
				int catId = set.getInt("catid");
				int userId = set.getInt("userid");
				Post post = new Post(pid, ptitle, pcontent, pcode, ppic, date, catId, userId);
				list.add(post);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Post getPostByPostId(int postId) throws SQLException {
		Post post = null;
		try {
			String q = "select * from posts where pid = ?";
			PreparedStatement p = this.con.prepareStatement(q);
			p.setInt(1, postId);
			ResultSet set = p.executeQuery();
			if (set.next()) {
				int pid = set.getInt("pid");
				String ptitle = set.getString("title");
				String pcontent = set.getString("pcontent");
				String pcode = set.getString("pcode");
				String ppic = set.getString("ppic");
				Timestamp date = set.getTimestamp("pdate");
				int catId = set.getInt("catid");
				int userId = set.getInt("userid");
				post = new Post(pid, ptitle, pcontent, pcode, ppic, date, catId, userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return post;

	}
}
