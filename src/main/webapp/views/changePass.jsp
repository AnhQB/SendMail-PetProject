<%@page import="fsoft.jits.mybatis.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="header.jsp" flush="true">
	  <jsp:param name="title" value="Verify" />
	</jsp:include>
	<% 
		User u = (User) request.getAttribute("user");
	%>

    <body class="authentication-bg" data-layout-config="{&quot;darkMode&quot;:false}">

        <div class="account-pages mt-5 mb-5">
            <div class="container">
                <div class="row justify-content-center">	
                    <div class="col-lg-5">
                        <div class="card">
                            <!-- Logo-->
                            <div class="card-header pt-4 pb-4 text-center bg-primary">
                                <a href="index.html">
                                    <span><img src="../resources/images/logo.png" alt="" height="18"></span>
                                </a>
                            </div>

                            <div class="card-body p-4">
                                
                                <div class="text-center w-75 m-auto">
                                    <h4 class="text-dark-50 text-center mt-0 font-weight-bold">Change Password</h4>
                                </div>

                                <form action="/process_changePass" method="post">
                                	<input type="hidden" name="id" value="<%= u.getId() %>" />
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /> 
					
                                    <div class="form-group">
                                        <label for="namee">Old Password</label>
                                        <input class="form-control" type="text" id="namee" name="oldPassword" placeholder="Enter your password in email" required="">
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="usernamee">New Password</label>
                                        <input class="form-control" type="text" id="usernamee" name="newPassword" placeholder="Enter your new password" required="">
                                    </div>

                                    <div class="form-group">
                                        <label for="addresss">Confirm Password</label>
                                        <input class="form-control" type="text" id="addresss" name="confirmPassword" required="" placeholder="Confirm password here">
                                    </div>

                                    <div class="form-group mb-0 text-center">
                                        <button class="btn btn-primary" type="submit"> Submit </button>
                                    </div>

                                </form>
                            </div> <!-- end card-body -->
                        </div>
                    </div> <!-- end col -->
                </div>
                <!-- end row -->
            </div>
            <!-- end container -->
        </div>
        <!-- end page -->

        <footer class="footer footer-alt">
            © Create by Bai
        </footer>

        <!-- bundle -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/vendor.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/app.min.js"></script>
    

</body></html>