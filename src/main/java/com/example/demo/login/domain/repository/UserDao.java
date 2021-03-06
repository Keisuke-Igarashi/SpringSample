package com.example.demo.login.domain.repository;

import java.util.List;
import org.springframework.dao.DataAccessException;
import com.example.demo.login.domain.model.User;

public interface UserDao {

	
	//DataAccessException
	public int count() throws DataAccessException;
	
	//Userテーブルにデータを１件insert.
	public int insertOne(User user) throws DataAccessException;
	
	//Userテーブルのデータを１件取得
	public User selectOne(String UserId) throws DataAccessException;
	
	//Userテーブルの全データを取得
	public List<User> selectMany() throws DataAccessException;
	
	//Userテーブルを1件更新
	public int updateOne(User user) throws DataAccessException;
	
	//Userテーブルを1件削除
	public int deleteOne(String userId) throws DataAccessException;
	
	//SQL取得結果をサーバーにCSVで保存
	public void userCsvOut() throws DataAccessException;
	
	
}
