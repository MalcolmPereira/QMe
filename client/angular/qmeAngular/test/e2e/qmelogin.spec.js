(function () {

	"use strict";

    describe('End to End : QMe Login Tests ', function () {

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
    });

})();