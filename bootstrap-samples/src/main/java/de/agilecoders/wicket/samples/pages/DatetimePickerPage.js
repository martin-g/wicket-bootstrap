
$('.nav a').on('click', function(evt) {
    evt.preventDefault();

    $('section').hide();
    var sectionToShow = $(this).attr('href');
    $(sectionToShow).show();
});
