//管理者サイト側
package jp.co.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.ecsite.model.domain.MstGoods;
import jp.co.internous.ecsite.model.domain.MstUser;
import jp.co.internous.ecsite.model.form.GoodsForm;
import jp.co.internous.ecsite.model.form.LoginForm;
import jp.co.internous.ecsite.model.mapper.MstGoodsMapper;
import jp.co.internous.ecsite.model.mapper.MstUserMapper;

@Controller
@RequestMapping("/ecsite/admin")
public class AdminController {
	
	//userMapperインターフェイスをインスタンス化
	@Autowired
	private MstUserMapper userMapper; 
	
	//goodsMapperインターフェイスをインスタンス化
	@Autowired
	private MstGoodsMapper goodsMapper;
	
	@RequestMapping("/")
	public String index() {
		return "admintop";
	}
	@PostMapping("/welcome")
	public String welcome(LoginForm form, Model model) {
		
		MstUser user = userMapper.findByUserNameAndPassword(form);
		
		//検索結果を判定
		if(user == null) {
			model.addAttribute("errMessage", "ユーザ名またはパスワードが違います。");
			return "forward:/ecsite/admin/"; //トップページに戻るように誘導される
		}
		
		//ログインした人が管理者かを判定
		if(user.getIsAdmin() == 0) {
			model.addAttribute("errMessage", "管理者ではありません。");
			return "forward:/ecsite/admin/"; //トップページに戻るように誘導される
		}
		
		List<MstGoods> goods = goodsMapper.findAll();
		model.addAttribute("userName" , user.getUserName()); //keyとVALUE
		model.addAttribute("password" , user.getPassword());
		model.addAttribute("goods" , goods);
		
		return "welcome";
	}
	
	//新規商品の登録機能
	@PostMapping("/goodsMst")
	public String goodsMst(LoginForm f, Model m) {
		m.addAttribute("userName", f.getUserName());
		m.addAttribute("password", f.getPassword());
		
		return "goodsmst";
	}
	
	//新規商品情報をDBに登録する機能
	@PostMapping("/addGoods")
	public String addGoods(GoodsForm goodsForm, LoginForm loginform, Model m) {
		m.addAttribute("userName", loginform.getUserName());
		m.addAttribute("password", loginform.getPassword());
		
		//MstGoodsクラスをインスタンス化
		MstGoods goods = new MstGoods();
		//goodsFormから商品名、価格を取得し、新しい商品オブジェクトに設定
		goods.setGoodsName(goodsForm.getGoodsName()); 
		goods.setPrice(goodsForm.getPrice());
		
		goodsMapper.insert(goods);
		
		return "forward:/ecsite/admin/welcome";
		
	}
	
	//商品マスタから商品を削除する機能
	@ResponseBody 
	@PostMapping("/api/deleteGoods")
	public String deleteApi(@RequestBody GoodsForm f) {
		try {
			goodsMapper.deleteById(f.getId());
		}catch(IllegalArgumentException e) {
			return "-1";
		}
		
		return "1";
	}
}
