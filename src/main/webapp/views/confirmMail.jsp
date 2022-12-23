<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="header.jsp" flush="true">
	  <jsp:param name="title" value="Confirm Email" />
	</jsp:include>

    <body class="authentication-bg" data-layout-config="{&quot;darkMode&quot;:false}">
        <div class="account-pages mt-5 mb-5">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card">
                            <!-- Logo -->
                            <div class="card-header pt-4 pb-4 text-center bg-primary">
                                <a href="index.html">
                                    <span><img src="assets/images/logo.png" alt="" height="18"></span>
                                </a>
                            </div>

                            <div class="card-body p-4">
                                
                                <div class="text-center m-auto">
                                    <h4 class="text-dark-50 text-center mt-4 font-weight-bold">Please check your email</h4>
                                    <p class="text-muted mb-4">
                                        A email has been send to <b><%= request.getAttribute("email") %></b>.
                                        Please check for an email from company and click on the included link to
                                        reset your password. 
                                    </p>
                                </div>

                                <form action="/welcome">
                                    <div class="form-group mb-0 text-center">
                                        <button class="btn btn-primary" type="submit"><i class="mdi mdi-home mr-1"></i> Back to Home</button>
                                    </div>
                                </form>

                            </div> <!-- end card-body-->
                        </div>
                        <!-- end card-->
                        
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
        <script src="assets/js/vendor.min.js"></script>
        <script src="assets/js/app.min.js"></script>
        
    

</body></html>