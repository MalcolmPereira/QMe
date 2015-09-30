(function () {

    'use strict';

    describe('Controller: QMe Register Controller', function() {

        var scope, state, httpBackend, ctrl, qmeContants,userRegisterEndpoint,logoutUserEndPoint;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope, $state, $controller, $httpBackend,_QME_CONSTANTS_) {
            scope = $rootScope.$new();
            state = $state;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            userRegisterEndpoint = qmeContants.qmeservice+"/user/register";
            logoutUserEndPoint =  qmeContants.qmeservice+"/logout";
            ctrl  = $controller('qmeRegisterCtrl', {
                $state: state,
                $scope: scope,
                userEmail:"",
                userName:"",
                userPassword:"",
                userPasswordConfirm:"",
                userFirstName:"",
                userLastName:""
            });
        }));


        it('Should have a qmeRegister controller', function() {
            expect(ctrl).toBeDefined();
        });

        it('Should have default fields ', function() {
            expect(ctrl.userEmail).toBeDefined();
            expect(ctrl.userEmail).toBe("");
            expect(ctrl.userName).toBeDefined();
            expect(ctrl.userName).toBe("");
            expect(ctrl.userPassword).toBeDefined();
            expect(ctrl.userPassword).toBe("");
            expect(ctrl.userPasswordConfirm).toBeDefined();
            expect(ctrl.userPasswordConfirm).toBe("");
            expect(ctrl.userFirstName).toBeDefined();
            expect(ctrl.userFirstName).toBe("");
            expect(ctrl.userLastName).toBeDefined();
            expect(ctrl.userLastName).toBe("");
        });


        it('Should check for non matching passwords ', function() {
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


        it('Should handle 400 Error for invalid data ', function() {
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


        it('Should handle 409 Error for duplicate data ', function() {
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

        it('Should handle 500 Error for server error ', function() {
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

        it('Should handle valid user registration ', function() {
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
            httpBackend.expectPOST(logoutUserEndPoint).respond(200,user);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.registerUser();
            httpBackend.flush();
            expect(scope.flash).not.toBeDefined();
            expect(state).not.toBeNull();
            expect(state.current.name).toBe('home');
            expect(state.current.url).toBe('/home');
            expect(state.current.templateUrl).toBe('js/home/qmehome.tmpl.html');
            expect(state.current.controller).toBe('qmeHomeCtrl');
            expect(state.current.controllerAs).toBe('qmeHome');
        });

    });
})();



