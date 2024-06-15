$(document).ready(function () {
    $('.dropdown-toggle-wrapper').click(function(event) {
        event.stopPropagation();
        $(this).closest('.nav-link').next('.dropdown-menu').toggleClass('show');
    });

    $(document).click(function(event) {
        if (!$(event.target).closest('.dropdown-menu').length && !$(event.target).closest('.dropdown-toggle-wrapper').length) {
            $('.dropdown-menu').removeClass('show');
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
                $('#noItemsMessage').hide();
                $('.list-cart, .total-money, .payment').show();
                updateCart(result.result.cartItemResponseList);
            } else {
                $('#noItemsMessage').show();
                $('.list-cart, .total-money, .payment').hide();
            }
        },
        error: function(xhr, status, error) {
            console.error('Lỗi khi lấy dữ liệu giỏ hàng:', error);
        }
    });

    $('.icon.add-to-cart').on('click', function(e) {
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
                updateCart(result.result.cartItemResponseList)
            },
            error: function(xhr, status, error) {
                alert('Lỗi');
            }
        });
    });

    $(document).on('click', '.quantity-item .add', function(e) {
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
            },
            error: function(xhr, status, error) {
                alert('Lỗi');
            }
        });
    });

    $(document).on('click', '.quantity-item .subtraction', function(e) {
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
                    $('#noItemsMessage').hide();
                    $('.list-cart, .total-money, .payment').show();
                    updateCart(result.result.cartItemResponseList);
                } else {
                    $('#noItemsMessage').show();
                    $('.list-cart, .total-money, .payment').hide();
                }
            },
            error: function(xhr, status, error) {
                alert('Lỗi');
            }
        });
    });
});

function updateCart(cartItems) {
    var $cartList = $('#cartItemsList');
    $cartList.empty();

    cartItems.forEach(function(cartItem) {
        var $cartItem = $('<li class="cart-item">' +
            '<a href="">' +
                '<img src="/uploads/' + cartItem.productResponse.image + '" alt="">' +
                '<div class="item">' +
                    '<p class="name">' + cartItem.productResponse.productName + '</p>' +
                    '<p class="price">Giá: ' + cartItem.productResponse.price.toLocaleString('vi-VN') + ' VNĐ</p>' +
                    '<div class="quantity-item">' +
                        '<span class="subtraction" data-product-id="' + cartItem.productResponse.id + '">-</span>' +
                        '<span class="quantity-product">' + cartItem.quantity + '</span>' +
                        '<span class="add" data-product-id="' + cartItem.productResponse.id + '">+</span>' +
                    '</div>' +
                '</div>' +
            '</a>' +
        '</li>');

        $cartList.append($cartItem);
    });

    updateTotal();
}

function calculateTotal() {
    var totalAmount = 0;
    $('.cart-item').each(function() {
        var price = parseFloat($(this).find('.price').text().replace('Giá: ', '').replace('VNĐ', '').replace(/\./g, '').replace(',', '.'));
        var quantity = parseInt($(this).find('.quantity-product').text());
        totalAmount += price * quantity;
    });
    return totalAmount;
}

function updateTotal() {
    var totalAmount = calculateTotal();
    $('#totalAmount').text(totalAmount.toLocaleString('vi-VN') + ' VNĐ');
}