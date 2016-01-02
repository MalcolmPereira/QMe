(function () {

	"use strict";

	describe('End to End : QMe Admin Module - User Management Tests ', function () {

        var EC = protractor.ExpectedConditions;

        it('Perform Login With Valid Admin Credentials, Route to Admin Core Modules', function() {

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

            element(by.id('usersNavLinkId')).click();
            browser.wait(EC.visibilityOf($('#qmepageinationDivId')), 5000);
            expect(element(by.css('.panel-title')).getText()).toEqual('User List');
            var headers = element.all(by.css('table.table th')).map(function(elm) {
                return elm.getText();
            });
            expect(headers).toEqual(['User Name', 'User Email', 'First Name', 'Last Name', 'Registered Date', 'Last Login Date', 'User Roles']);

            element(by.id('categoriesNavLinkId')).click();
            expect(element(by.css('.panel-title')).getText()).toEqual('Category List');

            element(by.id('questionsNavLinkId')).click();
            expect(element(by.css('.panel-title')).getText()).toEqual('Question List');

            element(by.id('quizzesNavLinkId')).click();
            expect(element(by.css('.panel-title')).getText()).toEqual('Quiz List');

            element(by.id('userNameMenuId')).click();
            element(by.id('logoutLinkId')).click();
        });


        it('Perform Login With Valid Admin Credentials, Validate User Module', function() {

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

            element(by.id('usersNavLinkId')).click();
            browser.wait(EC.visibilityOf($('#qmepageinationDivId')), 5000);
            expect(element(by.css('.panel-title')).getText()).toEqual('User List');
            var headers = element.all(by.css('table.table th')).map(function(elm) {
                return elm.getText();
            });
            expect(headers).toEqual(['User Name', 'User Email', 'First Name', 'Last Name', 'Registered Date', 'Last Login Date', 'User Roles']);

            element(by.id('userId0')).click();

            expect(element(by.id('userEmail')).isDisplayed()).toEqual(true);
            expect(element(by.id('userEmail')).isEnabled()).toEqual(false);
            expect(element(by.id('userName')).isDisplayed()).toEqual(true);
            expect(element(by.id('userName')).isEnabled()).toEqual(false);
            expect(element(by.id('userPassword')).isDisplayed()).toEqual(true);
            expect(element(by.id('userPassword')).isEnabled()).toEqual(false);
            expect(element(by.id('userFirstName')).isDisplayed()).toEqual(true);
            expect(element(by.id('userFirstName')).isEnabled()).toEqual(true);
            expect(element(by.id('userLastName')).isDisplayed()).toEqual(true);
            expect(element(by.id('userLastName')).isEnabled()).toEqual(true);
            expect(element(by.id('userRoleCheckBoxId')).isDisplayed()).toEqual(true);
            expect(element(by.id('userRoleCheckBoxId')).isEnabled()).toEqual(false);
            expect(element(by.id('reviewerRoleCheckBoxId')).isDisplayed()).toEqual(true);
            expect(element(by.id('reviewerRoleCheckBoxId')).isEnabled()).toEqual(true);
            expect(element(by.id('moderatorRoleCheckBoxId')).isDisplayed()).toEqual(true);
            expect(element(by.id('moderatorRoleCheckBoxId')).isEnabled()).toEqual(true);
            expect(element(by.id('submitUpdateUserButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitUpdateUserButton')).isEnabled()).toEqual(true);
            expect(element(by.id('cancelButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('cancelButton')).isEnabled()).toEqual(true);
            expect(element(by.id('deleteButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('deleteButton')).isEnabled()).toEqual(true);

            element(by.id('deleteButton')).click();
            browser.wait(EC.visibilityOf($('#qmeCofirmModalLabel')), 5000);
            expect($('.modal-dialog').element(by.id('qmeCofirmModalLabel')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('confirmCancelID')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('confirmSubmitID')).isDisplayed()).toEqual(true);
            $('.modal-dialog').element(by.id('confirmCancelID')).click();

            browser.wait(EC.visibilityOf($('#deleteButton')), 5000);

            expect(element(by.id('deleteButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('deleteButton')).isEnabled()).toEqual(true);
            expect(element(by.id('cancelButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('cancelButton')).isEnabled()).toEqual(true);

            element(by.id('cancelButton')).click();

            browser.wait(EC.visibilityOf($('#qmepageinationDivId')), 5000);
            expect(element(by.css('.panel-title')).getText()).toEqual('User List');
            headers = element.all(by.css('table.table th')).map(function(elm) {
                return elm.getText();
            });
            expect(headers).toEqual(['User Name', 'User Email', 'First Name', 'Last Name', 'Registered Date', 'Last Login Date', 'User Roles']);


            expect(element(by.id('dUserLabelId')).isDisplayed()).toEqual(true);
            expect(element(by.id('dUserLabelId')).isEnabled()).toEqual(true);
            element(by.id('dUserLabelId')).click();
            expect(element(by.id('addNewUserId')).isDisplayed()).toEqual(true);
            expect(element(by.id('addNewUserId')).isEnabled()).toEqual(true);
            element(by.id('addNewUserId')).click();

            expect(element(by.id('userEmail')).isDisplayed()).toEqual(true);
            expect(element(by.id('userEmail')).isEnabled()).toEqual(true);
            expect(element(by.id('userName')).isDisplayed()).toEqual(true);
            expect(element(by.id('userName')).isEnabled()).toEqual(true);
            expect(element(by.id('userPassword')).isDisplayed()).toEqual(true);
            expect(element(by.id('userPassword')).isEnabled()).toEqual(true);
            expect(element(by.id('userPasswordConfirm')).isDisplayed()).toEqual(true);
            expect(element(by.id('userPasswordConfirm')).isEnabled()).toEqual(true);
            expect(element(by.id('userFirstName')).isDisplayed()).toEqual(true);
            expect(element(by.id('userFirstName')).isEnabled()).toEqual(true);
            expect(element(by.id('userLastName')).isDisplayed()).toEqual(true);
            expect(element(by.id('userLastName')).isEnabled()).toEqual(true);

            expect(element(by.id('submitAddUserButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitAddUserButton')).isEnabled()).toEqual(false);
            expect(element(by.id('cancelButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('cancelButton')).isEnabled()).toEqual(true);

            var userEmailAdd = element(by.id('userEmail'));
            var userNameAdd  = element(by.id('userName'));
            var userPasswordAdd  = element(by.id('userPassword'));
            var userPasswordConfirmAdd  = element(by.id('userPasswordConfirm'));
            var userFirstNameAdd  = element(by.id('userFirstName'));
            var userLastNameAdd  = element(by.id('userLastName'));

            userEmailAdd.sendKeys('test.me@gmail.com');
            userNameAdd.sendKeys('testme');
            userPasswordAdd.sendKeys('testtest');
            userPasswordConfirmAdd.sendKeys('testtest');
            userFirstNameAdd.sendKeys('Test');
            userLastNameAdd.sendKeys('Me');

            expect(element(by.id('submitAddUserButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitAddUserButton')).isEnabled()).toEqual(true);
            element(by.id('submitAddUserButton')).click();
            browser.wait(EC.visibilityOf($('#flashMessageId')), 5000);
            expect(element(by.id('flashMessageId')).isDisplayed()).toEqual(true);

            var userNavLinkElement = element(by.id("usersNavLinkId"));
            browser.wait(EC.presenceOf(userNavLinkElement), 25000);
            expect(userNavLinkElement.isPresent()).toBeTruthy();
            browser.wait(EC.elementToBeClickable(userNavLinkElement), 25000);

            element(by.id('usersNavLinkId')).click();
            browser.wait(EC.visibilityOf($('#qmepageinationDivId')), 5000);
            expect(element(by.css('.panel-title')).getText()).toEqual('User List');
            headers = element.all(by.css('table.table th')).map(function(elm) {
                return elm.getText();
            });
            expect(headers).toEqual(['User Name', 'User Email', 'First Name', 'Last Name', 'Registered Date', 'Last Login Date', 'User Roles']);

            element(by.id('userNameMenuId')).click();
            element(by.id('logoutLinkId')).click();

            userEmail = element(by.model('qmeUserCtrl.userEmail'));
            userPassword = element(by.model('qmeUserCtrl.userPassword'));
            expect(userEmail.getText()).toEqual('');
            expect(userPassword.getText()).toEqual('');
            expect(element(by.id('emailField')).isDisplayed()).toEqual(true);
            expect(element(by.id('passwordField')).isDisplayed()).toEqual(true);
            expect(element(by.id('signInButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('signInButton')).isEnabled()).toEqual(false);
            expect(element(by.css('.panel-title')).getText()).toEqual('Home Page');

            userEmail.sendKeys('test.me@gmail.com');
            userPassword.sendKeys('testtest');
            expect(element(by.id('signInButton')).isEnabled()).toEqual(true);
            element(by.id('signInButton')).click();
            expect(element(by.id('emailField')).isDisplayed()).toEqual(false);
            expect(element(by.id('passwordField')).isDisplayed()).toEqual(false);
            expect(element(by.id('registerButton')).isDisplayed()).toEqual(false);
            expect(element(by.id('forgotButton')).isDisplayed()).toEqual(false);
            expect(element(by.id('qmeAppHeader')).getText()).toEqual('QMe Application');
            expect(element(by.id('loginName')).getText()).toEqual('Test Me');
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

            var firstNameUpdate = element(by.id('userFirstName'));
            var lastNameUpdate  = element(by.id('userLastName'));
            firstNameUpdate.sendKeys('U');
            lastNameUpdate.sendKeys('U');

            element(by.id('submitUpdateProfileButton')).click();
            expect(element(by.id('loginName')).getText()).toEqual('TestU MeU');

            element(by.id('userNameMenuId')).click();
            element(by.id('logoutLinkId')).click();

            userEmail = element(by.model('qmeUserCtrl.userEmail'));
            userPassword = element(by.model('qmeUserCtrl.userPassword'));
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

            element(by.id('usersNavLinkId')).click();
            browser.wait(EC.visibilityOf($('#qmepageinationDivId')), 5000);
            expect(element(by.css('.panel-title')).getText()).toEqual('User List');
            headers = element.all(by.css('table.table th')).map(function(elm) {
                return elm.getText();
            });
            expect(headers).toEqual(['User Name', 'User Email', 'First Name', 'Last Name', 'Registered Date', 'Last Login Date', 'User Roles']);

            expect(element(by.linkText('testme')).isDisplayed()).toBe(true);
            element(by.linkText('testme')).click();
            expect(element(by.id('userEmail')).isDisplayed()).toEqual(true);
            expect(element(by.id('userEmail')).isEnabled()).toEqual(false);
            expect(element(by.id('userName')).isDisplayed()).toEqual(true);
            expect(element(by.id('userName')).isEnabled()).toEqual(false);
            expect(element(by.id('userPassword')).isDisplayed()).toEqual(true);
            expect(element(by.id('userPassword')).isEnabled()).toEqual(false);
            expect(element(by.id('userFirstName')).isDisplayed()).toEqual(true);
            expect(element(by.id('userFirstName')).isEnabled()).toEqual(true);
            expect(element(by.id('userLastName')).isDisplayed()).toEqual(true);
            expect(element(by.id('userLastName')).isEnabled()).toEqual(true);
            expect(element(by.id('userRoleCheckBoxId')).isDisplayed()).toEqual(true);
            expect(element(by.id('userRoleCheckBoxId')).isEnabled()).toEqual(false);
            expect(element(by.id('reviewerRoleCheckBoxId')).isDisplayed()).toEqual(true);
            expect(element(by.id('reviewerRoleCheckBoxId')).isEnabled()).toEqual(true);
            expect(element(by.id('moderatorRoleCheckBoxId')).isDisplayed()).toEqual(true);
            expect(element(by.id('moderatorRoleCheckBoxId')).isEnabled()).toEqual(true);
            expect(element(by.id('submitUpdateUserButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('submitUpdateUserButton')).isEnabled()).toEqual(true);
            expect(element(by.id('cancelButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('cancelButton')).isEnabled()).toEqual(true);
            expect(element(by.id('deleteButton')).isDisplayed()).toEqual(true);
            expect(element(by.id('deleteButton')).isEnabled()).toEqual(true);

            element(by.id('deleteButton')).click();
            browser.wait(EC.visibilityOf($('#qmeCofirmModalLabel')), 5000);
            expect($('.modal-dialog').element(by.id('qmeCofirmModalLabel')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('confirmCancelID')).isDisplayed()).toEqual(true);
            expect($('.modal-dialog').element(by.id('confirmSubmitID')).isDisplayed()).toEqual(true);
            $('.modal-dialog').element(by.id('confirmSubmitID')).click();

            browser.wait(EC.visibilityOf($('#flashMessageId')), 5000);
            expect(element(by.id('flashMessageId')).isDisplayed()).toEqual(true);

            userNavLinkElement = element(by.id("usersNavLinkId"));
            browser.wait(EC.presenceOf(userNavLinkElement), 25000);
            expect(userNavLinkElement.isPresent()).toBeTruthy();
            browser.wait(EC.elementToBeClickable(userNavLinkElement), 25000);

            userNavLinkElement.click();

            element(by.id('userNameMenuId')).click();
            element(by.id('logoutLinkId')).click();

        });

	});

})();