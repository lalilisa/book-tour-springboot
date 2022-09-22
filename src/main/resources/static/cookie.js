function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for(let i = 0; i <ca.length; i++) {
      let c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }

function setCookie(cname, cvalue) {
    const d = new Date();
    d.setTime(d.getTime() + (60*60*1000));
    let expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
  }

function deleteCookie(){
  document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
  document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
  document.cookie = "role=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}

function authenticate(role, url){
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
        if (check && getCookie("role")==role){
          if (url!=""){
            document.location.href = url;
          }
        }
        else{
          document.location.href = "http://localhost:8081/login";
        }
        let gt = document.cookie;
        console.log(gt);
      },
      error : function(){
        alert("Có lỗi xảy ra!");
      }
  })
}

function logout(){
  deleteCookie();
  document.location.href = "http://localhost:8081/login";
}