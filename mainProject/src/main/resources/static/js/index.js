let slides = document.querySelectorAll('.main-slider__wrapper');
let line = document.querySelectorAll('.main-slider__line');
let currentSlide = 0;
let currentLine = 0;
let elem = '<div class="main-slider__line-active" style="animation-duration:10s"></div>';
let slideInterval = setInterval(nextSlide, 10000);
let slideInterval = setInterval(nextLine, 10000);

function nextSlide() {
    slides[currentSlide].className = 'main-slider__wrapper invisible';
    currentSlide = (currentSlide + 1) % slides.length;
    slides[currentSlide].className = 'main-slider__wrapper';
    line[currentSlide].className = 'main-slider__line';

}

function nextLine() {
    currentLine = (currentLine + 1) % line.length;
    if (line[currentLine].childElementCount === 0) {
        line[currentLine].insertAdjacentHTML("afterbegin", elem);
    }
    if (currentLine === 0) {
        line[line.length - 1].innerHTML = "";
    } else {
        line[currentLine - 1].innerHTML = "";
    }
}
