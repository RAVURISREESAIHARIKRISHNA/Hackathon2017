var form = document.getElementById("for");
form.onsubmit = function(){
    form.target = '_self';
};

document.getElementById('1').onclick = function(){
    form.target='/postissue.html';
    form.submit();
};

document.getElementById('2').onclick = function(){
    form.target='';
    form.submit();
};
