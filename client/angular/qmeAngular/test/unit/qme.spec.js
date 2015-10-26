(function () {
    'use strict';

    describe('Unit: QMe App Configuration', function () {

        var qmeContants, qmeStates,rootScope,httpBackend,location,userConfirmEndpoint, window;

        beforeEach(module('qmeApp'));

        beforeEach(inject(function(_QME_CONSTANTS_,$state,$rootScope,$httpBackend,$location, $window) {
            qmeContants = _QME_CONSTANTS_;
            userConfirmEndpoint =  qmeContants.qmeservice+"/user/confirm";
            qmeStates = $state;
            rootScope = $rootScope.$new();
            httpBackend = $httpBackend;
            location = $location;
            window = $window;
        }));

        it('Unit: Check Valid QMe Constants', function () {
            spyOn(window , 'onbeforeunload');
            var event = jasmine.createSpyObj('clickEvent', ['preventDefault']);
            event.type = 'reload';
            window.onbeforeunload(event);
            rootScope.$digest();
            expect(window.onbeforeunload).toHaveBeenCalled();
            console.log('event',event);
            console.log('event',event.preventDefault);

        });

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

            var stageState = qmeStates.get('stage');
            expect(stageState).not.toBeNull();
            expect(stageState.name).toBe('stage');
            expect(stageState.url).toBe('/stage');
            expect(stageState.templateUrl).toBe('js/user/qmestageuser.tmpl.html');
            expect(stageState.controller).toBe('qmeUserCtrl');
            expect(stageState.controllerAs).toBe('qmeUserCtrl');

            var confirmState = qmeStates.get('confirmuser');
            expect(confirmState).not.toBeNull();
            expect(confirmState.name).toBe('confirmuser');
            expect(confirmState.url).toBe('/confirmuser/:stagetoken');
            expect(confirmState.templateUrl).toBe('js/home/qmehome.tmpl.html');
            expect(confirmState.controller).toBe('qmeHomeCtrl');
            expect(confirmState.controllerAs).toBe('qmeHomeCtrl');

            var userProfileState = qmeStates.get('userprofile');
            expect(userProfileState).not.toBeNull();
            expect(userProfileState.name).toBe('userprofile');
            expect(userProfileState.url).toBe('/userprofile');
            expect(userProfileState.templateUrl).toBe('js/user/qmeuserprofile.tmpl.html');
            expect(userProfileState.controller).toBe('qmeUserCtrl');
            expect(userProfileState.controllerAs).toBe('qmeUserCtrl');

            var registerState = qmeStates.get('register');
            expect(registerState).not.toBeNull();
            expect(registerState.name).toBe('register');
            expect(registerState.url).toBe('/register');
            expect(registerState.templateUrl).toBe('js/user/qmeregister.tmpl.html');
            expect(registerState.controller).toBe('qmeUserCtrl');
            expect(registerState.controllerAs).toBe('qmeUserCtrl');

            var forgotPasswordState = qmeStates.get('forgotpassword');
            expect(forgotPasswordState).not.toBeNull();
            expect(forgotPasswordState.name).toBe('forgotpassword');
            expect(forgotPasswordState.url).toBe('/forgotpassword');
            expect(forgotPasswordState.templateUrl).toBe('js/user/qmeforgotpassword.tmpl.html');
            expect(forgotPasswordState.controller).toBe('qmeUserCtrl');
            expect(forgotPasswordState.controllerAs).toBe('qmeUserCtrl');

            var resetState = qmeStates.get('resetpassword');
            expect(resetState).not.toBeNull();
            expect(resetState.name).toBe('resetpassword');
            expect(resetState.url).toBe('/resetpassword/:token/:username');
            expect(resetState.templateUrl).toBe('js/user/qmeresetpassword.tmpl.html');
            expect(resetState.controller).toBe('qmeUserCtrl');
            expect(resetState.controllerAs).toBe('qmeUserCtrl');

        });

        it('Unit: Check Valid Confirm User Success', function () {
            httpBackend.whenGET("js/home/qmehome.tmpl.html").respond(200,{});
            httpBackend.expectPOST(userConfirmEndpoint,"sometoken").respond(200,{});
            location.url("/confirmuser/sometoken");
            qmeStates.go('confirmuser');
            rootScope.$digest();
            httpBackend.flush();
            expect(rootScope.flash).toBeDefined();
            expect(rootScope.flash.type).toBeDefined();
            expect(rootScope.flash.type).toBe('success');
            expect(rootScope.flash.message).toBeDefined();
            expect(rootScope.flash.message).toBe('User registration completed successfully, please login using your credentials, Thank you.');
        });

        it('Unit: Check Error Message Confirm User Confirmation Error', function () {
            httpBackend.whenGET("js/home/qmehome.tmpl.html").respond(200,{});
            httpBackend.expectPOST(userConfirmEndpoint,"sometoken").respond(500,{});
            location.url("/confirmuser/sometoken");
            qmeStates.go('confirmuser');
            rootScope.$digest();
            httpBackend.flush();
            expect(rootScope.flash).toBeDefined();
            expect(rootScope.flash.type).toBeDefined();
            expect(rootScope.flash.type).toBe('error');
            expect(rootScope.flash.message).toBeDefined();
            expect(rootScope.flash.message).toBe('Oops.....Error confirming user registration, registration token invalid/expired, please re-try request or register.');
        });
    });

})();
