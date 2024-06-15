$(document).ready(function () {
    $('.list-img img').click(function () {
        var currentImage = $(this).attr('src');

        $('.col-6 > img').attr('src', currentImage);
    });

    $('#expand').on('show.bs.modal', function (event) {
        var triggerElement = $(event.relatedTarget);

        if (!triggerElement.hasClass('list-img') && !triggerElement.parent().hasClass('list-img')) {
            var currentImage = triggerElement.attr('src');

            $('.col-6 > img').attr('src', currentImage);
        }
    });

    $('.description .head i').click(function () {
        $(this).toggleClass('fa-chevron-down fa-chevron-up');
        $(this).closest('.description').find('.body').slideToggle();
    });

    $(document).on('click', '.input-number-product .subtraction', function() {
        let quantityInput = $(this).siblings('.quantity-product');
        let currentValue = parseInt(quantityInput.val());
        if (isNaN(currentValue) || currentValue <= 1) {
            quantityInput.val(1);
        } else {
            quantityInput.val(currentValue - 1);
        }
    })

    $(document).on('click', '.input-number-product .add', function() {
        let quantityInput = $(this).siblings('.quantity-product');
        let currentValue = parseInt(quantityInput.val());
        if (isNaN(currentValue)) {
            quantityInput.val(1);
        } else {
            quantityInput.val(currentValue + 1);
        }
    })

    var addToCartAPI = '/api/cart/add';

    $(document).on('click', '.add-cart', function() {
        var productId = $('#product-id').text();
        var quantity = parseInt($('.input-number-product .quantity-product').val());
        var data = {
            productId: productId,
            quantity: quantity
        }

        $.ajax({
            url: addToCartAPI,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function(result) {
                updateCart(result.result.cartItemResponseList);
            },
            error: function(xhr, status, error) {
                alert('Lỗi');
            }
        });
    });
});