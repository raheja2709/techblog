function doLike(pid, uid, element) {
	const d = {
		uid: uid,
		pid: pid,
		operation: 'like',
	}
	if($(element).hasClass("Liked")) {
		console.log("Liked Post")
		$(element).removeClass("Liked");
		d.operation = "removeLike";
		console.log("Operation value = "+d.operation)
		let c = $(element).find('.like-counter').html();
		c = parseInt(c) - 1;
		console.log("value of c = "+c)
		$(element).find('.like-counter').html(c);
	} else {
		$(element).addClass("Liked");
		let c = $(element).find('.like-counter').html();
		c = parseInt(c) + 1;
		$(element).find('.like-counter').html(c);
	}
	$.ajax({
		url: "LikeServlet",
		data: d,

		success: function(data) {
			if (data.trim() === 'success') {
//				$(element).addClass("Liked");
				console.log("Like data  = " + data);
			} else {
//				$(element).removeClass("Liked");
				console.log('Error: Failed to save the like.');
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log('Error: ' + textStatus);
//			$(element).removeClass("Liked");
		}
	})
}

//function dislike(pid, uid, element) {
//	const d = {
//		uid: uid,
//		pid: pid,
//		operation: 'liked'
//	}
//	$.ajax({
//		url: "DisLikeServlet",
//		data: d,
//		success: function(data) {
//			if (data.trim() == 'success') {
//				let c = $(element).find('.dislike-counter').html();
//				c = parseInt(c) - 1;
//				$(element).find('.dislike-counter').html(c);
//			} else {
//				console.log('Error: Failed to DisLike the Post.');
//			}
//		},
//		error: function(jqXHR, textStatus, errorThrown) {
//			console.log('Error: ' + textStatus);
//		}
//	})
//}