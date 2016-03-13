/**
 * Created by Administrator on 2016/3/12.
 */
window.onload = function(){
    var contentBoxDom = document.getElementById('content');
    var left = 0;
    var timer = null;
    var src = null;
    timer = setInterval(move, 50);
    contentBoxDom.onmouseenter = function(){
        clearInterval(timer);
    }
    contentBoxDom.onmouseleave = function(){
        timer = setInterval(move, 50);
    }

    contentBoxDom.onclick = function(e){
        if((e.target.nodeName).toLowerCase() == 'span'){
            var span = e.target;
            var divDom = span.parentNode;
            var shadow = divDom.parentNode;
            var imgDom = shadow.previousElementSibling;
            var dataSrc = imgDom.getAttribute('data-src');
            if(imgDom.getAttribute('src') == dataSrc){
                imgDom.setAttribute('src', src);
            }else {
                src = imgDom.getAttribute('src');
                imgDom.setAttribute('src', dataSrc);
            }
        }
    }
    function move() {
        left -= 4;
        contentBoxDom.style.left = left + 'px';
        if (contentBoxDom.style.left == '-200px') {
            var firstChild = contentBoxDom.children[0].cloneNode(true);
            //contentBoxDom.children[0].id = 'child' + new Date().getTime();
            //var id = contentBoxDom.children[0].id;
            contentBoxDom.appendChild(firstChild);
            contentBoxDom.children[0].remove();
            //document.getElementById(id).remove();
            contentBoxDom.style.left = 0 + 'px';
            left = 0;
        }
    }
}
