(function () {

    "use strict";

    describe('End to End : QMe Admin Module - Question Management Tests ', function () {

        var EC = protractor.ExpectedConditions;

        it('Perform Login With Valid Admin Credentials, Route to Admin Question Module', function() {

            browser.get('');
            expect(browser.getTitle()).toEqual('QMe Application');
            expect(element(by.id('qmeAppHeader')).getText()).toEqual('QMe Application');
            var userEmail = element(by.model('qmeUserCtrl.userEmail'));
            var userPassword = element(by.model('qmeUserCtrl.userPassword'));
            expect(userEmail.getText()).toEqual('');
            expect(userPassword.getText()).toEqual('');
            expect(element(by.id('emailField')).isDisplayed()).toEqual(true);
            expect(element(by.id('passwordField')).isDisplayed()).toEqual(true);
            expect(element(by.id('signInButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('signInButton')).isEnabled()).toEqual(false);
            expect(element(by.css('.panel-title')).getText()).toEqual('Home Page');

            userEmail.sendKeys('test.admin@gmail.com');
            userPassword.sendKeys('testtest');
            expect(element(by.id('signInButton')).isEnabled()).toEqual(true);
            element(by.id('signInButton')).click();
            expect(element(by.id('emailField')).isDisplayed()).toEqual(false);
            expect(element(by.id('passwordField')).isDisplayed()).toEqual(false);
            expect(element(by.id('registerButton')).isDisplayed()).toEqual(false);
            expect(element(by.id('forgotButton')).isDisplayed()).toEqual(false);
            expect(element(by.id('qmeAppHeader')).getText()).toEqual('QMe Application');
            expect(element(by.id('loginName')).getText()).toEqual('Test Admin');
            expect(element(by.id('profileLinkId')).isEnabled()).toBe(true);
            expect(element(by.id('logoutLinkId')).isEnabled()).toBe(true);

            expect(element(by.id('homeNavLinkId')).isDisplayed()).toEqual(true);
            expect(element(by.id('homeNavLinkId')).isEnabled()).toEqual(true);
            expect(element(by.id('usersNavLinkId')).isDisplayed()).toEqual(true);
            expect(element(by.id('usersNavLinkId')).isEnabled()).toEqual(true);
            expect(element(by.id('categoriesNavLinkId')).isDisplayed()).toEqual(true);
            expect(element(by.id('categoriesNavLinkId')).isEnabled()).toEqual(true);
            expect(element(by.id('questionsNavLinkId')).isDisplayed()).toEqual(true);
            expect(element(by.id('questionsNavLinkId')).isEnabled()).toEqual(true);
            expect(element(by.id('quizzesNavLinkId')).isDisplayed()).toEqual(true);
            expect(element(by.id('quizzesNavLinkId')).isEnabled()).toEqual(true);

            element(by.id('questionsNavLinkId')).click();
            browser.wait(EC.visibilityOf($('#questionHeaderId')), 8000);

            element(by.id('userNameMenuId')).click();
            element(by.id('logoutLinkId')).click();
        });

    });


})();