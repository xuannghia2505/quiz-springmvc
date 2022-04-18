<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../layout/header.jsp" %>
<h2 class="text-title-custom" style="width:200px;">Quiz đã làm</h2>	
<div id="cards_landscape_wrap-2">

    <div class="container" >
        <div class="row">
        	<c:forEach items="${listQU}" var="quiz">
            <div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
              <c:if test="${sessionScope.user!=null}">
                <a href="/quiz?quizID=${quiz.quizID }">
                </c:if>
                  <c:if test="${sessionScope.user==null}">
                <a href="/login">
                </c:if>
                    <div class="card-flyer">
                        <div class="text-box">
                            <div class="image-box">
                                <img src="${quiz.image }" alt="" />
                            </div>
                            <div class="text-container">
                                <h6>${quiz.name }</h6>
                                <c:if test="${quiz.score<50 }">
                                <b style="color:red;">${quiz.score}%</b>
                                 </c:if>
                                   <c:if test="${quiz.score>49 }">
                                <b style="color:green;">${quiz.score}%</b>
                                 </c:if>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
            </c:forEach>
            
        </div>
    </div>
</div>
<div class="modal fade" id="modal" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Thay đổi thông
					tin</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form action="/updateUser" method="post">
				<div class="modal-body">
					<div style="text-align: center; font-size: 30px;">Xin chào
						${user.name }</div>

					<div class="form-group">
						<label for="exampleInputEmail1">Tên của bạn</label> <input
							type="text" class="form-control" id="exampleInputEmail1"
							placeholder="Nhập tên của bạn" value="${user.name }" name="name">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail2">Tên của bạn</label> <input
							type="number" class="form-control" id="exampleInputEmail2"
							placeholder="Nhập tuổi của bạn" value="${user.age }" name="age">
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
					<button class="btn btn-success" type="submit">Cập nhật</button>
				</div>
			</form>
		</div>
	</div>
</div>

<%@include file="../layout/footer.jsp" %>