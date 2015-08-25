(function () {

    'use strict';

    describe('Service: QMe Authentication Service', function() {

        var scope, qmeAuthService, httpBackend,qmeContants;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope, _qmeAuthService_, $httpBackend,_QME_CONSTANTS_) {
            scope = $rootScope.$new();
            qmeAuthService = _qmeAuthService_;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
        }));

        it('Should have a QMe Auth Service', function() {
            expect(qmeAuthService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
        });

        it('Should have QMe User for valid user login ', function() {
            expect(qmeAuthService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "username": "testuser",
                "password": "testpassword"
            };
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
                "role": ['user']
            };
            expect(qmeAuthService.isSignedIn()).toBe(false);
            console.log(qmeContants.authendpoint);
            httpBackend.expectGET(qmeContants.authendpoint+credentials.username).respond(200,user);
            qmeAuthService.login(credentials);
            httpBackend.flush();
            expect(qmeAuthService.isSignedIn()).toBe(true);
            expect(qmeAuthService.isAdmin()).toBe(false);
            expect(qmeAuthService.username()).toBe('testuser');
            expect(qmeAuthService.user()).toBeDefined();
        });


        it('Should have QMe User for valid admin login ', function() {
            expect(qmeAuthService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "username": "testadmin",
                "password": "testpassword"
            };
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
                "role": ['admin','user']
            };
            expect(qmeAuthService.isSignedIn()).toBe(false);
            httpBackend.expectGET(qmeContants.authendpoint+credentials.username).respond(200,user);
            qmeAuthService.login(credentials);
            httpBackend.flush();
            expect(qmeAuthService.isSignedIn()).toBe(true);
            expect(qmeAuthService.isAdmin()).toBe(true);
            expect(qmeAuthService.username()).toBe('testadmin');
            expect(qmeAuthService.user()).toBeDefined();
        });


        it('Should have No QMe User for valid logout ', function() {
            expect(qmeAuthService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "username": "testuser",
                "password": "testpassword"
            };
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
                "role": ['admin','user']
            };
            expect(qmeAuthService.isSignedIn()).toBe(false);
            httpBackend.expectGET(qmeContants.authendpoint+credentials.username).respond(200,user);
            qmeAuthService.login(credentials);
            httpBackend.flush();
            expect(qmeAuthService.isSignedIn()).toBe(true);
            expect(qmeAuthService.isAdmin()).toBe(true);
            expect(qmeAuthService.username()).toBe('testadmin');
            expect(qmeAuthService.user()).toBeDefined();
            qmeAuthService.logout();
            expect(qmeAuthService.isSignedIn()).toBe(false);
            expect(qmeAuthService.isAdmin()).toBe(false);
            expect(qmeAuthService.username()).toBe(null);
        });

        it('Should have valid error for server error ', function() {
            expect(qmeAuthService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "username": "testuser",
                "password": "testpassword"
            };
            httpBackend.expectGET(qmeContants.authendpoint+credentials.username).respond(500,{});
            qmeAuthService.login(credentials);
            httpBackend.flush();
            expect(qmeAuthService.isSignedIn()).toBe(false);
            expect(qmeAuthService.isAdmin()).toBe(false);
            expect(qmeAuthService.username()).toBe(null);
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error Connecting to service, please retry in some time.');
        });

        it('Should have valid error for user not found error ', function() {
            expect(qmeAuthService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "username": "testuser",
                "password": "testpassword"
            };
            httpBackend.expectGET(qmeContants.authendpoint+credentials.username).respond(404,{});
            qmeAuthService.login(credentials);
            httpBackend.flush();
            expect(qmeAuthService.isSignedIn()).toBe(false);
            expect(qmeAuthService.isAdmin()).toBe(false);
            expect(qmeAuthService.username()).toBe(null);
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Error Connecting to service, entered user credential not found.');
        });

    });
})();
