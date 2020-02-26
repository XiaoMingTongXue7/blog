function footerPosition(){
    $("footer").css({
        'bottom': '',
        'position': '',
        'width': ''
    });
    console.log(($("#box").outerHeight()<window.innerHeight));
    console.log('正文高度'+$("#box").outerHeight());
    console.log('窗口高度'+window.innerHeight);
    if(($("#box").outerHeight()<window.innerHeight)){
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
$("#newblog-container").load("/*[[@{/footer/newblog}]]*/");