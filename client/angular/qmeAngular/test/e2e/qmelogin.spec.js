(function () {

	"use strict";

    describe('End to End : QMe Login Tests ', function () {

        var EC = protractor.ExpectedConditions;

        it('Should have default elements on home page', function() {
            browser.get('');
            expect(browser.getTitle()).toEqual('QMe Application');
            expect(element(by.id('qmeAppHeader')).getText()).toEqual('QMe Application');
            expect(element(by.model('qmeUserCtrl.userEmail')).getText()).toEqual('');
            expect(element(by.model('qmeUserCtrl.userPassword')).getText()).toEqual('');
            expect(element(by.id('signInButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('registerButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('forgotButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('signInButton')).isEnabled()).toEqual(false);
            expect(element(by.id('registerButton')).isEnabled()).toEqual(true);
            expect(element(by.id('forgotButton')).isEnabled()).toEqual(true);
            expect(element(by.css('.panel-title')).getText()).toEqual('Home Page');
        });

        it('Should Route to User Staging UI and Back to Home', function() {
            browser.get('');
            expect(element(by.id('registerButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('registerButton')).isEnabled()).toEqual(true);

            element(by.id('registerButton')).click();
            expect(element(by.id('qmeAppHeader')).getText()).toEqual('QMe Application');
            expect(element(by.css('.panel-title')).getText()).toEqual('Register New User');
            expect(element(by.id('homeButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('homeButton')).isEnabled()).toEqual(true);
            expect(element(by.id('submitRegisterButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('resetButton')).isEnabled()).toEqual(true);
            expect(element(by.model('qmeUserCtrl.userEmail')).getText()).toEqual('');
            expect(element(by.model('qmeUserCtrl.userName')).getText()).toEqual('');
            expect(element(by.model('qmeUserCtrl.userPassword')).getText()).toEqual('');
            expect(element(by.model('qmeUserCtrl.userPassword')).getText()).toEqual('');
            expect(element(by.model('qmeUserCtrl.userFirstName')).getText()).toEqual('');
            expect(element(by.model('qmeUserCtrl.userLastName')).getText()).toEqual('');

            element(by.id('homeButton')).click();
            expect(element(by.id('qmeAppHeader')).getText()).toEqual('QMe Application');
            expect(element(by.model('qmeUserCtrl.userEmail')).getText()).toEqual('');
            expect(element(by.model('qmeUserCtrl.userPassword')).getText()).toEqual('');
            expect(element(by.id('signInButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('registerButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('forgotButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('signInButton')).isEnabled()).toEqual(false);
            expect(element(by.id('registerButton')).isEnabled()).toEqual(true);
            expect(element(by.id('forgotButton')).isEnabled()).toEqual(true);
            expect(element(by.css('.panel-title')).getText()).toEqual('Home Page');
        });

        it('Should Route to User Forgot Password UI and Back to Home', function() {
            browser.get('');
            expect(element(by.id('forgotButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('forgotButton')).isEnabled()).toEqual(true);

            element(by.id('forgotButton')).click();
            expect(element(by.id('qmeAppHeader')).getText()).toEqual('QMe Application');
            expect(element(by.css('.panel-title')).getText()).toEqual('Reset Password');
            expect(element(by.id('homeButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('homeButton')).isEnabled()).toEqual(true);
            expect(element(by.id('submitResetButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('resetButton')).isEnabled()).toEqual(true);
            expect(element(by.model('qmeUserCtrl.userEmail')).getText()).toEqual('');

            element(by.id('homeButton')).click();
            expect(element(by.id('qmeAppHeader')).getText()).toEqual('QMe Application');
            expect(element(by.model('qmeUserCtrl.userEmail')).getText()).toEqual('');
            expect(element(by.model('qmeUserCtrl.userPassword')).getText()).toEqual('');
            expect(element(by.id('signInButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('registerButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('forgotButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('signInButton')).isEnabled()).toEqual(false);
            expect(element(by.id('registerButton')).isEnabled()).toEqual(true);
            expect(element(by.id('forgotButton')).isEnabled()).toEqual(true);
            expect(element(by.css('.panel-title')).getText()).toEqual('Home Page');
        });

        it('Perform Login With Valid User Credentials, Route to User Profile and Then Log Out', function() {
            browser.get('');
            expect(browser.getTitle()).toEqual('QMe Application');
            expect(element(by.id('qmeAppHeader')).getText()).toEqual('QMe Application');
            var userEmail = element(by.model('qmeUserCtrl.userEmail'));
            var userPassword = element(by.model('qmeUserCtrl.userPassword'));
            expect(userEmail.getText()).toEqual('');
            expect(userPassword.getText()).toEqual('');
            expect(element(by.id('emailField')).isDisplayed()).toEqual(true)
            expect(element(by.id('passwordField')).isDisplayed()).toEqual(true)
            expect(element(by.id('signInButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('signInButton')).isEnabled()).toEqual(false);
            expect(element(by.css('.panel-title')).getText()).toEqual('Home Page');

            userEmail.sendKeys('test.admin@gmail.com');
            userPassword.sendKeys('testtest');
            expect(element(by.id('signInButton')).isEnabled()).toEqual(true);
            element(by.id('signInButton')).click();
            expect(element(by.id('emailField')).isDisplayed()).toEqual(false)
            expect(element(by.id('passwordField')).isDisplayed()).toEqual(false)
            expect(element(by.id('registerButton')).isDisplayed()).toEqual(false);
            expect(element(by.id('forgotButton')).isDisplayed()).toEqual(false);
            expect(element(by.id('qmeAppHeader')).getText()).toEqual('QMe Application');
            expect(element(by.id('loginName')).getText()).toEqual('Test Admin');
            expect(element(by.id('profileLinkId')).isEnabled()).toBe(true);
            expect(element(by.id('logoutLinkId')).isEnabled()).toBe(true);

            element(by.id('userNameMenuId')).click();
            element(by.id('profileLinkId')).click();
            expect(element(by.css('.panel-title')).getText()).toEqual('User Profile');
            expect(element(by.id('userEmail')).isDisplayed()).toEqual(true);
            expect(element(by.id('userEmail')).isEnabled()).toEqual(false);
            expect(element(by.id('userName')).isDisplayed()).toEqual(true);
            expect(element(by.id('userName')).isEnabled()).toEqual(false);
            expect(element(by.id('userPassword')).isDisplayed()).toEqual(true);
            expect(element(by.id('userFirstName')).isDisplayed()).toEqual(true);
            expect(element(by.id('userLastName')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitUpdateProfileButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitUpdateProfileButton')).isEnabled()).toEqual(true);
            expect(element(by.id('cancelButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('cancelButton')).isEnabled()).toEqual(true);
            expect(element(by.id('changeButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('changeButton')).isEnabled()).toEqual(true);

            element(by.id('changeButton')).click();
            browser.wait(EC.visibilityOf($('#passwordModalLabel')), 5000);
            expect($('.modal-dialog').element(by.id('passwordModalLabel')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('passwordModalLabel')).getText()).toEqual('Change Password');
            var userCurrentPassword = $('.modal-dialog').element(by.id('currentPassword'));
            var userNewPassword     = $('.modal-dialog').element(by.id('currentPassword'));
            var userConfirmPassword     = $('.modal-dialog').element(by.id('confirmPassword'));
            expect(userCurrentPassword.isDisplayed()).toEqual(true);
            expect(userCurrentPassword.isEnabled()).toEqual(true);
            expect(userNewPassword.isDisplayed()).toEqual(true);
            expect(userNewPassword.isEnabled()).toEqual(true);
            expect(userConfirmPassword.isDisplayed()).toEqual(true);
            expect(userConfirmPassword.isEnabled()).toEqual(true);
            expect($('.modal-dialog').element(by.id('cancelButtonId')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('cancelButtonId')).isEnabled()).toEqual(true);
            expect($('.modal-dialog').element(by.id('saveButtonId')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('saveButtonId')).isEnabled()).toEqual(false);
            expect($('.modal-dialog').element(by.id('passwordMisMatchId')).isDisplayed()).toEqual(false);
            userCurrentPassword.sendKeys('somepassword');
            userNewPassword.sendKeys('test1');
            userConfirmPassword.sendKeys('test2');
            userCurrentPassword.click();
            expect($('.modal-dialog').element(by.id('cancelButtonId')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('cancelButtonId')).isEnabled()).toEqual(true);
            expect($('.modal-dialog').element(by.id('saveButtonId')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('saveButtonId')).isEnabled()).toEqual(false);
            expect($('.modal-dialog').element(by.id('passwordMisMatchId')).isDisplayed()).toEqual(true);
            userCurrentPassword.sendKeys('somepassword');
            userNewPassword.sendKeys('test1');
            userConfirmPassword.sendKeys('test1');
            userCurrentPassword.click();
            expect($('.modal-dialog').element(by.id('cancelButtonId')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('cancelButtonId')).isEnabled()).toEqual(true);
            expect($('.modal-dialog').element(by.id('saveButtonId')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('saveButtonId')).isEnabled()).toEqual(true);
            expect($('.modal-dialog').element(by.id('passwordMisMatchId')).isDisplayed()).toEqual(false);
            $('.modal-dialog').element(by.id('saveButtonId')).click();

        });

    });

})();