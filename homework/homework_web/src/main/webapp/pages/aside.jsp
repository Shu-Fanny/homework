<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.request.contextPath}/img/user2-160x160.jpg"
					class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>
					<c:if test="${not empty student}">
						${student.s_name}
					</c:if>

					<c:if test="${not empty teacher}">
						${teacher.t_name}
					</c:if>
				</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>

		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">菜单</li>
			<li id="admin-index"><a
				href="${pageContext.request.contextPath}/pages/main.jsp"><i
					class="fa fa-dashboard"></i> <span>首页</span></a></li>

			<c:if test="${not empty student}">
			<li class="treeview"><a href="#"> <i class="fa fa-cogs"></i>
					<span>学生作业管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>


			</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/student/findHomeWorkByState.do?state=0&num=1"> <i
							class="fa fa-circle-o"></i> 未完成作业
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/student/findHomeWorkByState.do?state=1&num=1"> <i
							class="fa fa-circle-o"></i> 已提交作业
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/student/findHomeWorkByState.do?state=2&num=1">
							<i class="fa fa-circle-o"></i> 已被批改作业
					</a></li>
				</ul>
			</li>
			</c:if>

			<c:if test="${not empty teacher}">
			<li class="treeview"><a href="#"> <i class="fa fa-cube"></i>
					<span>教师作业管理</span> <span class="pull-right-container"> <i
						class="fa fa-angle-left pull-right"></i>
				</span>
			</a>
				<ul class="treeview-menu">

					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/teacher/findAllHomeWorkWithPage.do?num=1">
							<i class="fa fa-circle-o"></i> 已发布作业
					</a></li>
					<li id="system-setting"><a
						href="${pageContext.request.contextPath}/teacher/findCommitHomeWorkWithPage.do?state=1&num=1"> <i
							class="fa fa-circle-o"></i> 待批改作业
					</a></li>
					<li id="system-setting"><a
							href="${pageContext.request.contextPath}/teacher/findCommitHomeWorkWithPage.do?state=2&num=1"> <i
							class="fa fa-circle-o"></i> 已批改作业
					</a></li>
				</ul>
			</li>
			</c:if>

		</ul>
	</section>
	<!-- /.sidebar -->
</aside>