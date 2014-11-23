package com.github.chimelli.emmi;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	public Iterable<Comment> findByPicture(Picture picture);
}
