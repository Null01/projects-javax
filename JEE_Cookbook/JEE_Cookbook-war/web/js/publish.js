jQuery.each(jQuery('textarea[data-autoresize]'), function () {
    var offset = this.offsetHeight - this.clientHeight;

    var resizeTextarea = function (el) {
        jQuery(el).css('height', 'auto').css('height', el.scrollHeight + offset);
    };
    jQuery(this).on('keyup input', function () {
        resizeTextarea(this);
    }).removeAttr('data-autoresize');
});
/*
$(document).ready(
        function () {
            setInterval(function () {
                $("#comments-include").load('publishs-comments.jsp').fadeIn("current-date");
            }, 1000);
        });
*/