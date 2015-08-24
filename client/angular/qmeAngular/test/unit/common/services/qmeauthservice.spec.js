(function () {

    'use strict';

    describe('Service: QMe Authentication Service', function() {

        var scope, qmeAuthService, httpBackend;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope, _qmeAuthService_, $httpBackend) {
            scope = $rootScope.$new();
            qmeAuthService = _qmeAuthService_;
            httpBackend = $httpBackend;
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
                "id":1234,
                "name":"test user",
                "role": "user"
            };
            expect(qmeAuthService.isSignedIn()).toBe(false);
            httpBackend.expectPOST('/login',credentials).respond(200,user);
            qmeAuthService.login(credentials);
            httpBackend.flush();
            expect(qmeAuthService.isSignedIn()).toBe(true);
            expect(qmeAuthService.isAdmin()).toBe(false);
            expect(qmeAuthService.username()).toBe('test user');
            expect(qmeAuthService.user()).toBeDefined();
        });

        it('Should have QMe User for valid admin login ', function() {
            expect(qmeAuthService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "username": "testuser",
                "password": "testpassword"
            };
            var user = {
                "id":1234,
                "name":"admin user",
                "role": "admin"
            };
            expect(qmeAuthService.isSignedIn()).toBe(false);
            httpBackend.expectPOST('/login',credentials).respond(200,user);
            qmeAuthService.login(credentials);
            httpBackend.flush();
            expect(qmeAuthService.isSignedIn()).toBe(true);
            expect(qmeAuthService.isAdmin()).toBe(true);
            expect(qmeAuthService.username()).toBe('admin user');
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
                "id":1234,
                "name":"admin user",
                "role": "admin"
            };
            expect(qmeAuthService.isSignedIn()).toBe(false);
            httpBackend.expectPOST('/login',credentials).respond(200,user);
            qmeAuthService.login(credentials);
            httpBackend.flush();
            expect(qmeAuthService.isSignedIn()).toBe(true);
            expect(qmeAuthService.isAdmin()).toBe(true);
            expect(qmeAuthService.username()).toBe('admin user');
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
            httpBackend.expectPOST('/login',credentials).respond(500,{});
            qmeAuthService.login(credentials);
            httpBackend.flush();
            expect(qmeAuthService.isSignedIn()).toBe(false);
            expect(qmeAuthService.isAdmin()).toBe(false);
            expect(qmeAuthService.username()).toBe(null);
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Error Connecting to service');
        });

        it('Should have valid error for user not found error ', function() {
            expect(qmeAuthService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "username": "testuser",
                "password": "testpassword"
            };
            httpBackend.expectPOST('/login',credentials).respond(404,{});
            qmeAuthService.login(credentials);
            httpBackend.flush();
            expect(qmeAuthService.isSignedIn()).toBe(false);
            expect(qmeAuthService.isAdmin()).toBe(false);
            expect(qmeAuthService.username()).toBe(null);
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('User not found');
        });

        it('Should have valid error for user not found error ', function() {
            expect(qmeAuthService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "username": "testuser",
                "password": "testpassword"
            };
            var user = {
                "id":1234,
                "name":"admin user",
                "role": "admin"
            };
            httpBackend.expectPOST('/login',credentials).respond(201,user);
            qmeAuthService.login(credentials);
            httpBackend.flush();
            expect(qmeAuthService.isSignedIn()).toBe(false);
            expect(qmeAuthService.isAdmin()).toBe(false);
            expect(qmeAuthService.username()).toBe(null);
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('User not found');
        });

    });
})();
