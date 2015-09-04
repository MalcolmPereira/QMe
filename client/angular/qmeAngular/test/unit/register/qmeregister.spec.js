(function () {

    'use strict';

    describe('Controller: QMe Register Controller', function() {

        var scope, state, httpBackend, ctrl, qmeContants;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope, $state, $controller, $httpBackend,_QME_CONSTANTS_) {
            scope = $rootScope.$new();
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            ctrl  = $controller('qmeRegisterCtrl', {
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
            ctrl.userPassword = 'password1'
            ctrl.userPasswordConfirm = 'password2'
            ctrl.validatePasswordFields();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Password do not match, please confirm password');
            ctrl.userPassword = 'password1'
            ctrl.userPasswordConfirm = 'password1'
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
            }
            httpBackend.expectPOST(qmeContants.serviceurl+qmeContants.userapi+"register",user).respond(400,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.registerUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request for user registration, please make sure all required fields are valid.');

        });

    });
})();



