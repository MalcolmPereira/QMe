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


    });

})();