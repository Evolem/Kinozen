$(document).ready(function () {
    $(".owl-carousel").owlCarousel();
});

$('.owl-carousel').owlCarousel({
    loop: false,
    margin: 10,
    nav: true,
    responsive: {
        0: {
            items: 2
        },
        991: {
            items: 3
        },
        1200: {
            items: 4
        }
    }
})