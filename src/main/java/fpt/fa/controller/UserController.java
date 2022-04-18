/*
 * (C) Copyright 2022 Fresher Academy. All Rights Reserved
 *
 * @author NghiaHX
 * @birthDate 25/05/2000
 * @date 2022-04-03
 * version 1.0
 */
/**
 * 
 */
package fpt.fa.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fpt.fa.dao.QuizDAO;
import fpt.fa.dao.UserDao;
import fpt.fa.entities.Account;
import fpt.fa.entities.Question;
import fpt.fa.entities.Quiz;
import fpt.fa.entities.QuizUser;

/**
 * @author Admin
 *
 */
@Controller
public class UserController {
	@RequestMapping(value= {"/"}, method=RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public ModelAndView trangchu(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
		model.addAttribute("title", "Trang chủ");	
		model.addAttribute("titleQuiz","Quiz gợi ý");
		model.addAttribute("catelogy", "none");
		QuizDAO quizDAO = new QuizDAO();
		List<Quiz> listQuizs = new ArrayList<Quiz>();
		if(session.getAttribute("user")!=null) {
				Account user = (Account) session.getAttribute("user");
				
				try {
					listQuizs = quizDAO.getQuizHint(user.getAge());
					model.addAttribute("listQuizs",listQuizs);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
	 			
	 		}else {
	 			 
				try {
					listQuizs = quizDAO.getQuizHint(4);
					model.addAttribute("listQuizs",listQuizs);
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	 		}
	 		
	    	return new ModelAndView("user/index");
       
    }
	@RequestMapping(value= {"/login"}, method=RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public ModelAndView login(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
				model.addAttribute("title", "Đăng nhập");
	 		if(session.getAttribute("user")!=null) {
	 			return new ModelAndView("redirect:/");
	 		}
	    	return new ModelAndView("login/login");
       
    }
	@RequestMapping(value= {"/login"}, method=RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public ModelAndView loginCheck(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
	 		
			
	 		try {
	 			String username= request.getParameter("username");
				String password= request.getParameter("password");
		 		UserDao userDao = new UserDao();
				Account user = userDao.getAcc(username, password);
				if(user!=null) {
					session.setAttribute("user", user);
					if(user.getRole()>0) {
						return new ModelAndView("redirect:/");
					}
					else {
						return new ModelAndView("redirect:/admin");
					}
				}else {
					model.addAttribute("message", "Tài khoản hoặc mật khẩu sai");
					return new ModelAndView("login/login");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return new ModelAndView("login/login");
       
    }
	@RequestMapping(value= {"/logout"}, method=RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public ModelAndView logout(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
	 	
	 		if(session.getAttribute("user")!=null) {
	 			session.removeAttribute("user");
	 			
	 		}
	 		return new ModelAndView("redirect:/");
       
    }
	@RequestMapping(value= {"/register"}, method=RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public ModelAndView registerDisplay(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
		model.addAttribute("title", "Đăng ký");
		if(session.getAttribute("user")!=null) {
 			return new ModelAndView("redirect:/");
 			
 		}
 		
	    	return new ModelAndView("login/register");
       
    }
	@RequestMapping(value= {"/register"}, method=RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public ModelAndView register(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
	 	
	 		
	 		
			try {
				model.addAttribute("title", "Đăng ký");
				String username= request.getParameter("username");
				String password= request.getParameter("password");
				String repassword= request.getParameter("repassword");
				String name= request.getParameter("name");
				int age= Integer.parseInt(request.getParameter("age"));
				UserDao userDao = new UserDao();
				
				if(userDao.findAcc(username)) {
					model.addAttribute("message","Username đã tồn tại");
				}
				else if(!password.equals(repassword)) {
					model.addAttribute("message","Password không khớp");
				}else {
					userDao.insertAcc(username, password,name,age);
					return new ModelAndView("redirect:/");
				}
		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		return new ModelAndView("login/register");
       
    }
	
	@RequestMapping(value= {"/list"}, method=RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public ModelAndView toan1(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
	 		
	 		try {
	 			QuizDAO quizDAO = new QuizDAO();
		 		String catelogy = (String) request.getParameter("catelogy");		 		
				List<Quiz> listQuizs = quizDAO.getQuizByCatelogy(catelogy);
				model.addAttribute("listQuizs", listQuizs);
				
				String titleQuiz = quizDAO.getTitleByCatelogy(catelogy);
				model.addAttribute("titleQuiz",titleQuiz);
				if(catelogy.equals("quizbaby")) {
					model.addAttribute("title", "Quiz Baby");
				}else if(catelogy.equals("quizenglish")) {
					model.addAttribute("title", "Quiz English");
				}else {
					model.addAttribute("title", "Quiz Toán");
				}
				
				model.addAttribute("catelogy", catelogy);
				return new ModelAndView("user/index");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	 		return null;
	 		
	    	
       
    }
	@RequestMapping(value= {"/quiz"}, method=RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public ModelAndView quizDes(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
		if(session.getAttribute("user")==null) {
 			return new ModelAndView("redirect:/");
 		}
	 		try {
	 			QuizDAO quizDAO = new QuizDAO();
		 		int id = Integer.parseInt(request.getParameter("quizID"));
		 		Quiz quiz = quizDAO.getQuizById(id);
		 		model.addAttribute("quiz", quiz);
		 		model.addAttribute("title", "Start");
				return new ModelAndView("user/startConfirm");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	 		return null;
	 		
	    	
       
    }
	@RequestMapping(value= {"/play"}, method=RequestMethod.GET,produces = "text/plain;charset=UTF-8")
    public ModelAndView playQuiz(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
		if(session.getAttribute("user")==null) {
 			return new ModelAndView("redirect:/");
 		}
	 		try {
	 			QuizDAO quizDAO = new QuizDAO();
		 		int id = Integer.parseInt(request.getParameter("quizID"));
		 		model.addAttribute("quizID", id);
				List<Question> listQuestions = quizDAO.getQuestionByQuizID(id);
				model.addAttribute("listQuestions", listQuestions);
				model.addAttribute("title", "Quiz - App");
				return new ModelAndView("user/play");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	 		return null;
	 		
	    	
       
    }
	
	@RequestMapping(value= {"/hoanthanh"}, method=RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public ModelAndView hoanthanh(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
		if(session.getAttribute("user")==null) {
 			return new ModelAndView("redirect:/");
 		}
	 		try {
	 			QuizDAO quizDAO = new QuizDAO();
		 		int quizID = Integer.parseInt(request.getParameter("quizID"));
		 		int score = Integer.parseInt(request.getParameter("resultQuiz"));
				Account user = (Account) session.getAttribute("user");
				quizDAO.insertResult(user.getUsername(), quizID, score);
		 		
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	 		return new ModelAndView("redirect:/");
    }
	
	@RequestMapping(value= {"/search"}, method=RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public ModelAndView timkiem(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
	 		
	 		try {
	 			QuizDAO quizDAO = new QuizDAO();
	 			String key = (String) request.getParameter("keySearch");
				List<Quiz> listQuizs = quizDAO.getQuizbyName(key);
				model.addAttribute("listQuizs", listQuizs);
				model.addAttribute("title", "Kết quả tìm kiếm");
				model.addAttribute("catelogy", "none");
				model.addAttribute("titleQuiz", "Tìm kiếm");
				return new ModelAndView("user/index");
		 		
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	 		return new ModelAndView("redirect:/");
    }
	@RequestMapping(value= {"/searchOn"}, method=RequestMethod.POST,produces = "text/plain;charset=UTF-8")
    public void search(Model model,HttpSession session,HttpServletRequest  request,HttpServletResponse response) {
	 		
	 		try {
	 			QuizDAO quizDAO = new QuizDAO();

	 			String key = (String) request.getParameter("keySearch");
				List<Quiz> listQuizs = quizDAO.getQuizbyName(key);
				PrintWriter out = response.getWriter();
				for(Quiz quiz:listQuizs) {
					out.println("  <div class=\"col-xs-12 col-sm-6 col-md-3 col-lg-3\">");
					if(session.getAttribute("user")!=null) {
						out.println(" <a href=\"/quiz?quizID=${quiz.quizID }\">");
					}else {
						out.println(" <a href=\"/login\">");
					}
					out.println(" <div class=\"card-flyer\">\r\n" + 
							"                        <div class=\"text-box\">\r\n" + 
							"                            <div class=\"image-box\">\r\n" + 
							"                                <img src=\""+quiz.getImage()+"\" alt=\"\" />\r\n" + 
							"                            </div>\r\n" + 
							"                            <div class=\"text-container\">\r\n" + 
							"                                <h6>"+quiz.getName()+"</h6>\r\n" + 
							"                                <p style=\"color:green; font-weight:800;\">"+quiz.getNumberQuestion()+" câu hỏi.</p>\r\n" + 
							"                            </div>\r\n" + 
							"                        </div>\r\n" + 
							"                    </div>\r\n" + 
							"             </a>  \r\n" + 
							"            </div>");
				}
				
			
			
				
		 		
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 		
    }
	@RequestMapping(value = { "/quizDid" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quizDid(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if(session.getAttribute("user")==null) {
 			return new ModelAndView("redirect:/");
 		}
		try {
			UserDao userDao = new UserDao();
			Account acc = (Account) session.getAttribute("user");
			List<QuizUser> listQU = userDao.getQuizOfUser(acc.getUsername());
			model.addAttribute("title", "Quiz đã làm");
			model.addAttribute("catelogy", "none");
			model.addAttribute("listQU", listQU);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("/user/quizDid");
	}
	
	@RequestMapping(value = { "/updateUser" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView EditAcc(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if(session.getAttribute("user")==null) {
 			return new ModelAndView("redirect:/");
 		}
		Account account = (Account) session.getAttribute("user");
		String username = account.getUsername();
		String password = account.getPassword();
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		UserDao userDao = new UserDao();
		try {
			userDao.updateAcc(username,name, age);
			session.removeAttribute("user");
			Account newUser = userDao.getAcc(username, password);
			session.setAttribute("user", newUser);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/");
	}

}


