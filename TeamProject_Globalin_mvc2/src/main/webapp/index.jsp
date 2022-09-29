<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@700&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/1fd422b08e.js"
	crossorigin="anonymous"></script>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200;300&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Homemade+Apple|Old+Standard+TT:700|Share+Tech+Mono"
	rel="stylesheet">
<title>Document</title>
<link rel="stylesheet" href="frontEnd/css/index.css">
<link rel="stylesheet" href="resources/css/reset.css">
</head>
<body>
	<ul>
		<img class="logoImg" src="image/logoImg.png" alt="">
		<li class="title"><a class="rebake" href="#">RE:<span>bake</span></a>

		</li>

		<li><a href="#">Sign in</a></li>
		<li><a href="#">About</a></li>
		<li><a href="#">Recommend</a></li>
		<li><a href="#">Contact Us</a></li>


		<div class="tel_email">
			<span>TEL</span> : +81 02 519 3119 <br /> <span>Email : qpq</span>85@<span>gmail.com</span>
		</div>
		<div class="socialImo">
			<i class="fa-brands fa-facebook"></i> <i class="fa-brands fa-twitter"></i>
			<i class="fa-brands fa-instagram"></i>
		</div>
	</ul>

	<div class="mainImg">
		<div class="mainSentence">
			<p>
				Let's find <span>local bakerys</span> with us!
			</p>
		</div>
		<img class="mainSlides" src="image/main1.jpeg" alt=""> <img
			class="mainSlides" src="image/main4.jpeg" alt=""> <img
			class="mainSlides" src="image/main6.jpeg" alt=""> <img
			class="mainSlides" src="image/main8.jpeg" alt="">

		<div class="clickhere">
			<a href="ChoosePage.html"></a> Click here
		</div>
	</div>
	<script>
      let slideIndex = 0;

      const x = document.getElementsByClassName("mainSlides");
      for(let i = 0; i < x.length; ++i){
            x[i].style.display = "none";
      }
      slideShow();
      
      function slideShow(){
          x[Math.max(slideIndex-1, 0)].style.display = 'none';
          slideIndex++;
          if(slideIndex > x.length){
              slideIndex = 1;
          }
          x[slideIndex-1].style.display = "block";
          setTimeout(slideShow, 4000);
      }
      </script>

</body>
<script type="text/javascript" src="./resources/js/Map.js"></script>

</html>
