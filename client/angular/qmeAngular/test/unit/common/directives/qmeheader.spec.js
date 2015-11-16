(function () {

    'use strict';

    describe('Unit: QMe Header Directive', function () {

        var qmeHeader,qmeStates,compile,rootScope, headerNavctrl,qmeUserSession;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($compile, $rootScope, $state, $controller,_qmeUserSession_) {
            compile = $compile;
            rootScope = $rootScope;
            qmeStates = $state;
            qmeUserSession = _qmeUserSession_;
            headerNavctrl  = $controller('qmeNavCtrl', {
                $state: qmeStates,
                qmeUserSession:qmeUserSession
            });
        }));

        it('Should have a QMe Header Directive', function() {
            expect(compile).toBeDefined();
            expect(rootScope).toBeDefined();
            qmeHeader = compile("<qme-header></qme-header>")(rootScope);
            rootScope.$digest();
            expect(qmeHeader.html()).toContain('<span id="qmeAppHeader">QMe Application</span>');
        });

        it('Should routes to valid QMe Header Navigation States from QMe Header for not logged in User', function() {
            expect(headerNavctrl).toBeDefined();
            expect(headerNavctrl.isSignedIn()).toBe(false);
            expect(headerNavctrl.isAdmin()).toBe(false);
        });

        it('Should routes to valid QMe Header Navigation States from QMe Header for logged in User', function() {
            qmeUserSession.create(
                'someauthtoken',
                '1234' ,
                'someuser',
                'firstname',
                'lastname',
                'someuser@some.com',
                '2015-10-02T14:27:10',
                ['USER']);
            expect(headerNavctrl).toBeDefined();
            expect(headerNavctrl.isSignedIn()).toBe(true);
            expect(headerNavctrl.isAdmin()).toBe(false);
        });

        it('Should routes to valid QMe Header Navigation States from QMe Header for logged in Admin', function() {
            qmeUserSession.create(
                'someauthtoken',
                '1234' ,
                'someuser',
                'firstname',
                'lastname',
                'someuser@some.com',
                '2015-10-02T14:27:10',
                ['ADMIN']);
            expect(headerNavctrl).toBeDefined();
            expect(headerNavctrl.isSignedIn()).toBe(true);
            expect(headerNavctrl.isAdmin()).toBe(true);
        });

        it('Should routes to valid QMe States from QMe Header for admin user', function() {
            qmeUserSession.create(
                'someauthtoken',
                '1234' ,
                'someuser',
                'firstname',
                'lastname',
                'someuser@some.com',
                '2015-10-02T14:27:10',
                ['ADMIN']);

            expect(headerNavctrl).toBeDefined();
            headerNavctrl.routeHome();
            rootScope.$digest();
            expect(qmeStates).not.toBeNull();
            expect(qmeStates.current.name).toBe('home');
            expect(qmeStates.current.url).toBe('/home');
            expect(qmeStates.current.templateUrl).toBe('js/home/qmehome.tmpl.html');
            expect(qmeStates.current.controller).toBe('qmeHomeCtrl');
            expect(qmeStates.current.controllerAs).toBe('qmeHomeCtrl');

            headerNavctrl.routeUsers();
            rootScope.$digest();
            expect(qmeStates).not.toBeNull();
            expect(qmeStates.current.name).toBe('listusers');
            expect(qmeStates.current.url).toBe('/listusers');
            expect(qmeStates.current.templateUrl).toBe('js/admin/user/qmeuserlist.tmpl.html');
            expect(qmeStates.current.controller).toBe('qmeUserManagementCtrl');
            expect(qmeStates.current.controllerAs).toBe('qmeUserManagementCtrl');

            headerNavctrl.routeCategories();
            rootScope.$digest();
            expect(qmeStates).not.toBeNull();
            expect(qmeStates.current.name).toBe('listcategories');
            expect(qmeStates.current.url).toBe('/listcategories');
            expect(qmeStates.current.templateUrl).toBe('js/admin/category/qmecategorylist.tmpl.html');
            expect(qmeStates.current.controller).toBe('qmeCategoryManagementCtrl');
            expect(qmeStates.current.controllerAs).toBe('qmeCategoryManagementCtrl');

            headerNavctrl.routeQuestions();
            rootScope.$digest();
            expect(qmeStates).not.toBeNull();
            expect(qmeStates.current.name).toBe('listquestions');
            expect(qmeStates.current.url).toBe('/listquestions');
            expect(qmeStates.current.templateUrl).toBe('js/admin/question/qmequestionlist.tmpl.html');
            expect(qmeStates.current.controller).toBe('qmeQuestionManagementCtrl');
            expect(qmeStates.current.controllerAs).toBe('qmeQuestionManagementCtrl');


            headerNavctrl.routeQuizzes();
            rootScope.$digest();
            expect(qmeStates).not.toBeNull();
            expect(qmeStates.current.name).toBe('listquizzes');
            expect(qmeStates.current.url).toBe('/listquizzes');
            expect(qmeStates.current.templateUrl).toBe('js/admin/quiz/qmequizlist.tmpl.html');
            expect(qmeStates.current.controller).toBe('qmeQuizManagementCtrl');
            expect(qmeStates.current.controllerAs).toBe('qmeQuizManagementCtrl');

        });
    });


})();