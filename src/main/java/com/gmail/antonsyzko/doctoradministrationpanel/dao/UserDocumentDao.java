package com.gmail.antonsyzko.doctoradministrationpanel.dao;

import com.gmail.antonsyzko.doctoradministrationpanel.model.UserDocument;

import java.util.List;

public interface UserDocumentDao {

	List<UserDocument> findAll();
	
	UserDocument findById(int id);
	
	void save(UserDocument document);
	
	List<UserDocument> findAllByUserId(int userId);
	
	void deleteById(int id);
}
