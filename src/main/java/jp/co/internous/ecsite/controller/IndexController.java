package jp.co.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.ecsite.model.domain.MstGoods;
import jp.co.internous.ecsite.model.domain.MstUser;
import jp.co.internous.ecsite.model.dto.HistoryDto;
import jp.co.internous.ecsite.model.form.CartForm;
import jp.co.internous.ecsite.model.form.HistoryForm;
import jp.co.internous.ecsite.model.form.LoginForm;
import jp.co.internous.ecsite.model.mapper.MstGoodsMapper;
import jp.co.internous.ecsite.model.mapper.MstUserMapper;
import jp.co.internous.ecsite.model.mapper.TblPurchaseMapper;

@Controller
@RequestMapping("/ecsite")
public class IndexController {
	@Autowired
	private MstGoodsMapper goodsMapper; 	//MstGoodsMapperを自動でインスタンス化
	
	@Autowired
	private MstUserMapper userMapper; //MstUserMapperを自動でインスタンス化
	
	@Autowired
	private TblPurchaseMapper purchaseMapper;//TblPurchaseMapperを自動でインスタンス化
	
	private Gson gson = new Gson(); //Gsonをインスタンス化（WebサービスAPIとして作成するため）
	
	@GetMapping("/")
	public String index(Model model) { 
		List<MstGoods> goods = goodsMapper.findAll(); //mst_goodsのデータ情報をgoodsに代入
		model.addAttribute("goods", goods); //HTMLの＄{goods}にgoodsを代入
		
		return "index"; //index.htmlを戻り値
	}
	@ResponseBody //文字列そのものを返却するアノテーション
	@PostMapping("/api/login")
	public String  loginApi(@RequestBody LoginForm f) {
		MstUser user = userMapper.findByUserNameAndPassword(f); //mst_userの該当するデータ情報をuserに代入
		
		if(user == null) { //データベースに該当するデータがなかった時
			user = new MstUser(); //MstUserをインスタンス化して
			user.setFullName("ゲスト"); //フルネームをゲストに設定
		}
		
		return gson.toJson(user); //MstUser型のUserをJson形式の文字列に変換し、Viewに返却
	}

@ResponseBody
@PostMapping("/api/purchase")
public int purchaseApi(@RequestBody CartForm f) {
	
	f.getCartList().forEach((c) ->{  
		int total = c.getPrice() * c.getCount(); //各カートアイテムの合計金額を計算
		purchaseMapper.insert(f.getUserId(), c.getId(), c.getGoodsName(), c.getCount(), total); //DBに購入情報を挿入。
	});
	
	return f.getCartList().size();
	}
@ResponseBody
@PostMapping("/api/history")
public String historyApi(@RequestBody HistoryForm f) {
	int userId = f.getUserId();
	List<HistoryDto> history = purchaseMapper.findHistory(userId);
	
	return gson.toJson(history);
}



}



