var slides = document.querySelectorAll('.main-slider__wrapper');
var line = document.querySelectorAll('.main-slider__line');
var currentSlide = 0;
var currentLine = 0;
var elem = '<div class="main-slider__line-active" style="animation-duration:10s"></div>';
var slideInterval = setInterval(nextSlide, 10000);
var slideInterval = setInterval(nextLine, 10000);

function nextSlide() {
    slides[currentSlide].className = 'main-slider__wrapper invisible';
    currentSlide = (currentSlide + 1) % slides.length;
    slides[currentSlide].className = 'main-slider__wrapper';
    line[currentSlide].className = 'main-slider__line';

}

function nextLine() {
    currentLine = (currentLine + 1) % line.length;
    if (line[currentLine].childElementCount == 0) {
        line[currentLine].insertAdjacentHTML("afterbegin", elem);
    }
    if (currentLine == 0) {
        line[line.length - 1].innerHTML = "";
    } else {
        line[currentLine - 1].innerHTML = "";
    }
}
