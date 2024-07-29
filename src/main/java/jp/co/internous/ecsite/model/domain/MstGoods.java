//Entityクラス（クラス）

package jp.co.internous.ecsite.model.domain;

//Timestamp型を使えるようにするインポート文
import java.sql.Timestamp;

public class MstGoods {
	
	//mst_goodsテーブルの構造を確認してフィールドを作成する
	private int id;
	private String goodsName;
	private int price;
	private Timestamp createdAt;
	private Timestamp updatedAt;
	
	//セッター・ゲッターを定義する
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getGoodsName() {
		return goodsName;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
}
