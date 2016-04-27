package sign.bean;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sign.bean.memberDto;

@Controller
public class SignInBean {
	@Autowired
	private SqlMapClientTemplate sqlMap;
	@Autowired
	private ModelAndView mv;

	@RequestMapping("/signIn.dj")
	public ModelAndView signIn(){
		

		mv.setViewName("/user/signIn.jsp");
		return mv;
	}
	
	@RequestMapping("/signPro.dj")
	public ModelAndView signPro(memberDto dto,HttpSession session){
		
		int check = (Integer)sqlMap.queryForObject("signcheck", dto);
		
		if(check == 1){
			session.setAttribute("memId", dto.getEmail());
			mv.setViewName("/user/signPro.jsp");
		}else{
			mv.setViewName("/user/signError.jsp");
		}
	
		return mv;
	}
	
	@RequestMapping("/logout.dj")
	public ModelAndView logout(HttpSession session){
		
		session.invalidate();
		
		mv.setViewName("/user/logout.jsp");
		return mv;
		
	}
}
