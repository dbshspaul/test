// when the DOM is ready...
//$(document).ready(function () {
//
//    // handle nav selection
//    function selectNav() {
//        console.log('1')
//        $(this)
//            .parents('ul:first')
//                .find('a')
//                    .removeClass('selected')
//                .end()
//            .end()
//            .addClass('selected');
//    }
//
//    $('.navigation').find('a').click(selectNav);
//
//    // go find the navigation link that has this target and select the nav
//    function trigger(data) {
//        console.log('2 ' +data.id)
//        var el = $('.navigation').find('a[href$="' + data.id + '"]').get(0);
//                  
//        console.log('aa '+$('.navigation').find('a[href$="' + data.id + '"]').get(0))
//                  
//        console.log('el'+el)
//                  
//        var gl=(data.id+'.html')
//                  
//        console.log(gl)
//        selectNav.call(gl);
//    }
//
//    if (window.location.id) {
//        console.log('3')
//
//        console.log('33 '+ window.location.hash.substr(1))
//        trigger({ id : window.location.hash.substr(1) });
//    } else {
//        console.log('4')
//        $('ul.navigation a:first').click();
//    }
//});


$( document ).ready(function() {
                    // store url for current page as global variable
                    current_page = location.href.toLowerCase();
                    var pageNameParts = current_page.split('/');
                    
                    // You can look console to check pageNamePart contents
                    // and then remove this for bracket
                    for (var i = 0; i < pageNameParts.length; i++) {
                    console.log(pageNameParts[i]);
                    }
                    
                    // First remove '.selected'
                    $("ul.navigation li.selected").removeClass('selected')
                    
                    if (pageNameParts.length == 3) { // It's not 1 because there is 'http://'
                    $("ul.navigation li#home").addClass('selected');
                    } else {
                    $("ul.navigation li#"+ pageNameParts[4]).addClass('selected');
                    }
});




/*// when the DOM is ready...
$(document).ready(function () {

    var $panels = $('#slider .scrollContainer > div');
    var $container = $('#slider .scrollContainer');

    // if false, we'll float all the panels left and fix the width 
    // of the container
    var horizontal = false;

    // float the panels left if we're going horizontal
    if (horizontal) {
        $panels.css({
            'float' : 'left',
            'position' : 'relative' // IE fix to ensure overflow is hidden
        });

        // calculate a new width for the container (so it holds all panels)
        $container.css('width', $panels[0].offsetWidth * $panels.length);
    }

    // collect the scroll object, at the same time apply the hidden overflow
    // to remove the default scrollbars that will appear
    var $scroll = $('#slider .scroll').css('overflow', 'hidden');

    // apply our left + right buttons
    $scroll
        .before('<img class="scrollButtons left" src="" />')
        .after('<img class="scrollButtons right" src="" />');

    // handle nav selection
    function selectNav() {
        $(this)
            .parents('ul:first')
                .find('a')
                    .removeClass('selected')
                .end()
            .end()
            .addClass('selected');
    }

    $('#slider .navigation').find('a').click(selectNav);

    // go find the navigation link that has this target and select the nav
    function trigger(data) {
        var el = $('#slider .navigation').find('a[href$="' + data.id + '"]').get(0);
        selectNav.call(el);
    }

    if (window.location.hash) {
        trigger({ id : window.location.hash.substr(1) });
    } else {
        $('ul.navigation a:first').click();
    }

    // offset is used to move to *exactly* the right place, since I'm using
    // padding on my example, I need to subtract the amount of padding to
    // the offset.  Try removing this to get a good idea of the effect
    var offset = parseInt((horizontal ? 
        $container.css('paddingTop') : 
        $container.css('paddingLeft')) 
        || 0) * -1;


    var scrollOptions = {
        target: $scroll, // the element that has the overflow

        // can be a selector which will be relative to the target
        items: $panels,

        navigation: '.navigation a',

        // selectors are NOT relative to document, i.e. make sure they're unique
        prev: 'img.left', 
        next: 'img.right',

        // allow the scroll effect to run both directions
        axis: 'xy',

        onAfter: trigger, // our final callback

        offset: offset,

        // duration of the sliding effect
        duration: 500,

        // easing - can be used with the easing plugin: 
        // http://gsgd.co.uk/sandbox/jquery/easing/
        easing: 'swing'
    };

    // apply serialScroll to the slider - we chose this plugin because it 
    // supports// the indexed next and previous scroll along with hooking 
    // in to our navigation.
    $('#slider').serialScroll(scrollOptions);

    // now apply localScroll to hook any other arbitrary links to trigger 
    // the effect
    $.localScroll(scrollOptions);

    // finally, if the URL has a hash, move the slider in to position, 
    // setting the duration to 1 because I don't want it to scroll in the
    // very first page load.  We don't always need this, but it ensures
    // the positioning is absolutely spot on when the pages loads.
    scrollOptions.duration = 1;
    $.localScroll.hash(scrollOptions);

});*/