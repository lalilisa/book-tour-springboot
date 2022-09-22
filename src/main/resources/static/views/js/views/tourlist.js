const numberFormat = new Intl.NumberFormat('vi-VN', {
	style: 'currency',
	currency: 'VND',
});

function showFilter(){
	var e = document.querySelector(".sidebar-cn");
	e.classList.toggle('show-filter');
	console.log("ok filter");
}

function deleteShowFilter(){
	var e = document.querySelector(".sidebar-cn");
	e.classList.remove('show-filter');
	console.log("ok delete filter");
}

fetch("http://localhost:8081/data/category", {
	method: "GET"
}).then(function(response) {
	return response.json();
}).then(function(cate) {
	console.log(cate);
	var content=``;
	cate.forEach(element => {
		content += `<option value="${element.cateId}">${element.cateName}</option>`
	});
	document.querySelector(".select-category").innerHTML = content;
});

window.onload=function(){
	checkAcc();
	document.querySelector(".auto-click").click();
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

function searchbyfilter(pagenum=0){
	deleteShowFilter();
	var regionSelect = document.querySelector(".select-region");
	var	sortSelect = document.querySelector(".select-sort");
	var	cateSelect = document.querySelector(".select-category");
	var region = regionSelect.options[regionSelect.selectedIndex].value;
	var orderby = sortSelect.options[sortSelect.selectedIndex].value;
	var cateid = cateSelect.options[cateSelect.selectedIndex]?cateSelect.options[cateSelect.selectedIndex].value:1;
	console.log(region);
	console.log(orderby);
	console.log(cateid);
	console.log(pagenum);
	var url = "http://localhost:8081/data/pagetour?page="+pagenum+"&region="+region+"&cateid="+cateid+"&orderby="+orderby;
	fetch(url, {
		method: "GET"
	}).then(function(response) {
		return response.json();
	}).then(function(listtour) {
		console.log(listtour);
		var content=``;
		var data = listtour.content;
		data.forEach(element => {
			var link = element.listimgs[0];
			content += `<div class="cruise-item">
							<figure class="cruise-img">
								<a href="/tour_detail?id=${element.tourId}">
									<img src="${link.url}" alt="">
								</a>
							</figure>   
							<div class="cruise-text">
								<div class="cruise-name">
									<a href="/tour_detail?id=${element.tourId}">${element.tourName}</a>
								</div>
								<ul class="ship-port">
									<li>${element.tourDescription}</li>
								</ul>
								<div class="price-box">
									<span class="price night">
										<ins>${element.days}</ins><small> ngày</small>
									</span>
									<span class="price">
										<span style="text-decoration: line-through;">${(element.sale)>0?numberFormat.format(element.tourCost + element.tourCost*element.sale/100):''}</span>
										<ins>${numberFormat.format(element.tourCost)}</ins>
										<span>/khách</span>
									</span>
								</div>
							</div>
						</div> `
		});
		var pagenavigation = ``;
		document.querySelector(".container .tour-list-cn").innerHTML = content;
		for (var i=0 ; i<listtour.totalPages ; i++){
			if (i==listtour.number){
				pagenavigation+=`<li class="page-number current" onclick="searchbyfilter(${i})">
									<a href="#" title="">${i+1}</a></li>`
			}
			else{
				pagenavigation+=`<li class="page-number" onclick="searchbyfilter(${i})">
									<a href="#" title="">${i+1}</a></li>`
			}
		}
		document.querySelector(".page-navigation").innerHTML = pagenavigation;
		document.querySelector(".sidebar-cn .search-result").innerHTML = `<p>Tìm ra <br>
																				<ins>${listtour.totalElements}</ins> <span>tour phù hợp</span>
																			</p>`
	});
}