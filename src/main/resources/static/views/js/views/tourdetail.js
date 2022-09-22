const numberFormat = new Intl.NumberFormat('vi-VN', {
	style: 'currency',
	currency: 'VND',
});

window.onload=function(){
	checkAcc();
    showTourDetail();
  };

function checkAcc(){
	let token = getCookie("token");
	$.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json',
			"Authorization" : token
		},
		type : "GET",
		url : "http://localhost:8081/authenticate" ,
		success : function(check){
			if (check){
				document.querySelector(".no-account").style.display = 'none';
				document.querySelector(".has-account").style.display = 'inline-block';
                if (getCookie('role')=="ROLE_USER"){
					document.querySelector(".has-account a").href = '/userInfo';
				}
				else{
					document.querySelector(".has-account a").href = '/admin/adminInfo';
				}
			}
			else{
				document.querySelector(".no-account").style.display = 'inline-block';
				document.querySelector(".has-account").style.display = 'none';
			}
		},
		error : function(){
			alert("Có lỗi xảy ra!");
		}
	})
}

function showTourDetail(){
    var curl = document.URL;
    var id = curl.split('id=')[1];
    fetch("http://localhost:8081/data/tour/"+id, {
        method: "GET"
    }).then(function(response) {
        return response.json();
    }).then(function(tour) {
        console.log(tour);
        var slideimg=``;
        tour.listimgs.forEach(element => {
            slideimg += `<div class="mySlides">
                            <img src="${element.url}" style="width:100%">
                        </div>`
        });
        slideimg += `<a class="prev" onclick="plusSlides(-1)"></a>
                     <a class="next" onclick="plusSlides(1)"></a>`
        var dots=``;
        for (var i=0 ; i<tour.listimgs.length ; i++){
            dots+=`<span class="dot" onclick="currentSlide(${i+1})"></span>`
        }
        var actlist=``;
        tour.listacts.forEach(element => {
            var clhr = "#collapse"+element.day;
            var cl = "collapse"+element.day;
            var hd = "heading"+element.day;
            actlist += `<div class="panel">
                            <div class="panel-heading" id="${hd}">
                                <h4 class="panel-title">
                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="${clhr}">
                                        <small>Ngày ${element.day}:</small>${element.title}
                                        <span class="icon fa fa-angle-down"></span>
                                    </a>
                                </h4>
                            
                            </div>
                            <div  id="${cl}" class="panel-collapse collapse in" aria-labelledby="${hd}">
                                <div class="panel-body">${element.act}</div>
                            </div>
                        </div>`
        });
        document.querySelector(".slideshow-container").innerHTML = slideimg;
        document.querySelector(".listdot").innerHTML = dots;
        document.querySelector(".head-detail .head-dt-cn").innerHTML = `<div class="row">
                                                                            <div class="col-sm-12">
                                                                                <h1>${tour.tourName}</h1>
                                                                            </div> 
                                                                        </div>`;
        document.querySelector(".tour-description .tour-detail-text").innerHTML = `<p>${tour.tourDescription}</p>`;
        document.querySelector("#accordion").innerHTML = actlist;
        document.querySelector(".book-tour-bar").innerHTML =   `<p class="number_of_date">Tour <span>${tour.days}</span> ngày</p>
                                                                <a href="/book_tour?id=${tour.tourId}" title="" class="awe-btn awe-btn-1 awe-btn-lager">Đặt ngay</a>
                                                                <p class="price-book-now"><span>${numberFormat.format(tour.tourCost)}</span>/người</p>`;
    }).then(function() {
        document.querySelector(".dot:first-child").click();
    });
}

/* ----------Slide---------- */
var slideIndex = 1;

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function currentSlide(n) {
  showSlides(slideIndex = n);
}

function showSlides(n) {
  var i;
  var slides = document.getElementsByClassName("mySlides");
  var dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
      slides[i].style.display = "none";
  }
  for (i = 0; i < dots.length; i++) {
      dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";
  dots[slideIndex-1].className += " active";
}