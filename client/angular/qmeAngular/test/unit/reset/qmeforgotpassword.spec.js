(function () {

    'use strict';

    describe('Controller: QMe Reset Controller', function() {

        var scope, state, httpBackend, ctrl, qmeContants;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope, $state, $controller, $httpBackend,_QME_CONSTANTS_) {
            scope = $rootScope.$new();
            state = $state;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            ctrl  = $controller('qmeResetPasswordCtrl', {
                $state: state,
                $scope: scope,
                userEmail:"",
                userPassword:"",
                userPasswordConfirm:""
            });
        }));

        it('Should have a qmeResetPassword controller', function() {
            expect(ctrl).toBeDefined();
        });

        it('Should have default fields ', function() {
            expect(ctrl.userEmail).toBeDefined();
            expect(ctrl.userEmail).toBe("");
            expect(ctrl.userPassword).toBeDefined();
            expect(ctrl.userPassword).toBe("");
            expect(ctrl.userPasswordConfirm).toBeDefined();
            expect(ctrl.userPasswordConfirm).toBe("");
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

        it('Should handle 404 Error for user not found error ', function() {
            ctrl.userEmail = "someemail";
            httpBackend.expectPUT(qmeContants.serviceurl+qmeContants.userapi+"reset/forgotpassword/"+ctrl.userEmail,qmeContants.reseturl).respond(404,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitReset();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Entered user email not found. Please ener valid existing user email.');
        });

        it('Should handle 500 Error for user not found error ', function() {
            ctrl.userEmail = "someemail";
            httpBackend.expectPUT(qmeContants.serviceurl+qmeContants.userapi+"reset/forgotpassword/"+ctrl.userEmail,qmeContants.reseturl).respond(500,{});
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitReset();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error connecting to service for reset password, please retry in some time.');
        });

        it('Should handle valid user password reset submit request', function() {
            ctrl.userEmail = "someemail";
            httpBackend.expectPUT(qmeContants.serviceurl+qmeContants.userapi+"reset/forgotpassword/"+ctrl.userEmail,qmeContants.reseturl).respond(200,{});
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




