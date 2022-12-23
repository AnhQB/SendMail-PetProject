<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
	<jsp:include page="header.jsp" flush="true">
	  <jsp:param name="title" value="Success Change Password" />
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
                                
                                <div class="text-center w-75 m-auto">
                                    <p class="text-muted mb-4">You have not permission to access this page</p>
                                </div>

                                <div class="logout-icon m-auto">
                                    <img width="150px" src= "https://uxwing.com/wp-content/themes/uxwing/download/checkmark-cross/red-x-line-icon.png">
                                </div> <!-- end logout-icon-->

                            </div> <!-- end card-body-->
                        </div> <!-- end card-->

                        <div class="row mt-3">
                            <div class="col-12 text-center">
                                <p class="text-muted">Back to <a href="/" class="text-muted ml-1"><b>Home</b></a></p>
                            </div> <!-- end col -->
                        </div>
                        <!-- end row -->

                    </div> <!-- end col -->
                </div>
                <!-- end row -->
            </div>
            <!-- end container -->
        </div>
        <!-- end page -->

        <footer class="footer footer-alt">
            2018 - 2020 © Hyper - Coderthemes.com
        </footer>

        <!-- bundle -->
        <script src="assets/js/vendor.min.js"></script>
        <script src="assets/js/app.min.js"></script>
        
    

</body></html>