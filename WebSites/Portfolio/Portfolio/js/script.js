window.addEventListener('scroll', reveal);

function reveal() {
    let reveals = document.querySelectorAll('.content');

    for (let i = 0; i < reveals.length; i++) {
        if (reveals[i].getBoundingClientRect().top < window.innerHeight - 150 &&
            reveals[i].getBoundingClientRect().bottom > 0) {
            reveals[i].classList.add('active');
        } else {
            reveals[i].classList.remove('active');
        }
    }
}
window.onload = function () {
    let contents = document.querySelectorAll('.content');
    contents[0].classList.add('active');
}