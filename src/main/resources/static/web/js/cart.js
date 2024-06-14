$(document).ready(function () {
    $(".content .category .list-menu > li .heading").click(function () {
        var $currentHeading = $(this);
        $currentHeading.parent().toggleClass("active");
        var $subListMenu = $currentHeading.siblings('.sub-list-menu');
        $subListMenu.slideToggle();
        if ($currentHeading.parent().hasClass('active')) {
            $currentHeading.find('span').text('-');
        } else {
            $currentHeading.find('span').text('+');
        }
    });

    var addToCartAPI = '/api/cart/add';
    var getCartAPI = '/api/cart';
    var removeFromCartAPI = '/api/cart/remove';

    $.ajax({
        url: getCartAPI,
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: function(result) {
            if (result.result && result.result.cartItemResponseList && result.result.cartItemResponseList.length > 0) {
                updateCart(result.result.cartItemResponseList);
                updateOrderSummary(result.result.cartItemResponseList)
            } else {
                $('#no-items-message').show();
            }
        },
        error: function(xhr, status, error) {
            console.error('Lỗi khi lấy dữ liệu giỏ hàng:', error);
        }
    });

    function updateCart(cartItems) {
        $('#cart-items').empty();
        var totalMoney = 0;

        if (cartItems.length > 0) {
            $('#no-items-message').hide();
            cartItems.forEach(function(item) {
                var totalPrice = item.productResponse.price * item.quantity;
                var row = '<tr>' +
                    '<td>' +
                        '<div class="info d-flex">' +
                            '<div class="img">' +
                                '<img src="/uploads/' + item.productResponse.image + '" alt="">' +
                            '</div>' +
                            '<p>' + item.productResponse.productName + '</p>' +
                        '</div>' +
                    '</td>' +
                    '<td>' +
                        '<p class="price m-0 d-flex align-items-center">' + item.productResponse.price.toLocaleString('vi-VN') + ' VNĐ</p>' +
                    '</td>' +
                    '<td>' +
                        '<div class="quantity">' +
                            '<div class="input-number-product">' +
                                '<button style="left: 0px; right: auto;" class="subtraction" data-product-id="' + item.productResponse.id + '">-</button>' +
                                '<input type="text" value="' + item.quantity + '">' +
                                '<button class="add" data-product-id="' + item.productResponse.id + '">+</button>' +
                            '</div>' +
                        '</div>' +
                    '</td>' +
                    '<td>' + totalPrice.toLocaleString('vi-VN') + ' VNĐ</td>' +
                '</tr>';
                $('#cart-items').append(row);
            });
        } else {
            $('#no-items-message').show();
        }

        $('.total-money p').text(totalMoney.toLocaleString('vi-VN') + ' VNĐ');
    }

    function updateOrderSummary(cartItems) {
        var totalItems = 0;
        var totalMoney = 0;
        $('#order-detail-body').empty();

        if (cartItems.length > 0) {
            cartItems.forEach(function(item) {
                totalItems += item.quantity;
                var totalPrice = item.productResponse.price * item.quantity;
                totalMoney += totalPrice;

                var productHtml =
                '<div class="product d-flex justify-content-between align-items-center">' +
                    '<p class="name">' + item.productResponse.productName + '<span>x' + item.quantity + '</span></p>' +
                    '<p class="price">' + totalPrice.toLocaleString('vi-VN') + ' VNĐ</p>'
                '</div>'

                $('#order-detail-body').append(productHtml);
            });

            $('#total-money').text(totalMoney.toLocaleString('vi-VN') + ' VNĐ');
        }
    }

    $(document).on('click', '.input-number-product .add', function(e) {
        e.preventDefault();
        var productId = $(this).data('product-id');
        var quantity = 1;
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
                updateOrderSummary(result.result.cartItemResponseList)
            },
            error: function(xhr, status, error) {
                alert('Lỗi');
            }
        });
    });

    $(document).on('click', '.input-number-product .subtraction', function(e) {
        e.preventDefault();
        var productId = $(this).data('product-id');
        var data = {
            productId: productId,
        }

        $.ajax({
            url: removeFromCartAPI,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function(result) {
                if (result.result && result.result.cartItemResponseList && result.result.cartItemResponseList.length > 0) {
                    updateCart(result.result.cartItemResponseList);
                    updateOrderSummary(result.result.cartItemResponseList)
                } else {
                    $('#no-items-message').show();
                }
            },
            error: function(xhr, status, error) {
                alert('Lỗi');
            }
        });
    });
});