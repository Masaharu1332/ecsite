//Entityクラス（ユーザークラス）

package jp.co.internous.ecsite.model.domain;

public class MstUser {
	
	//mst_userテーブルの構造を確認してフィールドを作成する
	private int id;
	private String userName;
	private String password;
	private String fullName;
	private int isAdmin;
	
	
	//セッター・ゲッターを定義する
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
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
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public int getIsAdmin() {
		return isAdmin;	
    }
}
