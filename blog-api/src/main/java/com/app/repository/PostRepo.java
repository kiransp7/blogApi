package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Category;
import com.app.model.Post;
import com.app.model.User;

@Repository 
public interface PostRepo extends JpaRepository<Post, Long> {
	
	// Find All Users on Posts
	List<Post> findByUser(User user);
	
	// Find All Category on Posts
	List<Post> findByCategory(Category category);
	
	//Searching Post on title
	List<Post> findByTitleContaining(String title);  // in backend Like query fired for searching
	
	
	
	

}
