package jp.co.internous.ecsite.model.form;

import java.io.Serializable;

public class LoginForm implements Serializable{
	
	//ログイン時にユーザーの情報を受け取るためのフィールドを作成
	private String userName;
	private String password;
	
	//ログイン情報のセッター・ゲッターの作成
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	
	
	
}
