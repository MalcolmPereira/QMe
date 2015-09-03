(function () {
    'use strict';

    describe('Controller: QMe Header Controller', function() {

        var scope, httpBackend, ctrl,qmeContants;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope, $controller,$httpBackend,_QME_CONSTANTS_) {
            scope = $rootScope.$new();
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            ctrl  = $controller('qmeHeaderCtrl', {
                $scope: scope,
            });
        }));

        it('Should have a qmeHeader controller', function() {
            expect(ctrl).toBeDefined();
        });


        describe('QMeHeader Defaults', function(){
            it('Ensure user is not signed - signedIn is false', function() {
                expect(ctrl.isSignedIn() ).toBe(false);
            });
            it('Ensure user is not registering - isRegistering  is false', function() {
                expect(ctrl.isRegistering() ).toBe(false);
            });
            it('Ensure user is not resetting password  - isResetingPassword is false', function() {
                expect(ctrl.isResetingPassword() ).toBe(false);
            });
            it('Ensure user email is empty - userEmail is empty ', function() {
                expect(ctrl.userEmail ).toBe('');
            });
            it('Ensure user password is empty - userPassword is empty ', function() {
                expect(ctrl.userPassword ).toBe('');
            });
        });

       it('Ensure user sign-on is valid ', function() {
           ctrl.userEmail = "testuser@test.com";
           ctrl.userPassword = "testpassword";
           var user = {
               "userName": "testuser",
               "userPassword": null,
               "userFirstName": "Test",
               "userLastName": "User",
               "userEmail": "test.user@gmail.com",
               "userId": 1,
               "userRegisteredDate": "2015-28-05 13:35:29",
               "userUpdateDate": "2015-28-05 13:35:29",
               "updateUserID": 0,
               "updateUserName": "",
               "userRoles": ['USER']
           };
           httpBackend.expectGET(qmeContants.authendpoint+ctrl.userEmail).respond(200,user);
           httpBackend.whenGET(/js\//).respond(200,{});
           ctrl.performSignIn();
           httpBackend.flush();
           expect(ctrl.userEmail ).not.toBe('');
           expect(ctrl.userEmail ).toBe('testuser@test.com');
           expect(ctrl.userPassword ).not.toBe('');
           expect(ctrl.userPassword ).toBe('testpassword');
           expect(ctrl.isSignedIn() ).toBe(true);
           expect(ctrl.isAdmin() ).toBe(false);
       });

       it('Ensure user sign-on is valid ', function() {
            ctrl.userEmail = "testuser@test.com";
            ctrl.userPassword = "testpassword";
            var user = {
               "userName": "testadmin",
               "userPassword": null,
               "userFirstName": "Test",
               "userLastName": "Admin",
               "userEmail": "test.admin@gmail.com",
               "userId": 1,
               "userRegisteredDate": "2015-28-05 13:35:29",
               "userUpdateDate": "2015-28-05 13:35:29",
               "updateUserID": 0,
               "updateUserName": "",
               "userRoles": ['ADMIN','USER']
            };
           httpBackend.expectGET(qmeContants.authendpoint+ctrl.userEmail).respond(200,user);
           httpBackend.whenGET(/js\//).respond(200,{});
           ctrl.performSignIn();
           httpBackend.flush();
           expect(ctrl.userEmail ).not.toBe('');
           expect(ctrl.userEmail ).toBe('testuser@test.com');
           expect(ctrl.userPassword ).not.toBe('');
           expect(ctrl.userPassword ).toBe('testpassword');
           expect(ctrl.isSignedIn() ).toBe(true);
           expect(ctrl.isAdmin() ).toBe(true);
       });
    });
})();