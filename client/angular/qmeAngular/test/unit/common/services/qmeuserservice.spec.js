(function () {

    'use strict';

    describe('Service: QMe User Service', function() {

        var scope, state, qmeUserService, qmeUserSession,httpBackend,qmeContants;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope,$state, _qmeUserService_, _qmeUserSession_,$httpBackend,_QME_CONSTANTS_) {
            scope = $rootScope.$new();
            state = $state;
            qmeUserService = _qmeUserService_;
            qmeUserSession = _qmeUserSession_;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
        }));

        it('Should have a QMe Auth Service', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
        });



        it('Should throw invalid error for wrong data ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'someusername',
                "userPassword": 'somepassword' ,
                "userFirstName": 'first name',
                "userLastName": 'last name',
                "userEmail": 'email'
            };
            httpBackend.expectPOST(qmeContants.serviceurl+qmeContants.userapi+"register",user).respond(400,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .register(user)
                    .then(
                    function(res){
                    },
                    function(error){
                        expect(error.status).toBe(400);
                    }
            );
            httpBackend.flush();
        });


        it('Should throw conflict error for duplicate data ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'someusername',
                "userPassword": 'somepassword' ,
                "userFirstName": 'first name',
                "userLastName": 'last name',
                "userEmail": 'email'
            };
            httpBackend.expectPOST(qmeContants.serviceurl+qmeContants.userapi+"register",user).respond(409,{});
            httpBackend.whenGET(/js\//).respond(200,{});

            qmeUserService
                .register(user)
                    .then(
                        function(res){
                        },
                        function(error){
                            expect(error.status).toBe(409);
                        }
            );
            httpBackend.flush();
        });


        it('Should throw server error for server data ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'someusername',
                "userPassword": 'somepassword' ,
                "userFirstName": 'first name',
                "userLastName": 'last name',
                "userEmail": 'email'
            };
            httpBackend.expectPOST(qmeContants.serviceurl+qmeContants.userapi+"register",user).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .register(user)
                    .then(
                        function(res){
                        },
                        function(error){
                            expect(error.status).toBe(500);
                        }
            );
            httpBackend.flush();
        });



        it('Should register user for valid data ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var user = {
                "userName": 'testuser',
                "userPassword": 'somepassword' ,
                "userFirstName": 'Test',
                "userLastName": 'User',
                "userEmail": 'test.user@gmail.com'
            };
            var registereduser = {
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
                "userRoles": []
            };
            httpBackend.expectPOST(qmeContants.serviceurl+qmeContants.userapi+"register",user).respond(200,registereduser);
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .register(user)
                    .then(
                        function(res){
                            expect(res).toBeDefined();
                        },
                        function(error){
                        }
            );
            httpBackend.flush();
        });

        it('Should submit reset request with url for password reset ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var useremail = "some-email"
            httpBackend.expectPUT(qmeContants.serviceurl+qmeContants.userapi+"reset/forgotpassword/"+useremail,qmeContants.reseturl).respond(200,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .resetPassword(useremail)
                .then(
                    function(res){
                        expect(res).toBeDefined();
                    },
                    function(error){
                    }
            );
            httpBackend.flush();
        });

        it('Should return user not found error 404 on password reset ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var useremail = "some-email"
            httpBackend.expectPUT(qmeContants.serviceurl+qmeContants.userapi+"reset/forgotpassword/"+useremail,qmeContants.reseturl).respond(404,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .resetPassword(useremail)
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                    expect(error.status).toBe(404);
                }
            );
            httpBackend.flush();
        });

        it('Should return internal server error 500  on password reset ', function() {
            expect(qmeUserService).toBeDefined();
            expect(scope.flash).not.toBeDefined();
            var useremail = "some-email"
            httpBackend.expectPUT(qmeContants.serviceurl+qmeContants.userapi+"reset/forgotpassword/"+useremail,qmeContants.reseturl).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            qmeUserService
                .resetPassword(useremail)
                .then(
                function(res){
                    expect(res).toBeDefined();
                },
                function(error){
                    expect(error.status).toBe(500);
                }
            );
            httpBackend.flush();
        });
    });
})();