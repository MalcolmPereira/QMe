(function () {

    'use strict';

    describe('Controller: QMe User Controller', function() {


        var scope, state, httpBackend, ctrl, qmeContants, userAuthEndPoint,userStagingEndpoint,userRegisterEndpoint,logoutUserEndPoint,userForgotPaswordEndpoint;

        beforeEach(module('qmeApp'));


        beforeEach(inject(function($rootScope,$state, $controller,$httpBackend,_QME_CONSTANTS_) {

            scope = $rootScope.$new();

            state = $state;

            httpBackend = $httpBackend;

            qmeContants = _QME_CONSTANTS_;

            userAuthEndPoint =  qmeContants.qmeservice+"/user/searchemail/";

            userStagingEndpoint = qmeContants.qmeservice+"/user/stage";

            userRegisterEndpoint = qmeContants.qmeservice+"/user/register";

            userForgotPaswordEndpoint =  qmeContants.qmeservice+"/user/reset/forgotpassword/";

            logoutUserEndPoint =  qmeContants.qmeservice+"/logout";

            ctrl  = $controller('qmeUserCtrl', {
                $scope: scope,
                $state: state,
                userEmail:"",
                userName:"",
                userPassword:"",
                userPasswordConfirm:"",
                userFirstName:"",
                userLastName:""
            });
        }));

        it('Should have a QMe User controller', function() {
            expect(ctrl).toBeDefined();
        });

        it('Ensure User is not signed - signedIn is false', function() {
            expect(ctrl.isSignedIn() ).toBe(false);
        });

        it('Ensure user is not registering - isRegistering  is false', function() {
            expect(ctrl.isRegistering() ).toBe(false);
        });

        it('Ensure user is not resetting password  - isResetingPassword is false', function() {
            expect(ctrl.isResetingPassword() ).toBe(false);
        });

        it('Ensure user email is empty - userEmail is empty ', function() {
            expect(ctrl.userEmail ).toBe("");
        });

        it('Ensure user password is empty - userPassword is empty ', function() {
            expect(ctrl.userPassword ).toBe("");
        });

        it('Ensure user able to sign-on with valid credentials for USER Role', function() {
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
            httpBackend.expectGET(userAuthEndPoint+ctrl.userEmail).respond(200,user);
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

        it('Ensure user able to sign-on with valid credentials for ADMIN Role', function() {
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
            httpBackend.expectGET(userAuthEndPoint+ctrl.userEmail).respond(200,user);
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
        //TODO: Write Test Cases for 404 and 401 Errors

        it('Should have default fields available for User Staging', function() {
            expect(ctrl.userEmail).toBeDefined();
            expect(ctrl.userEmail).toBe("");
            expect(ctrl.userName).toBeDefined();
            expect(ctrl.userName).toBe("");
            expect(ctrl.userNameDisplay).toBeDefined();
            expect(ctrl.userNameDisplay()).toBe("");
            expect(ctrl.userPassword).toBeDefined();
            expect(ctrl.userPassword).toBe("");
            expect(ctrl.userPasswordConfirm).toBeDefined();
            expect(ctrl.userPasswordConfirm).toBe("");
            expect(ctrl.userFirstName).toBeDefined();
            expect(ctrl.userFirstName).toBe("");
            expect(ctrl.userLastName).toBeDefined();
            expect(ctrl.userLastName).toBe("");
        });

        it('Should check for non-matching passwords on User Staging', function() {
            ctrl.userPassword = 'password1';
            ctrl.userPasswordConfirm = 'password2';
            ctrl.validatePasswordFields();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Password do not match, please confirm password');
            ctrl.userPassword = 'password1';
            ctrl.userPasswordConfirm = 'password1';
            ctrl.validatePasswordFields();
            ctrl.validatePasswordFields();
            expect(scope.flash).not.toBeDefined();
        });

        it('Should handle 400 Error for invalid data on User Staging', function() {
            ctrl.userEmail = "someemail";
            ctrl.userName = "someusername";
            ctrl.userPassword = "somepassword";
            ctrl.userPasswordConfirm= "somepassword";
            ctrl.userFirstName = "firstname";
            ctrl.userLastName = "lastname";
            var user = {
                "userName": ctrl.userName,
                "userPassword": ctrl.userPassword ,
                "userFirstName": ctrl.userFirstName,
                "userLastName": ctrl.userLastName,
                "userEmail": ctrl.userEmail,
                "confirmURL": qmeContants.stageconfirmurl
            };
            httpBackend.expectPOST(userStagingEndpoint,user).respond(400,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.stageUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request for user registration, please make sure all required fields are valid.');
        });

        it('Should handle 409 Error for duplicate data on User Staging', function() {
            ctrl.userEmail = "someemail";
            ctrl.userName = "someusername";
            ctrl.userPassword = "somepassword";
            ctrl.userPasswordConfirm= "somepassword";
            ctrl.userFirstName = "firstname";
            ctrl.userLastName = "lastname";
            var user = {
                "userName": ctrl.userName,
                "userPassword": ctrl.userPassword ,
                "userFirstName": ctrl.userFirstName,
                "userLastName": ctrl.userLastName,
                "userEmail": ctrl.userEmail,
                "confirmURL": qmeContants.stageconfirmurl
            };
            httpBackend.expectPOST(userStagingEndpoint,user).respond(409,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.stageUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops...User with same email address already exists please enter valid unique email address.');
        });

        it('Should handle 500 Error for server error on User Staging', function() {
            ctrl.userEmail = "someemail";
            ctrl.userName = "someusername";
            ctrl.userPassword = "somepassword";
            ctrl.userPasswordConfirm= "somepassword";
            ctrl.userFirstName = "firstname";
            ctrl.userLastName = "lastname";
            var user = {
                "userName": ctrl.userName,
                "userPassword": ctrl.userPassword ,
                "userFirstName": ctrl.userFirstName,
                "userLastName": ctrl.userLastName,
                "userEmail": ctrl.userEmail,
                "confirmURL": qmeContants.stageconfirmurl
            };
            httpBackend.expectPOST(userStagingEndpoint,user).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.stageUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error registering new user, please retry in some time.');
        });

        it('Should handle valid User Staging ', function() {
            ctrl.userEmail = "someemail";
            ctrl.userName = "someusername";
            ctrl.userPassword = "somepassword";
            ctrl.userPasswordConfirm= "somepassword";
            ctrl.userFirstName = "firstname";
            ctrl.userLastName = "lastname";
            var user = {
                "userName": ctrl.userName,
                "userPassword": ctrl.userPassword ,
                "userFirstName": ctrl.userFirstName,
                "userLastName": ctrl.userLastName,
                "userEmail": ctrl.userEmail,
                "confirmURL": qmeContants.stageconfirmurl
            };
            httpBackend.expectPOST(userStagingEndpoint,user).respond(200,"sometoken");
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.stageUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('success');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('User registration submitted, please check your email and complete steps to confirm registration, Thank you.');
            expect(state).toBeDefined();
            expect(state.current.name).toBe('home');
            expect(state.current.url).toBe('/home');
            expect(state.current.templateUrl).toBe('js/home/qmehome.tmpl.html');
            expect(state.current.controller).toBe('qmeHomeCtrl');
            expect(state.current.controllerAs).toBe('qmeHomeCtrl');

        });

        it('Should have default fields available for User Registration', function() {
            expect(ctrl.userEmail).toBeDefined();
            expect(ctrl.userEmail).toBe("");
            expect(ctrl.userName).toBeDefined();
            expect(ctrl.userName).toBe("");
            expect(ctrl.userNameDisplay).toBeDefined();
            expect(ctrl.userNameDisplay()).toBe("");
            expect(ctrl.userPassword).toBeDefined();
            expect(ctrl.userPassword).toBe("");
            expect(ctrl.userPasswordConfirm).toBeDefined();
            expect(ctrl.userPasswordConfirm).toBe("");
            expect(ctrl.userFirstName).toBeDefined();
            expect(ctrl.userFirstName).toBe("");
            expect(ctrl.userLastName).toBeDefined();
            expect(ctrl.userLastName).toBe("");
        });

        it('Should check for non-matching passwords on User Registration', function() {
            ctrl.userPassword = 'password1';
            ctrl.userPasswordConfirm = 'password2';
            ctrl.validatePasswordFields();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Password do not match, please confirm password');
            ctrl.userPassword = 'password1';
            ctrl.userPasswordConfirm = 'password1';
            ctrl.validatePasswordFields();
            ctrl.validatePasswordFields();
            expect(scope.flash).not.toBeDefined();
        });

        it('Should handle 400 Error for invalid data on User Registration', function() {
            ctrl.userEmail = "someemail";
            ctrl.userName = "someusername";
            ctrl.userPassword = "somepassword";
            ctrl.userPasswordConfirm= "somepassword";
            ctrl.userFirstName = "firstname";
            ctrl.userLastName = "lastname";
            var user = {
                "userName": ctrl.userName,
                "userPassword": ctrl.userPassword ,
                "userFirstName": ctrl.userFirstName,
                "userLastName": ctrl.userLastName,
                "userEmail": ctrl.userEmail,
            };
            httpBackend.expectPOST(userRegisterEndpoint,user).respond(400,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.registerUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request for user registration, please make sure all required fields are valid.');

        });

        it('Should handle 409 Error for duplicate data on User Registration', function() {
            ctrl.userEmail = "someemail";
            ctrl.userName = "someusername";
            ctrl.userPassword = "somepassword";
            ctrl.userPasswordConfirm= "somepassword";
            ctrl.userFirstName = "firstname";
            ctrl.userLastName = "lastname";
            var user = {
                "userName": ctrl.userName,
                "userPassword": ctrl.userPassword ,
                "userFirstName": ctrl.userFirstName,
                "userLastName": ctrl.userLastName,
                "userEmail": ctrl.userEmail
            };
            httpBackend.expectPOST(userRegisterEndpoint,user).respond(409,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.registerUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops...User with same email address already exists please enter valid unique email address.');
        });

        it('Should handle 500 Error for server error on User Registration', function() {
            ctrl.userEmail = "someemail";
            ctrl.userName = "someusername";
            ctrl.userPassword = "somepassword";
            ctrl.userPasswordConfirm= "somepassword";
            ctrl.userFirstName = "firstname";
            ctrl.userLastName = "lastname";
            var user = {
                "userName": ctrl.userName,
                "userPassword": ctrl.userPassword ,
                "userFirstName": ctrl.userFirstName,
                "userLastName": ctrl.userLastName,
                "userEmail": ctrl.userEmail
            };
            httpBackend.expectPOST(userRegisterEndpoint,user).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.registerUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error registering new user, please retry in some time.');
        });

        it('Should handle valid User Registration ', function() {
            ctrl.userEmail = "someemail";
            ctrl.userName = "someusername";
            ctrl.userPassword = "somepassword";
            ctrl.userPasswordConfirm= "somepassword";
            ctrl.userFirstName = "firstname";
            ctrl.userLastName = "lastname";
            var user = {
                "userName": ctrl.userName,
                "userPassword": ctrl.userPassword ,
                "userFirstName": ctrl.userFirstName,
                "userLastName": ctrl.userLastName,
                "userEmail": ctrl.userEmail
            };
            var registerdUser = {
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
            httpBackend.expectPOST(userRegisterEndpoint,user).respond(200,registerdUser);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.registerUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('success');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('User registration successful.');
            expect(state).toBeDefined();
            expect(state.current.name).toBe('home');
            expect(state.current.url).toBe('/home');
            expect(state.current.templateUrl).toBe('js/home/qmehome.tmpl.html');
            expect(state.current.controller).toBe('qmeHomeCtrl');
            expect(state.current.controllerAs).toBe('qmeHomeCtrl');
        });

        it('Should have default fields on Forgot Password', function() {
            expect(ctrl.userEmail).toBeDefined();
            expect(ctrl.userEmail).toBe("");
            expect(ctrl.userPassword).toBeDefined();
            expect(ctrl.userPassword).toBe("");
            expect(ctrl.userPasswordConfirm).toBeDefined();
            expect(ctrl.userPasswordConfirm).toBe("");
        });

        it('Should check for non matching passwords on Reset Password', function() {
            ctrl.userPassword = 'password1';
            ctrl.userPasswordConfirm = 'password2';
            ctrl.validatePasswordFields();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Password do not match, please confirm password');
            ctrl.userPassword = 'password1';
            ctrl.userPasswordConfirm = 'password1';
            ctrl.validatePasswordFields();
            ctrl.validatePasswordFields();
            expect(scope.flash).not.toBeDefined();
        });

        it('Should handle 404 Error for user not found error on Submit Reset Password', function() {
            ctrl.userEmail = "someemail";
            httpBackend.expectPUT(userForgotPaswordEndpoint+ctrl.userEmail,qmeContants.reseturl).respond(404,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitReset();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Entered user email not found. Please ener valid existing user email.');
        });


        it('Should handle 500 Error for user not found error on Submit Reset Password', function() {
            ctrl.userEmail = "someemail";
            httpBackend.expectPUT(userForgotPaswordEndpoint+ctrl.userEmail,qmeContants.reseturl).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitReset();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error connecting to service for reset password, please retry in some time.');
        });

        it('Should handle valid user password submit on Submit Reset Password', function() {
            ctrl.userEmail = "someemail";
            httpBackend.expectPUT(userForgotPaswordEndpoint+ctrl.userEmail,qmeContants.reseturl).respond(200,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitReset();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('success');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('User password reset request submitted successfully, please validate your email address to complete reset.');
        });

    });

})();