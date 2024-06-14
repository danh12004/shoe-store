var slides = document.querySelectorAll('.slide');
var counter = 1;
var intervalId;

function slideImage() {
    const newTransformValue = -counter * 100 + '%';
    slides.forEach((slide) => {
        slide.style.transform = 'translateX(' + newTransformValue + ')';
    });
}

function prev() {
    stopSlideInterval();
    counter = (counter - 1 + slides.length) % slides.length;
    slideImage();
    startSlideInterval();
}

function next() {
    stopSlideInterval();
    counter = (counter + 1) % slides.length;
    slideImage();
    startSlideInterval();
}

function startSlideInterval() {
    intervalId = setInterval(next, 6000);
}

function stopSlideInterval() {
    clearInterval(intervalId);
}

startSlideInterval();

function changeImage(element) {
    element.style.backgroundImage = 'url(../img/nike/nam/nike2.png)'; 
}

function resetImage(element) {
    element.style.backgroundImage = 'url(../img/nike/nam/nike1.png)'; 
}

function scrollToFlashSale() {
    var flashSaleElement = document.getElementById('flash-sale');
    if (flashSaleElement) {
        $('html, body').animate({
            scrollTop: $(flashSaleElement).offset().top
        }, 800);
    } 
}

function copyCouponCode(couponCode) {
    var textField = document.createElement('textarea');
    textField.innerText = couponCode;
    document.body.appendChild(textField);
    textField.select();
    document.execCommand('copy');
    textField.remove();
    var copyButton = event.target; 
    copyButton.innerText = 'Đã lưu';
    copyButton.disabled = true;
    setTimeout(function () {
        copyButton.innerText = 'Sao chép';
        copyButton.disabled = false;
    }, 3000);
}

$(document).ready(function () {
    $('.list-img img').click(function () {
        var currentImage = $(this).attr('src');

        $('.modal-body .col-6 > img').attr('src', currentImage);
    });

    $('#expand').on('show.bs.modal', function (event) {
        var triggerElement = $(event.relatedTarget);

        if (!triggerElement.hasClass('list-img') && !triggerElement.parent().hasClass('list-img')) {
            var currentImage = triggerElement.attr('src');

            $('.modal-body .col-6 > img').attr('src', currentImage);
        }
    });

    $('#detail').click(function(e) {
        e.preventDefault();
    });
});

