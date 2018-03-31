package com.hbf.hy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
//@RestController	返回json数据
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@RequestMapping(value="list.html")
	public String list(Model model){
		List<Member> list = memberService.sys();
		model.addAttribute("say", "spring boot");
		System.out.println(list.size());
		
		return "hy/index";
	}

}
