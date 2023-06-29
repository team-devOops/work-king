window.addEventListener("load",function(){
    console.log("g");

    var btnTest = document.getElementById("btnTest");

    btnTest.onclick = function(){
        console.log("!");
        axios.get('/main', {
                params: {}
              })
              .then(function(response) {
                console.log(response);
              })
              .catch(function(error) {
                console.log(error);
              })
              .finally(function() {
                // 항상 실행되는 영역
                console.log("~!!!");
              });
    };
});