(function () {
    'use strict';

    describe('Unit: QMe App Configuration', function () {

        var qmeContants, qmeStates;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function(_QME_CONSTANTS_,$state) {
            qmeContants = _QME_CONSTANTS_;
            qmeStates = $state;
        }));

        it('Unit: Check Valid QMe Constants', function () {
            expect(qmeContants.success).toBe('success');
            expect(qmeContants.error).toBe('error');
            expect(qmeContants.authendpoint).toBeDefined();
            expect(qmeContants.adminrole).toBe('admin');
        });

        it('Unit: Check Valid QMe States', function () {

            var homeState = qmeStates.get('home');
            expect(homeState).not.toBeNull();
            expect(homeState.name).toBe('home');
            expect(homeState.url).toBe('/home');
            expect(homeState.templateUrl).toBe('js/home/qmehome.tmpl.html');
            expect(homeState.controller).toBe('qmeHomeCtrl');
            expect(homeState.controllerAs).toBe('qmeHome');

            var registerState = qmeStates.get('register');
            expect(registerState).not.toBeNull();
            expect(registerState.name).toBe('register');
            expect(registerState.url).toBe('/register');
            expect(registerState.templateUrl).toBe('js/register/qmeregister.tmpl.html');
            expect(registerState.controller).toBe('qmeRegisterCtrl');
            expect(registerState.controllerAs).toBe('qmeRegister');

            var resetState = qmeStates.get('reset');
            expect(resetState).not.toBeNull();
            expect(resetState.name).toBe('reset');
            expect(resetState.url).toBe('/reset');
            expect(resetState.templateUrl).toBe('js/reset/qmeforgotpassword.tmpl.html');
            expect(resetState.controller).toBe('qmeResetPasswordCtrl');
            expect(resetState.controllerAs).toBe('qmeReset');
        });
    });

})();