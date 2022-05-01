/*
 * (C) Copyright 2022 Fresher Academy. All Rights Reserved
 *
 * @author NghiaHX
 * @birthDate 25/05/2000
 * @date 2022-04-11
 * version 1.0
 */
/**
 * 
 */
package fpt.fa.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.taglibs.standard.tag.common.xml.IfTag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fpt.fa.dao.QuizDAO;
import fpt.fa.dao.UserDao;
import fpt.fa.entities.Account;
import fpt.fa.entities.Question;
import fpt.fa.entities.Quiz;

/**
 * @author Admin
 *
 */
@Controller

public class AdminController {
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView trangchu(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("admin/quanly");

	}

	@RequestMapping(value = { "/huhu" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView traaaangchu(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("admin/NewFile");

	}

	@RequestMapping(value = { "/acc/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView ListAcc(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		try {
			UserDao userDao = new UserDao();
			int page = 1;
			int numberAcc = userDao.getCountAcc();
			int maxPage = numberAcc / 3;
			if (numberAcc % 3 != 0) {
				maxPage++;
			}
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			if (request.getParameter("message") != null) {
				if (request.getParameter("message").equals("pre")) {
					if (page > 1) {
						page--;
					}
				} else {
					if (page < maxPage) {
						page++;
					}
				}

			}
			List<Account> listAcc = userDao.getListAccByPage(page);
			model.addAttribute("page", page);
			model.addAttribute("maxPage", maxPage);
			model.addAttribute("listAcc", listAcc);
			return new ModelAndView("admin/listAcc");

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = { "/acc/create" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView CreateAcc(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		try {
			UserDao userDao = new UserDao();
			String username = request.getParameter("username");

			String password = request.getParameter("password");
			String name = request.getParameter("name");
			int age = Integer.parseInt(request.getParameter("age"));
			userDao.insertAcc(username, password, name, age);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/acc/list");
	}

	@RequestMapping(value = { "/acc/checkUser" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public void checkUser(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		UserDao userDao = new UserDao();
		String username = request.getParameter("username");

		try {
			PrintWriter out = response.getWriter();
			if (userDao.getAccByUsername(username) != null) {

				out.println("Đã tồn tại user");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = { "/acc/edit" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public void DisplayEditAcc(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			PrintWriter out = response.getWriter();
			UserDao userDao = new UserDao();
			String username = request.getParameter("username");
			System.out.println(username);
			Account acc = userDao.getAccByUsername(username);
			model.addAttribute("acc", acc);
			out.println(
					"<form action=\"/acc/edit\" method=\"post\"  id=\"formEditUser\" style=\" margin-left:50px;\"  onsubmit=\"return ValidationEdit();\">\r\n"
							+ "  <div class=\"form-group\">\r\n" + " <b>Username</b>"
							+ "    <input type=\"text\"  id=\"usernameEdit\" class=\"form-control\" style=\"width:350px;\" value=\""
							+ acc.getUsername() + "\" name=\"username\" readonly >\r\n"
							+ "<small style=\"color:red\" id=\"userValid\"></small>  </div>\r\n"
							+ "  <div class=\"form-group\">\r\n" + " <b>Password</b>"
							+ "    <input type=\"text\"  id=\"passwordEdit\" class=\"form-control\" style=\"width:350px;\" value=\""
							+ acc.getPassword() + "\"  name=\"password\">\r\n"
							+ " <small style=\"color:red\" id=\"password1\"></small>  </div>\r\n"
							+ "  <div class=\"form-group\">\r\n" + " <b>Name</b>"
							+ "    <input type=\"text\"  id=\"nameEdit\" class=\"form-control\" style=\"width:350px;\" value=\""
							+ acc.getName() + "\"  name=\"name\">\r\n"
							+ " <small style=\"color:red\" id=\"name1\"></small>  </div>\r\n"
							+ "  <div class=\"form-group\">\r\n" + " <b>Age</b>"
							+ "    <input type=\"text\"  id=\"ageEdit\" class=\"form-control\" style=\"width:350px;\" value=\""
							+ acc.getAge() + "\"  name=\"age\">\r\n"
							+ "<small style=\"color:red\" id=\"age1\"></small>  </div>\r\n"
							+ "  <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n" + "</form>");

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = { "/acc/edit" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView EditAcc(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		UserDao userDao = new UserDao();
		try {
			userDao.updateAcc(username, password, name, age);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/acc/list");
	}

	@RequestMapping(value = { "/acc/delete" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView DeleteAcc(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}

		try {
			String username = request.getParameter("username");
			UserDao userDao = new UserDao();
			userDao.deleteAcc(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/acc/list");
	}

	// quiz
	@RequestMapping(value = { "/quiz/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView ListQuiz(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		QuizDAO quizDAO = new QuizDAO();
		List<Quiz> listQuizs = new ArrayList<Quiz>();
		ArrayList<String> listCatelogy = new ArrayList<String>();

		try {
			listCatelogy = quizDAO.getAllCatelogyQuiz();
			listQuizs = quizDAO.getAllQuiz();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("listQuizs", listQuizs);
		model.addAttribute("listCatelogy", listCatelogy);
		return new ModelAndView("admin/listQuiz");
	}

	@RequestMapping(value = { "/quiz/create" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView CreateQuiz(@RequestParam(value = "image") CommonsMultipartFile file, Model model,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		try {

			QuizDAO quizDAO = new QuizDAO();

			String quizName = request.getParameter("quizName");

			String catelogy = request.getParameter("catelogy");
			// image

			String anh = "";
			String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file.getBytes();
			Date date = new Date();
			String name = date.getTime() + file.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			anh += "images/quiz/" + name;

			quizDAO.insertQuiz(quizName, catelogy, anh);

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/quiz/list");
	}

	@RequestMapping(value = { "/quiz/edit" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public void DisplayEditQuiz(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			PrintWriter out = response.getWriter();
			QuizDAO quizDAO = new QuizDAO();
			ArrayList<String> listCatelogy = quizDAO.getAllCatelogyQuiz();
			int quizID = Integer.parseInt(request.getParameter("quizID"));
			Quiz quiz = quizDAO.getQuizById(quizID);

			out.println(
					"<form action=\"/quiz/edit\" method=\"post\"  id=\"formEditQuiz\" style=\"align-items:center; text-align:center; margin-left:30px;\" onsubmit=\"return Validation2();\"  enctype=\"multipart/form-data\">\r\n"
							+ "<input type=\"text\" value=\"" + quiz.getQuizID() + "\" name=\"quizID\" hidden >"
							+ "  <div class=\"form-group\">\r\n"
							+ "    <input id=\"quizNameEdit\" type=\"text\" class=\"form-control\" style=\"width:250px;\" value=\""
							+ quiz.getName() + "\" name=\"quizName\" >\r\n" + "  </div>\r\n"
							+ "  <div class=\"form-group\">\r\n"
							+ "<small style=\"color:red\" id=\"errorNameEdit\"></small>"
							+ "     <select class=\"form-control\" name=\"catelogy\" id=\"selectCatelogy\">\r\n");
			for (int i = 0; i < listCatelogy.size(); i++) {
				if (listCatelogy.get(i).equals(quiz.getCatelogy())) {
					out.print("<option selected value=\"" + listCatelogy.get(i) + "\">" + listCatelogy.get(i)
							+ "</option>");
				} else {
					out.print("<option value=\"" + listCatelogy.get(i) + "\">" + listCatelogy.get(i) + "</option>");
				}

			}
			out.println("</select> </div><small style=\"color:red\" id=\"errorCatelogy\"></small>\r\n"
					+ "  <div style=\"display:flex;\" class=\"form-group\">\r\n"
					+ " <img id=\"newImage\" src=\"#\" alt=\"your image\" class=\"hide\">"
					+ "<img id=\"oldImage\" src=\"/" + quiz.getImage() + "\" alt=\"image\" height=\"50\" width=\"50\">"
					+ "    <input onchange=\"readURL(this);\" type=\"file\" class=\"form-control\" style=\"width:200px;\" "
					+ "  name=\"image\">\r\n" + "  </div>\r\n" + "  <div class=\"form-group\">\r\n"
					+ " <small style=\"color:red\" id=\"errorImage\"></small>"

					+ "    <input type=\"text\" class=\"form-control\" style=\"width:250px;\" value=\""
					+ quiz.getNumberQuestion() + "\"  name=\"numberQuestion\" hidden>\r\n" + "  </div>\r\n"
					+ "  <button type=\"submit\" class=\"btn btn-primary\">Submit</button>\r\n" + "</form>");

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = { "/quiz/edit" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView EditQuiz(@RequestParam(value = "image") CommonsMultipartFile file, Model model,
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		try {

			QuizDAO quizDAO = new QuizDAO();
			String quizName = request.getParameter("quizName");

			String catelogy = request.getParameter("catelogy");
			int numberQuestion = Integer.parseInt(request.getParameter("numberQuestion"));
			int quizID = Integer.parseInt(request.getParameter("quizID"));
			// image
			String image = "";
			if (file != null) {
				String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
				File dir = new File(absolutefilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				byte[] bytes = file.getBytes();
				Date date = new Date();
				String name = date.getTime() + file.getOriginalFilename();

				File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				outputStream.write(bytes);
				outputStream.close();
				image += "/images/quiz/" + name;
			}

			if (file.getOriginalFilename().equals("")) {
				quizDAO.updateQuizNoImage(quizID, quizName, catelogy, numberQuestion);
			} else {
				quizDAO.updateQuiz(quizID, quizName, catelogy, image, numberQuestion);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/quiz/list");
	}

	@RequestMapping(value = { "/quiz/delete" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView DeleteQuiz(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		try {
			int quizID = Integer.parseInt(request.getParameter("quizID"));
			QuizDAO quizDAO = new QuizDAO();
			quizDAO.deleteQuiz(quizID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/quiz/list");
	}

	@RequestMapping(value = { "quiz/search" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView timkiem(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		try {
			QuizDAO quizDAO = new QuizDAO();
			String key = (String) request.getParameter("keySearch");
			List<Quiz> listQuizs = quizDAO.getQuizbyName(key);
			model.addAttribute("listQuizs", listQuizs);
			return new ModelAndView("admin/listQuiz");

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return new ModelAndView("redirect:/admin");
	}

	@RequestMapping(value = { "acc/search" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView timkiemacc(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}

		try {
			int page = 1;
			UserDao userDao = new UserDao();
			String key = (String) request.getParameter("keySearch");
			List<Account> listAcc = userDao.findAccByUsername(key);
			model.addAttribute("listAcc", listAcc);
			model.addAttribute("pagination", "none");
			return new ModelAndView("admin/listAcc");

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return new ModelAndView("redirect:/admin");
	}

	// question
	@RequestMapping(value = { "/question/list" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView QuestionQuiz(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		QuizDAO quizDAO = new QuizDAO();
		List<Quiz> listQuiz = new ArrayList<Quiz>();
		Question question = new Question(0, "0", "0", "0", "0", "0", "0", "0", "0", 0);
		model.addAttribute("message", "Chọn Quiz bạn muốn xem");
		model.addAttribute("q", question);
		model.addAttribute("message2", "none");
		try {
			listQuiz = quizDAO.getAllQuiz();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("listQuizs", listQuiz);
		return new ModelAndView("admin/listQuestion");
	}

	@RequestMapping(value = { "/question/search" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public void QuestionEdit(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		QuizDAO quizDAO = new QuizDAO();
		PrintWriter out = response.getWriter();
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		model.addAttribute("quizID", quizID);
		ArrayList<Question> listQuestions = new ArrayList<Question>();

		try {

			listQuestions = quizDAO.getQuestionByQuizID(quizID);
			out.println("<table id=\"questionTable\" class=\"table table-hover\" style=\"width: 800px;\">\r\n"
					+ "		<thead>\r\n" + "			<tr style=\"text-align: center;\">\r\n"
					+ "				<th scope=\"col\">QuestionID</th>\r\n"
					+ "				<th scope=\"col\">Question</th>\r\n"
					+ "				<th scope=\"col\">CorrectAnswer</th>\r\n"
					+ "				<th scope=\"col\">Action</th>\r\n" + "			</tr>\r\n" + "		</thead>\r\n"
					+ "		<tbody>");
			for (Question question : listQuestions) {
				StringBuilder element = new StringBuilder("<td>");
				if (question.getQuestion() == null) {
					element.append("<audio src=\"/assets/audio/");
					element.append(question.getAudio());
					element.append("\" controls style=\"width:100px;\"></audio>");
					element.append("</td>");
				} else {
					element.append(question.getQuestion());
					element.append("</td>");
				}
				out.println("\r\n" + "				<tr style=\"text-align: center;\">\r\n" + "					<td>"
						+ question.getQuestionID() + "</td>\r\n" + element + "					<td>"
						+ question.getCorrectAnswer() + "</td>\r\n"
						+ "					<td><button class=\"btn btn-success\"\r\n"
						+ "						onclick=\"getQuestion('" + question.getQuestionID() + "')\"><i\r\n"
						+ "							class=\"fa fa-edit\"></i></button>\r\n"
						+ "						<button type=\"button\" class=\"btn btn-danger\" data-toggle=\"modal\"\r\n"
						+ "							data-target=\"#modal" + question.getQuestionID() + "\">\r\n"
						+ "							<i class=\"fa fa-trash\"></i>\r\n"
						+ "						</button></td>\r\n" + "				</tr>\r\n"
						+ "				<div class=\"modal fade\" id=\"modal" + question.getQuestionID()
						+ "\" tabindex=\"-1\"\r\n"
						+ "					role=\"dialog\" aria-labelledby=\"exampleModalLabel"
						+ question.getQuestionID() + "\"\r\n" + "					aria-hidden=\"true\">\r\n"
						+ "					<div class=\"modal-dialog\" role=\"document\">\r\n"
						+ "						<div class=\"modal-content\">\r\n"
						+ "							<div class=\"modal-header\">\r\n"
						+ "								<h5 class=\"modal-title\" id=\"exampleModalLabel"
						+ question.getQuestionID() + "\">Xác\r\n" + "									nhận</h5>\r\n"
						+ "								<button type=\"button\" class=\"close\" data-dismiss=\"modal\"\r\n"
						+ "									aria-label=\"Close\">\r\n"
						+ "									<span aria-hidden=\"true\">&times;</span>\r\n"
						+ "								</button>\r\n" + "							</div>\r\n"
						+ "							<div class=\"modal-body\">\r\n"
						+ "								Bạn muốn xóa Question có ID: <span style=\"color: red;\">"
						+ question.getQuestionID() + "</span>\r\n" + "								?\r\n"
						+ "							</div>\r\n"
						+ "							<div class=\"modal-footer\">\r\n"
						+ "								<button type=\"button\" class=\"btn btn-secondary\"\r\n"
						+ "									data-dismiss=\"modal\">Close</button>\r\n"
						+ "								<a class=\"btn btn-danger\"\r\n"
						+ "									href=\"/question/delete?questionID="
						+ question.getQuestionID() + "\">Yes</a>\r\n" + "							</div>\r\n"
						+ "						</div>\r\n" + "					</div>\r\n" + "				</div>");

			}
			out.println("</tbody>\r\n" + "	</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = { "/question/addFull" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView AddQuestionFull(@RequestParam(value = "image") CommonsMultipartFile file,
			@RequestParam(value = "audio") CommonsMultipartFile file2, RedirectAttributes redirectAttributes,
			Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		try {
			int quizID = Integer.parseInt(request.getParameter("quizID"));
			String question = request.getParameter("question");
			String answerA = request.getParameter("answerA");
			String answerB = request.getParameter("answerB");
			String answerC = request.getParameter("answerC");
			String answerD = request.getParameter("answerD");
			String correctAnswer = request.getParameter("correctAnswer");
			// image

			String image = "";
			String absolutefilePath = request.getServletContext().getRealPath("/images/" + quizID);
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file.getBytes();
			Date date = new Date();
			String name = date.getTime() + file.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			image += "/images/" + quizID + "/" + name;
			// audio

			String audio = "";
			String absolutefilePath2 = request.getServletContext().getRealPath("/audios/" + quizID);
			File dir2 = new File(absolutefilePath2);
			if (!dir2.exists()) {
				dir2.mkdirs();
			}

			byte[] bytes2 = file2.getBytes();
			Date date2 = new Date();
			String name2 = date2.getTime() + file2.getOriginalFilename();

			File uploadfile2 = new File(dir2.getAbsolutePath() + "\\" + name2);
			BufferedOutputStream outputStream2 = new BufferedOutputStream(new FileOutputStream(uploadfile2));
			outputStream2.write(bytes2);
			outputStream2.close();
			audio += "audios/" + quizID + "/" + name2;
			QuizDAO quizDAO = new QuizDAO();
			quizDAO.addQuestion(quizID, question, answerA, answerB, answerC, answerD, correctAnswer, image, audio);
			redirectAttributes.addFlashAttribute("quizID", quizID);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/question/list");
	}

	@RequestMapping(value = { "/question/delete" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView DeleteQuestion(RedirectAttributes rd,Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}

		try {
			int questionID = Integer.parseInt(request.getParameter("questionID"));
			int quizID = Integer.parseInt(request.getParameter("quizID"));
			rd.addFlashAttribute("quizID", quizID);
			QuizDAO quizDAO = new QuizDAO();
			quizDAO.deleteQuestion(questionID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("redirect:/question/editQuestion");
	}

	@RequestMapping(value = { "/question/edit" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public void EditQuestion(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			int questionID = Integer.parseInt(request.getParameter("questionID"));
			QuizDAO quizDAO = new QuizDAO();
			Question q = quizDAO.getQuestionByID(questionID);
			try {
				StringBuilder question = new StringBuilder("");
				if (q.getQuestion() != null) {
					question.append(q.getQuestion());
				}
				StringBuilder answerA = new StringBuilder("");
				if (q.getAnswerA() != null) {
					answerA.append(q.getAnswerA());
				}
				StringBuilder answerB = new StringBuilder("");
				if (q.getAnswerB() != null) {
					answerB.append(q.getAnswerB());
				}
				StringBuilder answerC = new StringBuilder("");
				if (q.getAnswerC() != null) {
					answerC.append(q.getAnswerC());
				}
				StringBuilder answerD = new StringBuilder("");
				if (q.getAnswerD() != null) {
					answerD.append(q.getAnswerD());
				}
				StringBuilder image = new StringBuilder("");
				if (q.getImage() != null) {
					image.append(q.getImage());
				}
				StringBuilder audio = new StringBuilder("");
				if (q.getAudio() != null) {
					audio.append(q.getAudio());
				}
				PrintWriter out = response.getWriter();
				out.println("<div id=\"formEditQuestion\" style=\"width:500px; position:absolute; right:20px;\">\r\n"
						+ "			<input type=\"text\" value=\"" + question
						+ "\" class=\"form-control\" placeholder=\"Question\" name=\"question\">\r\n"
						+ "			<input type=\"text\" value=\"" + answerA
						+ "\" class=\"form-control\" placeholder=\"AnswerA\" name=\"answerA\">\r\n"
						+ "			<input type=\"text\" value=\"" + answerB
						+ "\" class=\"form-control\" placeholder=\"AnswerB\" name=\"answerB\">\r\n"
						+ "			<input type=\"text\" value=\"" + answerC
						+ "\" class=\"form-control\" placeholder=\"AnswerC\" name=\"answerC\">\r\n"
						+ "			<input type=\"text\" value=\"" + answerD
						+ "\" class=\"form-control\" placeholder=\"AnswerD\" name=\"answerD\">\r\n"
						+ "			<input type=\"text\" value=\"" + q.getCorrectAnswer()
						+ "\" class=\"form-control\" placeholder=\"Correct Answer\" name=\"correctAnswer\">\r\n"
						+ "			<input type=\"text\" value=\"" + image
						+ "\" class=\"form-control\" placeholder=\"Image\" name=\"image\">\r\n"
						+ "			<input type=\"text\" value=\"" + audio
						+ "\" class=\"form-control\" placeholder=\"Audio\" name=\"audio\">\r\n"
						+ "			<input type=\"text\" value=\"" + questionID
						+ "\" class=\"form-control\"  name=\"questionID\" hidden>\r\n"
						+ "			<input type=\"text\" value=\"" + q.getQuizID()
						+ "\" class=\"form-control\"  name=\"quizID\" hidden>\r\n"
						+ "			<button class=\"btn btn-success\" type=\"submit\">Edit</button>\r\n"
						+ "		</div>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = { "/question/edit" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView EditQuestionAction(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}

		try {
			int questionID = Integer.parseInt(request.getParameter("questionID"));
			QuizDAO quizDAO = new QuizDAO();
			int quizID = Integer.parseInt(request.getParameter("quizID"));
			String question = request.getParameter("question");
			String answerA = request.getParameter("answerA");
			String answerB = request.getParameter("answerB");
			String answerC = request.getParameter("answerC");
			String answerD = request.getParameter("answerD");
			String correctAnswer = request.getParameter("correctAnswer");
			String image = request.getParameter("image");
			String audio = request.getParameter("audio");
			quizDAO.updateQuestion(questionID, question, answerA, answerB, answerC, answerD, correctAnswer, image,
					audio, quizID);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/question/list");
	}

	@RequestMapping(value = {
			"/question/editQuestion" }, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public ModelAndView quizEditQuestion(Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {

		QuizDAO quizDAO = new QuizDAO();
		int quizID = 0;
		if (request.getParameter("quizID") != null) {
			quizID = Integer.parseInt(request.getParameter("quizID"));

		} else {
			quizID = (int) model.asMap().get("quizID");
		}

		Quiz quiz = quizDAO.getQuizById(quizID);
		model.addAttribute("quizID", quizID);
		ArrayList<Question> listQuestions = new ArrayList<Question>();
		ArrayList<Quiz> listQuizs = quizDAO.getAllQuiz();
		try {

			listQuestions = quizDAO.getQuestionByQuizID(quizID);
			model.addAttribute("listQuestions", listQuestions);
			model.addAttribute("listQuizs", listQuizs);
			model.addAttribute("quiz", quiz);
			if (listQuestions.size() == 0) {
				Question question = new Question(0, "0", "0", "0", "0", "0", "0", "0", "0", 0);
				model.addAttribute("message", "Chưa có câu hỏi");
				model.addAttribute("q", question);
			} else {
				model.addAttribute("q", listQuestions.get(0));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("admin/listQuestion");

	}

	@RequestMapping(value = { "/question/update1" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView update1(RedirectAttributes rd, Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) throws IOException, SQLException {

		QuizDAO quizDAO = new QuizDAO();
		int questionID = Integer.parseInt(request.getParameter("questionID"));
		String question = request.getParameter("question");
		String answerA = request.getParameter("answerA");
		String answerB = request.getParameter("answerB");
		String answerC = request.getParameter("answerC");
		String answerD = request.getParameter("answerD");
		String correctAnswer = request.getParameter("correctAnswer");
		int quizID = Integer.parseInt(request.getParameter("quizID"));

		try {
			quizDAO.updateQuestion1(questionID, question, answerA, answerB, answerC, answerD, correctAnswer, quizID);
			rd.addFlashAttribute("quizID", quizID);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/question/editQuestion");

	}

	@RequestMapping(value = { "/question/update2" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView update2(@RequestParam(value = "image") CommonsMultipartFile file, RedirectAttributes rd,
			Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {

		QuizDAO quizDAO = new QuizDAO();
		int questionID = Integer.parseInt(request.getParameter("questionID"));
		String question = request.getParameter("question");
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		// image
		String image = "";
		if (file != null) {
			String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file.getBytes();
			Date date = new Date();
			String name = date.getTime() + file.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			image += "/images/quiz/" + name;
		}

		if (file.getOriginalFilename().equals("")) {
			quizDAO.updateQuestion2NoImage(questionID, question, question.toLowerCase(), quizID);
		} else {
			quizDAO.updateQuestion2(questionID, question, question.toLowerCase(), image, quizID);
		}
		rd.addFlashAttribute("quizID", quizID);
		return new ModelAndView("redirect:/question/editQuestion");

	}

	@RequestMapping(value = { "/question/update3" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView update3(@RequestParam(value = "answerA") CommonsMultipartFile file1,
			@RequestParam(value = "answerB") CommonsMultipartFile file2,
			@RequestParam(value = "answerC") CommonsMultipartFile file3,
			@RequestParam(value = "answerD") CommonsMultipartFile file4,
			@RequestParam(value = "audio") CommonsMultipartFile file5, RedirectAttributes rd, Model model,
			HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {

		QuizDAO quizDAO = new QuizDAO();
		int questionID = Integer.parseInt(request.getParameter("questionID"));
		Question questionRoot = quizDAO.getQuestionByID(questionID);
		String correctAnswer = request.getParameter("correctAnswer");
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		// image1
		String image1 = "";
		if (file1 != null) {
			String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file1.getBytes();
			Date date = new Date();
			String name = date.getTime() + file1.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			image1 += "/images/quiz/" + name;
		}

		if (file1.getOriginalFilename().equals("")) {
			image1 = questionRoot.getAnswerA();
		}
		// image1
		String image2 = "";
		if (file2 != null) {
			String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file2.getBytes();
			Date date = new Date();
			String name = date.getTime() + file2.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			image2 += "/images/quiz/" + name;
		}

		if (file2.getOriginalFilename().equals("")) {
			image2 = questionRoot.getAnswerB();
		}
		// image3
		String image3 = "";
		if (file1 != null) {
			String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file3.getBytes();
			Date date = new Date();
			String name = date.getTime() + file3.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			image3 += "/images/quiz/" + name;
		}

		if (file3.getOriginalFilename().equals("")) {
			image3 = questionRoot.getAnswerC();
		}
		// image4
		String image4 = "";
		if (file4 != null) {
			String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file4.getBytes();
			Date date = new Date();
			String name = date.getTime() + file4.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			image4 += "/images/quiz/" + name;
		}

		if (file4.getOriginalFilename().equals("")) {
			image4 = questionRoot.getAnswerD();
		}
		// audio

		String audio = "";
		if (file5 != null) {
			String absolutefilePath = request.getServletContext().getRealPath("/audios/" + quizID);
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file5.getBytes();
			Date date = new Date();
			String name = date.getTime() + file5.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			audio += "audios/" + quizID + "/" + name;
		}
		if (file5.getOriginalFilename().equals("")) {
			audio = questionRoot.getAudio();
		}
		quizDAO.updateQuestion3(questionID, image1, image2, image3, image4, correctAnswer, audio, quizID);
		rd.addFlashAttribute("quizID", quizID);
		return new ModelAndView("redirect:/question/editQuestion");

	}

	@RequestMapping(value = { "/question/add1" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView questionAdd1(RedirectAttributes redirectAttributes, Model model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		try {
			QuizDAO quizDAO = new QuizDAO();
			int quizID = Integer.parseInt(request.getParameter("quizID"));
			String question = request.getParameter("question");
			String answerA = request.getParameter("answerA");
			String answerB = request.getParameter("answerB");
			String answerC = request.getParameter("answerC");
			String answerD = request.getParameter("answerD");
			String correctAnswer = request.getParameter("correctAnswer");
			quizDAO.addQuestion1(question, answerA, answerB, answerC, answerD, correctAnswer, quizID);
			redirectAttributes.addFlashAttribute("quizID", quizID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/question/editQuestion");
	}

	@RequestMapping(value = { "/question/add2" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView questionAdd2(@RequestParam(value = "image") CommonsMultipartFile file,
			RedirectAttributes redirectAttributes, Model model, HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		if (session.getAttribute("user") != null) {
			Account account = (Account) session.getAttribute("user");
			if (account.getRole() != 0) {
				return new ModelAndView("redirect:/");
			}
		} else {
			return new ModelAndView("redirect:/");
		}
		try {
			QuizDAO quizDAO = new QuizDAO();
			int quizID = Integer.parseInt(request.getParameter("quizID"));
			String question = request.getParameter("question");
			// image1
			String image = "";
			if (file != null) {
				String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
				File dir = new File(absolutefilePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}

				byte[] bytes = file.getBytes();
				Date date = new Date();
				String name = date.getTime() + file.getOriginalFilename();

				File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
				outputStream.write(bytes);
				outputStream.close();
				image += "/images/quiz/" + name;
			}

			quizDAO.addQuestion2(question, image, quizID);
			redirectAttributes.addFlashAttribute("quizID", quizID);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/question/editQuestion");
	}
	
	@RequestMapping(value = { "/question/add3" }, method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ModelAndView questionAdd2(@RequestParam(value = "answerA") CommonsMultipartFile file1,
			@RequestParam(value = "answerB") CommonsMultipartFile file2,
			@RequestParam(value = "answerC") CommonsMultipartFile file3,
			@RequestParam(value = "answerD") CommonsMultipartFile file4,
			@RequestParam(value = "audio") CommonsMultipartFile file5, RedirectAttributes rd, Model model,
			HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws IOException, SQLException {

		QuizDAO quizDAO = new QuizDAO();
		String correctAnswer = request.getParameter("correctAnswer");
		int quizID = Integer.parseInt(request.getParameter("quizID"));
		// image1
		String image1 = "";
		if (file1 != null) {
			String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file1.getBytes();
			Date date = new Date();
			String name = date.getTime() + file1.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			image1 += "/images/quiz/" + name;
		}

		
		// image1
		String image2 = "";
		if (file2 != null) {
			String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file2.getBytes();
			Date date = new Date();
			String name = date.getTime() + file2.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			image2 += "/images/quiz/" + name;
		}
		// image3
		String image3 = "";
		if (file1 != null) {
			String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file3.getBytes();
			Date date = new Date();
			String name = date.getTime() + file3.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			image3 += "/images/quiz/" + name;
		}

		
		// image4
		String image4 = "";
		if (file4 != null) {
			String absolutefilePath = request.getServletContext().getRealPath("/images/quiz");
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file4.getBytes();
			Date date = new Date();
			String name = date.getTime() + file4.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			image4 += "/images/quiz/" + name;
		}

		
		// audio

		String audio = "";
		if (file5 != null) {
			String absolutefilePath = request.getServletContext().getRealPath("/audios/" + quizID);
			File dir = new File(absolutefilePath);
			if (!dir.exists()) {
				dir.mkdirs();
			}

			byte[] bytes = file5.getBytes();
			Date date = new Date();
			String name = date.getTime() + file5.getOriginalFilename();

			File uploadfile = new File(dir.getAbsolutePath() + "\\" + name);
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(uploadfile));
			outputStream.write(bytes);
			outputStream.close();
			audio += "audios/" + quizID + "/" + name;
		}
		quizDAO.addQuestion3(image1, image2, image3, image4, correctAnswer, audio, quizID);
		rd.addFlashAttribute("quizID", quizID);
		return new ModelAndView("redirect:/question/editQuestion");

	}
}
