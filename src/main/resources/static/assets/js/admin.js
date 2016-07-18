function deletePost(postId)
{
	//alert('delete post :'+postId);
	var confirmed = confirm('Are you sure to delete?');
	if(confirmed)
	{
		//alert('delete post :'+postId);
		$.ajax({
		    url:   "/admin/posts/"+postId+"/delete",
		    //data:  {'foo':'bar'},
		   // async: false,
		    method: 'delete',
		    success: function(){
		    	window.location = 'posts';
		    }
		});
	}
	else {
		//alert('Cancel');
	}
	
}