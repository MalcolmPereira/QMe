(function () {

    'use strict';

    describe('Service: QMe Authentication Service', function() {

            var scope, qmeAuthService, httpBackend,qmeContants,userAuthEndPoint,logoutUserEndPoint ;

            beforeEach(module('qmeApp'));

            beforeEach(inject(function($rootScope, _qmeAuthService_, $httpBackend,_QME_CONSTANTS_) {
                scope = $rootScope.$new();
                qmeAuthService = _qmeAuthService_;
                httpBackend = $httpBackend;
                qmeContants = _QME_CONSTANTS_;
                userAuthEndPoint =  qmeContants.qmeservice+"/login";
                logoutUserEndPoint =  qmeContants.qmeservice+"/logout";
            }));


            it('Should have a QMe Auth Service', function() {
                expect(qmeAuthService).toBeDefined();
                expect(scope.flash).not.toBeDefined();
            });


            it('Should have QMe User for valid user login ', function() {
                expect(qmeAuthService).toBeDefined();
                var credentials = {
                    "userName": "testuser",
                    "userPassword": "testpassword"
                };
                var user = {
                    "authToken": "someauthtoken",
                    "userId": 1,
                    "userName": "testuser",
                    "userPassword": null,
                    "userFirstName": "Test",
                    "userLastName": "User",
                    "userEmail": "test.user@gmail.com",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                };
                expect(qmeAuthService.isSignedIn()).toBe(false);
                httpBackend.expectPOST(userAuthEndPoint,credentials).respond(200,user);
                qmeAuthService
                .login(credentials)
                    .then(
                        function(res){
                            expect(qmeAuthService.isSignedIn()).toBe(true);
                            expect(qmeAuthService.isAdmin()).toBe(false);
                            expect(qmeAuthService.username()).toBe('testuser');
                            expect(qmeAuthService.user()).toBeDefined();
                        },
                        function(error){
                        }
                    );
                httpBackend.flush();
            });


            it('Should have QMe User for valid admin login ', function() {
                expect(qmeAuthService).toBeDefined();
                expect(scope.flash).not.toBeDefined();
                var credentials = {
                    "userName": "testuser",
                    "userPassword": "testpassword"
                };
                var user = {
                    "authToken": "someauthtoken",
                    "userId": 1,
                    "userName": "testadmin",
                    "userPassword": null,
                    "userFirstName": "Test",
                    "userLastName": "User",
                    "userEmail": "test.user@gmail.com",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['ADMIN','USER']
                };
                expect(qmeAuthService.isSignedIn()).toBe(false);
                httpBackend.expectPOST(userAuthEndPoint,credentials).respond(200,user);
                qmeAuthService
                .login(credentials)
                    .then(
                        function(res){
                            expect(qmeAuthService.isSignedIn()).toBe(true);
                            expect(qmeAuthService.isAdmin()).toBe(true);
                            expect(qmeAuthService.username()).toBe('testadmin');
                            expect(qmeAuthService.user()).toBeDefined();
                        },
                        function(error){
                        }
                );
                httpBackend.flush();
            });


            it('Should have No QMe User for valid logout ', function() {
                expect(qmeAuthService).toBeDefined();
                expect(scope.flash).not.toBeDefined();
                var credentials = {
                    "userName": "testuser",
                    "userPassword": "testpassword"
                };
                var user = {
                    "authToken": "someauthtoken",
                    "userId": 1,
                    "userName": "testadmin",
                    "userPassword": null,
                    "userFirstName": "Test",
                    "userLastName": "User",
                    "userEmail": "test.user@gmail.com",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['ADMIN','USER']
                };
                expect(qmeAuthService.isSignedIn()).toBe(false);
                httpBackend.expectPOST(userAuthEndPoint,credentials).respond(200,user);
                httpBackend.expectPOST(logoutUserEndPoint).respond(200,user);
                qmeAuthService
                .login(credentials)
                    .then(
                        function(res){
                            expect(qmeAuthService.isSignedIn()).toBe(true);
                            expect(qmeAuthService.isAdmin()).toBe(true);
                            expect(qmeAuthService.username()).toBe('testadmin');
                            expect(qmeAuthService.user()).toBeDefined();
                            qmeAuthService.logout();
                            expect(qmeAuthService.isSignedIn()).toBe(false);
                            expect(qmeAuthService.isAdmin()).toBe(false);
                            expect(qmeAuthService.username()).toBe(null);
                        },
                        function(error){
                        }
                );
                httpBackend.flush();
            });

            it('Should have valid error for server error ', function() {
                expect(qmeAuthService).toBeDefined();
                expect(scope.flash).not.toBeDefined();
                var credentials = {
                    "userName": "testuser",
                    "userPassword": "testpassword"
                };
                httpBackend.expectPOST(userAuthEndPoint,credentials).respond(500,{});
                qmeAuthService
                .login(credentials)
                    .then(
                        function(res){
                        },
                        function(error){
                            expect(qmeAuthService.isSignedIn()).toBe(false);
                            expect(qmeAuthService.isAdmin()).toBe(false);
                            expect(qmeAuthService.username()).toBe(null);
                            expect(error.status).toBe(500);
                        }
                    );
                httpBackend.flush();
            });

            it('Should have valid error for user not found error ', function() {
                expect(qmeAuthService).toBeDefined();
                expect(scope.flash).not.toBeDefined();
                var credentials = {
                    "userName": "testuser",
                    "userPassword": "testpassword"
                };
                httpBackend.expectPOST(userAuthEndPoint,credentials).respond(404,{});
                qmeAuthService
                    .login(credentials)
                    .then(
                        function(res){

                        },
                        function(error){
                            expect(qmeAuthService.isSignedIn()).toBe(false);
                            expect(qmeAuthService.isAdmin()).toBe(false);
                            expect(qmeAuthService.username()).toBe(null);
                            expect(error.status).toBe(404);
                        }
                );
                httpBackend.flush();
            });

        it('Should have valid error for user not authorized error ', function() {
            expect(qmeAuthService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var credentials = {
                "userName": "testuser",
                "userPassword": "testpassword"
            };
            httpBackend.expectPOST(userAuthEndPoint,credentials).respond(403,{});
            qmeAuthService
                .login(credentials)
                .then(
                function(res){

                },
                function(error){
                    expect(qmeAuthService.isSignedIn()).toBe(false);
                    expect(qmeAuthService.isAdmin()).toBe(false);
                    expect(qmeAuthService.username()).toBe(null);
                    expect(error.status).toBe(403);
                }
            );
            httpBackend.flush();
        });
    });
})();

