let account = document.querySelector('.wrapper');
let content = document.querySelector('.settings__content');

account.addEventListener('click', function (e) {
    if (e.target.className.includes('settings__menu-title')) {
        let menu = document.querySelector('.active');
        menu.className = 'settings__menu-title ';
        e.target.parentElement.className = 'settings__menu-title active';
        if (e.target.textContent == "История просмотра") {

        } else if (e.target.textContent == "Аккаунт") {
            content.innerHTML = '<div class="settings-item">\n' +
                '        <h1>Аккаунт</h1>\n' +
                '        <div class="settings-item__account">\n' +
                '            <div class="settings-item__account-title">Данные аккаунта</div>\n' +
                '            <div class="settings-item__account-content">\n' +
                '                <div class="settings-item__account-item">\n' +
                '                    <div class="settings-item__account-name">E-mail</div>\n' +
                '                    <div class="settings-item__account-data">Тут должна быть почта</div>\n' +
                '                    <div id="E-mail" class="settings-item__account-actions settings-item__disabled">Изменить</div>\n' +
                '                </div>\n' +
                '                <div class="settings-item__account-item">\n' +
                '                    <div class="settings-item__account-name">Пароль</div>\n' +
                '                    <div class="settings-item__account-data">••••••••••••</div>\n' +
                '                    <div id="passwords" class="settings-item__account-actions">Изменить</div>\n' +
                '                </div>\n' +
                '            </div>\n' +
                '        </div>\n' +
                '        <div class="settings-item__account">\n' +
                '            <div class="button m-top-16">Выйти из аккаунта</div>\n' +
                '        </div>';
        }
    } else if (e.target.id.includes('passwords')) {
        let reg = document.querySelector(".wrapper .settings-page-wrapper")
        let div = '<div class="popup">\n' +
            '            <div class="popup__wrapper player-error">\n' +
            '               <form action="#" th:action="@{/profile/change}" th:object="${userPojo}" method="POST">\n' +
            '                <div class="popup__title">Изменить пароль</div>\n' +
            '                <div class="popup__password">' +
            '                    <label for="newPassword"><span>Текущий пароль</span></label>\n' +
            '                    <input id="oldPassword" th:field="*{password}"  name="oldPassword" type="password" class="registration__form-input error"\n' +
            '                           placeholder="••••••••••••" value="">\n' +
            '                    <div class="popup__password-eye">\n' +
            '                        <img src="images/icons/eye.png" name="visibleOldPassword"\n' +
            '                             class="registration__eye-img"></div>\n' +
            '                    <div class="popup__error"></div>\n' +
            '                </div>\n' +
            '                <div class="popup__password">' +
            '                   <label for="newPassword">' +
            '                   <span>Новый пароль</span>' +
            '                   <span>(минимум 6 символов)</span></label>\n' +
            '                    <input id="newPassword" th:field="*{newPassword1}" name="newPassword" type="password" class="registration__form-input "\n' +
            '                           placeholder="••••••••••••" value="">\n' +
            '                    <div class="popup__password-eye">\n' +
            '                        <img src="images/icons/eye.png" name="visibleNewPassword"\n' +
            '                             class="registration__eye-img"></div>\n' +
            '                </div>\n' +
            '                <div class="popup__password">' +
            '                       <label for="newPassword">' +
            '                       <span>Повторите новый пароль</span></label>\n' +
            '                    <input id="confirmPassword" th:field="*{newPassword2}" name="confirmPassword" type="password" class="registration__form-input"\n' +
            '                           placeholder="••••••••••••" value="">\n' +
            '                    <div class="popup__password-eye">\n' +
            '                        <img src="images/icons/eye.png" name="visibleConfirmPassword"\n' +
            '                             class="registration__eye-img">\n' +
            '                    </div>\n' +
            '                </div>\n' +
            '                <div class="btn-group">\n' +
            '                    <button type="button" class="btn btn_block btn btn_primary">Применить</button>\n' +
            '                    <button type="button" class="btn btn_block btn btn_outline">Закрыть</button>\n' +
            '                </div>\n' +
            '               </form>\n' +
            '            </div>\n' +
            '        </div>';
        reg.insertAdjacentHTML('afterend', div);
    } else if (e.target.className.includes('btn btn_block btn btn_outline')) {
        let reg = document.querySelector(".popup");
        reg.remove();
    }
    if (e.target.id.includes('E-mail')) {
        let reg = document.querySelector(".wrapper .settings-page-wrapper")
        let div = '<div class="popup">\n' +
            '        <div class="popup__wrapper player-error">\n' +
            '            <div class="popup__title">Изменить e-mail</div>\n' +
            '            <div class="popup__description">Изменение e-mail возможно только через обращение в техподдержку</div>\n' +
            '            <div class="btn-group">\n' +
            '                <button type="button" class="btn btn_block btn btn_primary">Написать в поддержку</button>\n' +
            '                <button type="button" class="btn btn_block btn btn_outline">Закрыть</button>\n' +
            '            </div>\n' +
            '        </div>\n' +
            '    </div>';
        reg.insertAdjacentHTML('afterend', div);
    }
})

function historyAjax() {
    let url = /profile/;
    $('.settings__content').load(url);
}
