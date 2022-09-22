const numberFormat = new Intl.NumberFormat('vi-VN', {
	style: 'currency',
	currency: 'VND',
});

// Hiển thị danh sách hot sale
fetch("http://localhost:8081/data/hotsale", {
	method: "GET"
}).then(function(response) {
	return response.json();
}).then(function(sale) {
	console.log(sale);
	var content=``;
	sale.forEach(element => {
		var link = element.listimgs[0];
		content += `<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4" >
						<div class="sales-item">
							<figure class="home-sales-img">
								<a href="/tour_detail?id=${element.tourId}" title="">
									<img src="${link.url}" alt="">
								</a>
								<figcaption>
									Giảm<br> <span>${element.sale}</span>%
								</figcaption>
							</figure>
							<div class="home-sales-text">
								<div class="home-sales-name-places">
									<div class="home-sales-name">
										<a href="/tour_detail?id=${element.tourId}" title="">${element.tourName}</a>
									</div>
								</div>
								<hr class="hr">
								<div class="price-box">
									<span class="price old-price" style="text-decoration: line-through;">${(element.sale)>0?numberFormat.format(element.tourCost + element.tourCost*element.sale/100):''}</span>
									<span class="price special-price">${numberFormat.format(element.tourCost)}<small>/khách</small></span>
								</div>
							</div>
						</div>
					</div>`
	});
	document.querySelector(".sales .container .sales-cn .row").innerHTML = content;
});

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

// Tour theo miền
window.onload=function(){
	checkAcc();
	document.querySelector(".auto-click").click();
  };
function getbyregion(rg){
	fetch("http://localhost:8081/data/region?name="+rg+"&limit=6", {
		method: "GET"
	}).then(function(response) {
		return response.json();
	}).then(function(tours) {
		console.log(tours);
		var content=``;
		tours.forEach(element => {
			var link = element.listimgs[0];
			content += `<div class="col-xs-6 col-sm-4 col-md-6 col-lg-4">
							<div class="destinations-item ">
								<div class="destinations-text">
									<div class="destinations-name">
										<a href="/tour_detail?id=${element.tourId}" title="">${element.tourName}</a>
									</div>
								</div>
								<figure class="destinations-img">
									<a href="/tour_detail?id=${element.tourId}" title="">
										<img src="${link.url}" alt="">
									</a>
								</figure>
							</div>
						</div>`
		});
		document.querySelector(".destinations-grid .tab-pane").innerHTML = content;
	});
}