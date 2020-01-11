$(function () {
    function footerPosition(){
        $("footer").css({
            'bottom': '',
            'position': '',
            'width': ''
        });
        var contentHeight=document.body.scrollHeight,//网页正文高度
            winHeight=window.innerHeight;// 可是窗口高度，不包括浏览器顶部导航栏
        if(!($("#box").outerHeight()>winHeight)){
            console.log('contentHeight->'+contentHeight);
            console.log(!($("#box").outerHeight()>winHeight));
            console.log($("#box").outerHeight());
            //当网页正文高度小于可是窗口高度时，为footer添加类fixed-bottom
            $("footer").css({
                'bottom': '0',
                'position': 'absolute',
                'width': '100%'
            });
        }
    }
    footerPosition();
    $(window).resize(footerPosition);
    window.setTimeout(footerPosition, 100);
});