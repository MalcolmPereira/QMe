(function () {

	"use strict";

    describe('Controller: QMe Admin User Management Controller', function() {

        var rootScope,
            state,
            httpBackend,
            ctrl,
            qmeContants,
            qmeUserService,
            qmePageSession,
            qmeFlashService,
            userCountEndPoint,
            userPagedEndPoint
            ;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($rootScope,$state, $controller,$httpBackend,_QME_CONSTANTS_,_qmeUserService_,_qmePageSession_, _qmeFlashService_) {
            rootScope = $rootScope;
            state = $state;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            userPagedEndPoint = qmeContants.qmeservice+"/user/paged";
            userCountEndPoint  = qmeContants.qmeservice+"/user/count";
            qmeUserService = _qmeUserService_;
            qmePageSession = _qmePageSession_;
            qmeFlashService = _qmeFlashService_;
            ctrl  = $controller('qmeUserManagementCtrl', {
                $state:state,
                qmeFlashService: qmeFlashService,
                qmeUserService: qmeUserService,
                qmePageSession: qmePageSession,
            });
        }));

        it('Should have a QMe User Management controller', function() {
            expect(ctrl).toBeDefined();
            expect(ctrl.users).not.toBeDefined();
            expect(ctrl.usercount).toBe(0);
            expect(ctrl.currentpage).toBe(0);
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
            var userList = [
                {
                    "userID": 1,
                    "userName": "testuser1",
                    "userPassword": null,
                    "userFirstName": "Test1",
                    "userLastName": "User1",
                    "userEmail": "test1.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                },
                {
                    "userID": 2,
                    "userName": "testuser2",
                    "userPassword": null,
                    "userFirstName": "Test2",
                    "userLastName": "User2",
                    "userEmail": "test2.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                },
                {
                    "userID": 3,
                    "userName": "testuser3",
                    "userPassword": null,
                    "userFirstName": "Test3",
                    "userLastName": "User3",
                    "userEmail": "test3.user@gmail.com",
                    "userRegisteredDate": "2015-28-05 13:35:29",
                    "userLastLoginDate": "2015-28-05 13:35:29",
                    "userRoles": ['USER']
                }
            ];
            httpBackend.expectGET(userCountEndPoint).respond(200,3);
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.listUsers();
            httpBackend.flush();
            expect(ctrl.users).toBeDefined();
            expect(ctrl.users.length).toBe(3);
            expect(ctrl.usercount).toBe(3);
            expect(ctrl.currentpage).toBe(0);
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
        });

    });

})();