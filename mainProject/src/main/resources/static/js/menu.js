let next = document.querySelector("#__next");
let wrapper = document.querySelector("#__next > div.wrapper > div");
let menu = document.querySelector("#__next > div.wrapper > div > div.menu");

next.addEventListener('click', function (e) {
    console.log(e.target);
    if (e.target.className.includes('menu__burger')) {
        console.log(e.target);
        wrapper.className = 'wrapper overlayed';
        menu.className = 'menu menu_opened';
        let accountWrapper = '<div class="account-wrapper">\n' +
            '        <div class="account">\n' +
            '            <img src="images/close.png" class="account__close">\n' +
            '            <div class="account__profiles">\n' +
            '                <div class="account__profile active">\n' +
            '                    <div class="profile active increased">' +
            '                       <img src="" class="profile__img">\n' +
            '                        <div class="profile__name">Мой профиль</div>\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '                <div class="profile account__profile new">' +
            '                    <img src="" class="profile__img">\n' +
            '                    <div class="profile__name">Новый профиль</div>\n' +
            '                </div>\n' +
            '            </div>\n' +
            '            <div class="account__links">\n' +
            '                <a class="account__link" href="#">Избранное</a>\n' +
            '                <a class="account__link" href="#">Истор просмотров</a>\n' +
            '                <a class="account__link" href="/kinozen/profile">Настройки</a>\n' +
            '                <div class="account__link logout">Выйти из аккаунта</div>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>';
        menu.insertAdjacentHTML('beforeend', accountWrapper);
    } else if (e.target.className.includes('account__close')) {
        let accountWrapper = document.querySelector('.account-wrapper');
        accountWrapper.remove();
    }
})