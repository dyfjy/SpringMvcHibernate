<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String context = request.getContextPath();
	pageContext.setAttribute("context_", context);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8" />
    <title>Metronic Frotnend | Blog - Item</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
    <meta content="" name="description" />
    <meta content="alexgaoyh" name="author" />

   <!-- BEGIN GLOBAL MANDATORY STYLES -->          
   <link href="${pageContext.request.contextPath}/front/assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
   <link href="${pageContext.request.contextPath}/front/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
   <!-- END GLOBAL MANDATORY STYLES -->
   
   <!-- BEGIN PAGE LEVEL PLUGIN STYLES --> 
   <link href="${pageContext.request.contextPath}/front/assets/plugins/fancybox/source/jquery.fancybox.css" rel="stylesheet" />               
   <!-- END PAGE LEVEL PLUGIN STYLES -->

   <!-- BEGIN THEME STYLES --> 
   <link href="${pageContext.request.contextPath}/front/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
   <link href="${pageContext.request.contextPath}/front/assets/css/style.css" rel="stylesheet" type="text/css"/>
   <link href="${pageContext.request.contextPath}/front/assets/css/themes/blue.css" rel="stylesheet" type="text/css" id="style_color"/>
   <link href="${pageContext.request.contextPath}/front/assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
   <link href="${pageContext.request.contextPath}/front/assets/css/custom.css" rel="stylesheet" type="text/css"/>
   <!-- END THEME STYLES -->
   
   <!-- ueditor 视频播放 -->
   <link href="${pageContext.request.contextPath}/ueditor/third-party/video-js/video-js.min.css" rel="stylesheet" type="text/css"/>
   <link href="${pageContext.request.contextPath}/ueditor/third-party/video-js/video.js" rel="stylesheet" type="text/css"/>
   <link href="${pageContext.request.contextPath}/ueditor/ueditor.parse.js" rel="stylesheet" type="text/css"/>

   <link rel="shortcut icon" href="favicon.ico" />
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
	
	<!-- BEGIN HEADER -->
    <jsp:include page="../../common/header.jsp"></jsp:include>
    <!-- END HEADER -->
	
    <!-- BEGIN PAGE CONTAINER -->  
    <div class="page-container">

        <!-- BEGIN BREADCRUMBS -->   
        <div class="row breadcrumbs margin-bottom-40">
            <div class="container">
                <div class="col-md-4 col-sm-4">
                    <h1>${newsTemplete.name}</h1>
                </div>
                <div class="col-md-8 col-sm-8">
                    <ul class="pull-right breadcrumb">
                        <li><a href="index.html">Home</a></li>
                        <li><a href="">Pages</a></li>
                        <li class="active">Blog Item</li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- END BREADCRUMBS -->

		<!-- BEGIN CONTAINER -->   
		<div class="container min-hight">
			<!-- BEGIN BLOG -->
			<div class="row">
				<!-- BEGIN LEFT SIDEBAR -->
				<!-- TODO -->
				<div class="col-md-9 blog-item margin-bottom-40">
					${newsTemplete.content}
				</div>
				<!-- END LEFT SIDEBAR -->

				<!-- BEGIN RIGHT SIDEBAR -->            
				<div class="col-md-3 blog-sidebar">
					<!-- CATEGORIES START -->
					<h2 class="no-top-space">Categories</h2>
					<ul class="nav sidebar-categories margin-bottom-40">
						<li><a href="#">London (18)</a></li>
						<li><a href="#">Moscow (5)</a></li>
						<li class="active"><a href="#">Paris (12)</a></li>
						<li><a href="#">Berlin (7)</a></li>
						<li><a href="#">Instanbul (3)</a></li>
					</ul>
					<!-- CATEGORIES END -->
					
					<!-- BEGIN RECENT NEWS -->                            
					<h2>Recent News</h2>
					<div class="recent-news margin-bottom-10">
						<div class="row margin-bottom-10">
							<div class="col-md-3">
								<img src="${pageContext.request.contextPath}/front/assets/img/people/img2-large.jpg" alt="" class="img-responsive">                        
							</div>
							<div class="col-md-9 recent-news-inner">
								<h3><a href="#">Letiusto gnissimos</a></h3>
								<p>Decusamus tiusto odiodig nis simos ducimus qui sint</p>
							</div>                        
						</div>
						<div class="row margin-bottom-10">
							<div class="col-md-3">
								<img src="${pageContext.request.contextPath}/front/assets/img/people/img1-large.jpg" alt="" class="img-responsive">                        
							</div>
							<div class="col-md-9 recent-news-inner">
								<h3><a href="#">Deiusto anissimos</a></h3>
								<p>Decusamus tiusto odiodig nis simos ducimus qui sint</p>
							</div>                        
						</div>
						<div class="row margin-bottom-10">
							<div class="col-md-3">
								<img src="${pageContext.request.contextPath}/front/assets/img/people/img3-large.jpg" alt="" class="img-responsive">                        
							</div>
							<div class="col-md-9 recent-news-inner">
								<h3><a href="#">Tesiusto baissimos</a></h3>
								<p>Decusamus tiusto odiodig nis simos ducimus qui sint</p>
							</div>                        
						</div>
					</div>
					<!-- END RECENT NEWS -->                            

					<!-- BEGIN BLOG TALKS -->
					<div class="blog-talks margin-bottom-30">
						<h2>Popular Talks</h2>
						<div class="tab-style-1">
							<ul class="nav nav-tabs">
								<li class="active"><a href="#tab-1" data-toggle="tab">Multipurpose</a></li>
								<li><a href="#tab-2" data-toggle="tab">Documented</a></li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane row-fluid fade in active" id="tab-1">
									<p class="margin-bottom-10">Raw denim you probably haven't heard of them jean shorts Austin. eu banh mi, qui irure terry richardson ex squid Aliquip placeat salvia cillum iphone.</p>
									<p><a href="#" class="read-more">Read more</a></p>
								</div>
								<div class="tab-pane fade" id="tab-2">
									<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. aliquip jean shorts ullamco ad vinyl aesthetic magna delectus mollit. Keytar helvetica VHS salvia..</p>
								</div>
							</div>
						</div>
					</div>                            
					<!-- END BLOG TALKS -->

					<!-- BEGIN BLOG PHOTOS STREAM -->
					<div class="blog-photo-stream margin-bottom-20">
						<h2>Photos Stream</h2>
						<ul class="list-unstyled">
							<li><a href="#"><img src="${pageContext.request.contextPath}/front/assets/img/people/img5-small.jpg" alt=""></a></li>
							<li><a href="#"><img src="${pageContext.request.contextPath}/front/assets/img/works/img1.jpg" alt=""></a></li>
							<li><a href="#"><img src="${pageContext.request.contextPath}/front/assets/img/people/img4-large.jpg" alt=""></a></li>
							<li><a href="#"><img src="${pageContext.request.contextPath}/front/assets/img/works/img6.jpg" alt=""></a></li>
							<li><a href="#"><img src="${pageContext.request.contextPath}/front/assets/img/pics/img1-large.jpg" alt=""></a></li>
							<li><a href="#"><img src="${pageContext.request.contextPath}/front/assets/img/pics/img2-large.jpg" alt=""></a></li>
							<li><a href="#"><img src="${pageContext.request.contextPath}/front/assets/img/works/img3.jpg" alt=""></a></li>
							<li><a href="#"><img src="${pageContext.request.contextPath}/front/assets/img/people/img2-large.jpg" alt=""></a></li>
						</ul>                    
					</div>
					<!-- END BLOG PHOTOS STREAM -->

					<!-- BEGIN BLOG TAGS -->
					<div class="blog-tags margin-bottom-20">
						<h2>Tags</h2>
						<ul>
							<li><a href="#"><i class="fa fa-tags"></i>OS</a></li>
							<li><a href="#"><i class="fa fa-tags"></i>Metronic</a></li>
							<li><a href="#"><i class="fa fa-tags"></i>Dell</a></li>
							<li><a href="#"><i class="fa fa-tags"></i>Conquer</a></li>
							<li><a href="#"><i class="fa fa-tags"></i>MS</a></li>
							<li><a href="#"><i class="fa fa-tags"></i>Google</a></li>
							<li><a href="#"><i class="fa fa-tags"></i>Keenthemes</a></li>
							<li><a href="#"><i class="fa fa-tags"></i>Twitter</a></li>
						</ul>
					</div>
					<!-- END BLOG TAGS -->
				</div>
				<!-- END RIGHT SIDEBAR -->            
			</div>
			<!-- END BEGIN BLOG -->
		</div>
		<!-- END CONTAINER -->

	</div>
    <!-- END BEGIN PAGE CONTAINER -->  
	
	<!-- footer begin -->
	<jsp:include page="../../common/footer.jsp"></jsp:include>
	<!-- footer end -->
	
    <!-- Load javascripts at bottom, this will reduce page load time -->
    <!-- BEGIN CORE PLUGINS(REQUIRED FOR ALL PAGES) -->
    <!--[if lt IE 9]>
    <script src="assets/plugins/respond.min.js"></script>  
    <![endif]-->  
    <script src="${pageContext.request.contextPath}/front/assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/front/assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/front/assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>      
    <script type="text/javascript" src="${pageContext.request.contextPath}/front/assets/plugins/hover-dropdown.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/front/assets/plugins/back-to-top.js"></script>    
    <!-- END CORE PLUGINS -->

    <!-- BEGIN PAGE LEVEL JAVASCRIPTS(REQUIRED ONLY FOR CURRENT PAGE) -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/front/assets/plugins/fancybox/source/jquery.fancybox.pack.js"></script>  
    <script src="${pageContext.request.contextPath}/front/assets/scripts/app.js"></script>  
    <script type="text/javascript">
        jQuery(document).ready(function() {
            App.init();                      
        });
    </script>
    <!-- END PAGE LEVEL JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>