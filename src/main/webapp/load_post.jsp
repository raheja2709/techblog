<%@page import="com.tech.blog.entities.Usermaster"%>
<%@page import="com.tech.blog.DAO.LikesDAO"%>
<%@page import="com.tech.blog.entities.Post"%>
<%@page import="java.util.List"%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import="com.tech.blog.DAO.PostDAO"%>
<style type="text/css">
.Liked {
	color: #212529;
    background-color: #f8f9fa;
    border-color: #f8f9fa;
}
</style>
<div class="row">
	<%
	Usermaster u = (Usermaster) session.getAttribute("currentUser");
	Thread.sleep(1000);
	PostDAO d = new PostDAO(ConnectionProvider.getConnection());

	int cid = Integer.parseInt(request.getParameter("cid"));
	List<Post> posts = null;
	if (cid == 0) {
		posts = d.getAllPosts();
	} else {
		posts = d.getPostbyCatId(cid);
	}
	if (posts.size() == 0) {
		out.println("<h3 class='display-3 text-center primary-background text-white'>No Posts in this category..</h3>");
		return;
	}
	for (Post p : posts) {
	%>

	<div class="col-md-6 mt-2">
		<div class="card">
			<div class="card">

				<img class="card-img-top" src="blog_pics/<%=p.getpPic()%>"
					alt="Card image cap">
				<div class="card-body">
					<b><%=p.getpTitle()%></b>
					<p><%=p.getpContent()%></p>
					<%-- 					<pre><%=p.getpCode()%></pre> --%>
				</div>
				<div class="card-footer bg-primary text-center">
					<%
					LikesDAO ld = new LikesDAO(ConnectionProvider.getConnection());
					%>

					<a id="like-button" href="#!"
						onclick="doLike(<%=p.getpId()%>, <%=u.getId()%>, this)"
						class="btn btn-outline-light btn-sm like-button <%= ld.isLikedByUser(p.getpId(),u.getId()) ? "Liked" : ""%>">
						<i class="fa fa-thumbs-o-up"></i> 
						<span class="like-counter"><%=ld.countLikeOnPost(p.getpId())%></span>
					</a> 
					<a href="show_blog.jsp?post_id=<%=p.getpId()%>" class="btn
							btn-outline-light btn-sm">Read More
					</a> <a href="#!" class="btn btn-outline-light btn-sm"> <i
						class="fa fa-commenting-o"></i><span> 20</span>
					</a>

				</div>
			</div>

		</div>
	</div>
	<%
	}
	%>
</div>

