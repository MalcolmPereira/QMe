(function () {

    'use strict';

    describe('End to End : QMe Admin Module - Category Management Tests ', function () {

        var EC = protractor.ExpectedConditions;

        var EXIST_CAT = "Math";
        var TEST_CAT = "TestCategory";

        it('Perform Login With Valid Admin Credentials, Route to Admin Category Module', function() {

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

            element(by.id('categoriesNavLinkId')).click();
            browser.wait(EC.visibilityOf($('#categoryHeaderId')), 8000);

            expect(element(by.id('addNewCategoryButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('addNewCategoryButton')).isEnabled()).toEqual(true);
            expect(element(by.id('categoryName')).isDisplayed()).toEqual(true);
            expect(element(by.id('categoryName')).isEnabled()).toEqual(true);
            expect(element(by.id('parentCategoryId')).isDisplayed()).toEqual(true);
            expect(element(by.id('parentCategoryId')).isEnabled()).toEqual(true);
            expect(element(by.id('submitUpdateButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitUpdateButton')).isEnabled()).toEqual(false);
            expect(element(by.id('cancelButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('cancelButton')).isEnabled()).toEqual(true);
            expect(element(by.id('deleteCategoryButton')).isDisplayed()).toEqual(false);

            expect(element(by.linkText(EXIST_CAT)).isDisplayed()).toBe(true);
            element(by.linkText(EXIST_CAT)).click();
            expect(element(by.id('submitUpdateButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitUpdateButton')).isEnabled()).toEqual(true);
            expect(element(by.id('cancelButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('cancelButton')).isEnabled()).toEqual(true);
            expect(element(by.id('deleteCategoryButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('deleteCategoryButton')).isEnabled()).toEqual(true);

            element(by.id('addNewCategoryButton')).click();
            expect(element(by.id('submitUpdateButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitUpdateButton')).isEnabled()).toEqual(false);
            expect(element(by.id('cancelButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('cancelButton')).isEnabled()).toEqual(true);
            expect(element(by.id('deleteCategoryButton')).isDisplayed()).toEqual(false);

            var catNameElement = element(by.id('categoryName'));
            catNameElement.sendKeys(TEST_CAT);
            expect(element(by.id('submitUpdateButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitUpdateButton')).isEnabled()).toEqual(true);
            expect(element(by.id('cancelButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('cancelButton')).isEnabled()).toEqual(true);
            expect(element(by.id('deleteCategoryButton')).isDisplayed()).toEqual(false);
            element(by.id('submitUpdateButton')).click();

            var newCatElement = element(by.linkText(TEST_CAT));
            browser.wait(EC.presenceOf(newCatElement), 20000);
            expect(newCatElement.isPresent()).toBeTruthy();
            browser.wait(EC.elementToBeClickable(newCatElement), 20000);

            expect(element(by.linkText(TEST_CAT)).isDisplayed()).toBe(true);
            element(by.linkText(TEST_CAT)).click();
            expect(element(by.id('submitUpdateButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitUpdateButton')).isEnabled()).toEqual(true);
            expect(element(by.id('cancelButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('cancelButton')).isEnabled()).toEqual(true);
            expect(element(by.id('deleteCategoryButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('deleteCategoryButton')).isEnabled()).toEqual(true);


            element(by.id('deleteCategoryButton')).click();
            browser.wait(EC.visibilityOf($('#qmeCofirmModalLabel')), 5000);
            expect($('.modal-dialog').element(by.id('qmeCofirmModalLabel')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('confirmCancelID')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('confirmSubmitID')).isDisplayed()).toEqual(true);
            $('.modal-dialog').element(by.id('confirmSubmitID')).click();

            var existCatElement = element(by.linkText(EXIST_CAT));
            browser.wait(EC.presenceOf(existCatElement), 10000);
            expect(existCatElement.isPresent()).toBeTruthy();
            browser.wait(EC.elementToBeClickable(existCatElement), 10000);

            expect(element(by.linkText(EXIST_CAT)).isDisplayed()).toBe(true);
            element(by.linkText(EXIST_CAT)).click();
            expect(element(by.id('submitUpdateButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitUpdateButton')).isEnabled()).toEqual(true);
            expect(element(by.id('cancelButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('cancelButton')).isEnabled()).toEqual(true);
            expect(element(by.id('deleteCategoryButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('deleteCategoryButton')).isEnabled()).toEqual(true);

            element(by.id('userNameMenuId')).click();
            element(by.id('logoutLinkId')).click();

        });
    });

})();