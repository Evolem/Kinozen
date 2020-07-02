let login = document.querySelectorAll('.registration__form-switcher');

for (let add of login) {
    add.addEventListener('click', function (e) {
        if (e.target.className.includes('registration__form-switcher_item')) {
            let loginActive = document.querySelector('.active');
            loginActive.className = 'registration__form-switcher_item ';
            e.target.className = 'registration__form-switcher_item active';
            let inputForm = document.querySelector('.registration__form-input_wrapper');
            if (e.target.textContent == "E-mail") {
                inputForm.innerHTML = '<input type="email" value="" class="registration__form-input" placeholder="E-mail"/>';
            } else {
                inputForm.innerHTML = '<input type="text" name="username" class="registration__form-input" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Введите логин"/>';
            }
        }
    })
}