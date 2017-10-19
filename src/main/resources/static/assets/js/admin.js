function deletePost(postId)
{
	var confirmed = confirm('Are you sure to delete?');
	if(confirmed)
	{
		$.ajax({
		    url:   "/admin/posts/"+postId+"/delete",
		    method: 'delete',
		    success: function(){
		    	window.location = 'posts';
		    }
		});
	}
}
