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
            expect(qmeContants.qme_auth_header).toBe('QME-AUTH-TOKEN');
            expect(qmeContants.adminrole).toBe('ADMIN');
            expect(qmeContants.userrole).toBe('USER');
        });

        it('Unit: Check Valid QMe States', function () {

            var homeState = qmeStates.get('home');
            expect(homeState).not.toBeNull();
            expect(homeState.name).toBe('home');
            expect(homeState.url).toBe('/home');
            expect(homeState.templateUrl).toBe('js/home/qmehome.tmpl.html');
            expect(homeState.controller).toBe('qmeHomeCtrl');
            expect(homeState.controllerAs).toBe('qmeHomeCtrl');


            var registerState = qmeStates.get('register');
            expect(registerState).not.toBeNull();
            expect(registerState.name).toBe('register');
            expect(registerState.url).toBe('/register');
            expect(registerState.templateUrl).toBe('js/user/qmeregister.tmpl.html');
            expect(registerState.controller).toBe('qmeUserCtrl');
            expect(registerState.controllerAs).toBe('qmeUserCtrl');


            var resetState = qmeStates.get('forgotpassword');
            expect(resetState).not.toBeNull();
            expect(resetState.name).toBe('forgotpassword');
            expect(resetState.url).toBe('/forgotpassword');
            expect(resetState.templateUrl).toBe('js/user/qmeforgotpassword.tmpl.html');
            expect(resetState.controller).toBe('qmeUserCtrl');
            expect(resetState.controllerAs).toBe('qmeUserCtrl');

        });
    });

})();
