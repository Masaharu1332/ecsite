package jp.co.internous.ecsite.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.ecsite.model.domain.MstGoods;

@Mapper
public interface MstGoodsMapper {
	
	//mst_goodsテーブルからすべてのレコードを取得するメソッド。
	@Select(value = "SELECT * FROM mst_goods")
	List<MstGoods> findAll();
	
	//mst_goodsテーブルに新しいレコードを挿入するメソッド。（goodsNameとpriceの値を指定して新しい商品を追加）
	@Insert("INSERT INTO mst_goods(goods_name, price) VALUES (#{goodsName}, #{price})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	int insert(MstGoods goods);
	
	//指定されたIDに一致するレコードをmst_goodsテーブルから削除するメソッド。
	@Update("DELETE FROM mst_goods WHERE id = #{id}")
	int deleteById(int id);
	
}
