(function () {

    'use strict';

    describe('Service: QMe Resource Service', function() {

        var scope, qmeUserResource, qmeContants;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function($rootScope,_qmeUserResource_,_QME_CONSTANTS_) {
            scope = $rootScope.$new();
            qmeContants = _QME_CONSTANTS_;
            qmeUserResource = _qmeUserResource_;
        }));

        it('Should have a QMe User Resource', function() {
            expect(qmeUserResource).toBeDefined();
            expect(scope.flash).not.toBeDefined();
        });

        it('Should have a User Auth Resource Defined', function() {
            expect(qmeUserResource.userAuthResource()).toBeDefined();
        });

        it('Should have a User Staging Resource Defined', function() {
            expect(qmeUserResource.userStageResource()).toBeDefined();
        });

        it('Should have a User Staging Confirm Resource Defined', function() {
            expect(qmeUserResource.userConfirmResource()).toBeDefined();
        });

        it('Should have a User Register Resource Defined', function() {
            expect(qmeUserResource.userRegisterResource()).toBeDefined();
        });

        it('Should have a User Forgot Password Resource Defined', function() {
            expect(qmeUserResource.userForgotPasswordResource()).toBeDefined();
        });

        it('Should have a User Submit Reset Password Resource Defined', function() {
            expect(qmeUserResource.userResetPasswordResource()).toBeDefined();
        });

        it('Should have a User Logout Resource Defined', function() {
            expect(qmeUserResource.logoutResource()).toBeDefined();
        });

    });

})();