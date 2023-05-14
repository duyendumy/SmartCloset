package com.group.smartcloset.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.group.smartcloset.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {

	@Query("SELECT u FROM User u WHERE u.email =:email AND u.password =:password")
	User getUser(@Param("email") String email, @Param("password") String password);
	
	@Query("SELECT u FROM User u WHERE u.id =:id")
	public User getUserById(@Param("id") Long id);
	
	@Query("SELECT u FROM User u WHERE u.email =:email")
	public User getUserByEmail(@Param("email") String email);
}
