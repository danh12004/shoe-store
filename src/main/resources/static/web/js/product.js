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

    $('.input-group input, .input-group label').click(function() {
         $(this).closest('a')[0].click();
    });

    var getDetailProduct = '/api/product/detail';

    $(document).on('click', '.detail-product', function(e) {
        e.preventDefault();
        var productId = $(this).data('product-id');
        var data = {
            id : productId,
        }

        $.ajax({
            url: getDetailProduct,
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function(result) {
                showModalDetailProduct(result.result)
            },
            error: function(xhr, status, error) {
                alert('Lỗi');
            }
        })
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
                $('#expand').modal('hide');
            },
            error: function(xhr, status, error) {
                alert('Lỗi');
            }
        });
    });

     var urlParams = new URLSearchParams(window.location.search);
     var sort = urlParams.get('sort');
     if (sort) {
         $('#sort-select').val(sort.toLowerCase());
     }

    $('#sort-select').on('change', function() {
        var sortValue = $(this).val();
        if (sortValue === 'default') {
            window.location.href = '/product';
        } else {
            window.location.href = '/product?sort=' + sortValue.toLowerCase();
        }
    });
});

function showModalDetailProduct(product) {
    var detailProduct =
        '<div class="col-6 d-flex flex-column">' +
            '<img src="/uploads/' + product.image + '" alt="">' +
            '<div class="list-img d-flex">' +
                '<div class="row mt-4">' +
                    '<div class="col-3">' +
                        '<img src="/web/img/nike/male/nike1.png" alt="" style="width: 100%;">' +
                    '</div>' +
                    '<div class="col-3">' +
                        '<img src="/web/img/nike/male/nike1.png" alt="" style="width: 100%;">' +
                    '</div>' +
                    '<div class="col-3">' +
                        '<img src="/web/img/nike/male/nike1.png" alt="" style="width: 100%;">' +
                    '</div>' +
                    '<div class="col-3">' +
                        '<img src="/web/img/nike/male/nike1.png" alt="" style="width: 100%;">' +
                    '</div>' +
                '</div>' +
            '</div>' +
        '</div>' +
        '<div class="col-6 mt-3">' +
            '<h3 class="name" style="font-weight: bold;">' + product.productName + '</h3>' +
            '<div class="infomation d-flex align-items-center">' +
                '<p style="font-size: 12px; margin: 0;">Thương hiệu: <span style="color: var(--primary-color); font-size: 12px;">'+ product.categoryName +'</span></p>' +
                '<span style="padding: 0 20px;">|</span>' +
                '<p style="font-size: 12px; margin: 0;">Mã sản phẩm: <span id="product-id"' +
                        'style="color: var(--primary-color);">'+ product.id +'</span></p>' +
            '</div>' +
            '<p class="price mt-3" style="font-weight: bold; font-size: 20px;">'+ product.price.toLocaleString('vi-VN') + ' VNĐ</p>' +
            '<div class="color">' +
                '<p style="margin: 0;">Màu sắc:</p>' +
                '<div class="list-color d-flex">' +
                    '<div class="box m-2"' +
                         'style="width: 23px; height: 23px; background-color: red; border-radius: 50%; cursor: pointer;"></div>' +
                    '<div class="box m-2"' +
                         'style="width: 23px; height: 23px; background-color: orange; border-radius: 50%; cursor: pointer;"></div>' +
                    '<div class="box m-2"' +
                         'style="width: 23px; height: 23px; background-color: blue; border-radius: 50%; cursor: pointer;"></div>' +
                    '<div class="box m-2"' +
                         'style="width: 23px; height: 23px; background-color: grey; border-radius: 50%; cursor: pointer;"></div>' +
                    '<div class="box m-2"' +
                         'style="width: 23px; height: 23px; background-color: black; border-radius: 50%; cursor: pointer;"></div>' +
                    '<div class="box m-2"' +
                         'style="width: 23px; height: 23px; background-color: white; border-radius: 50%; cursor: pointer;"></div>' +
                '</div>' +
            '</div>' +
            '<div class="quantity" style="margin: 10px 0; width: 130px; margin-right: 10px; position: relative;">' +
                '<p style="margin: 0;">Số lượng:</p>' +
                '<div class="input-number-product mt-3">' +
                    '<button style="left: 0px; right: auto;" class="subtraction">-</button>' +
                    '<input type="text" class="quantity-product" value="1" style="outline: none;">' +
                    '<button class="add">+</button>' +
                '</div>' +
            '</div>' +
            '<button class="add-cart mt-3">Thêm vào giỏ hàng</button>' +
            '<div class="promotion">' +
                '<div class="promotion-head d-flex align-items-center">' +
                    '<i class="fa-solid fa-gift"></i>' +
                    '<h3>Khuyến mãi và ưu đãi</h3>' +
                '</div>' +
                '<ul class="promotion-box">' +
                    '<li>Đồng giá Ship toàn quốc 30.000đ</li>' +
                    '<li>Hỗ trợ 100.000 phí ship cho đơn hàng từ 1.000.000đ</li>' +
                    '<li>Miễn phí ship đơn hàng từ 2.000.000đ</li>' +
                    '<li>Đổi trả trong 30 ngày nếu sản phẩm lỗi bất kì</li>' +
                '</ul>' +
            '</div>' +
        '</div>';

        $('#detail-product').empty();
        $('#detail-product').append(detailProduct);
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