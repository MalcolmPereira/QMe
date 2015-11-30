(function () {

    "use strict";

    describe('Controller: QMe Admin User Management Controller', function() {

        var rootScope,
            scope,
            state,
            stateParams,
            httpBackend,
            ctrl,
            qmeContants,
            qmeUserService,
            qmePageSession,
            qmeFlashService,
            userCountEndPoint,
            userPagedEndPoint,
            userApiEndPoint,
            timeout
            ;

        beforeEach(module('qmeApp'));

        beforeEach(module('qmeApp.templates'));

        beforeEach(inject(function($rootScope,$state,$stateParams, $controller,$httpBackend,_QME_CONSTANTS_,_qmeUserService_,_qmePageSession_, _qmeFlashService_,_$timeout_) {
            rootScope = $rootScope;
            scope = $rootScope.$new();
            state = $state;
            stateParams = $stateParams;
            httpBackend = $httpBackend;
            qmeContants = _QME_CONSTANTS_;
            userPagedEndPoint = qmeContants.qmeservice+"/user/paged";
            userCountEndPoint  = qmeContants.qmeservice+"/user/count";
            userApiEndPoint = qmeContants.qmeservice+"/user";
            qmeUserService = _qmeUserService_;
            qmePageSession = _qmePageSession_;
            qmeFlashService = _qmeFlashService_;
            timeout = _$timeout_;
            stateParams.sortasc = true;
            stateParams.sortfields = "USERNAME";
            stateParams.currentpage = 1;
            ctrl  = $controller('qmeUserManagementCtrl', {
                $scope: scope,
                $state:state,
                $stateParams:stateParams,
                qmeFlashService: qmeFlashService,
                qmeUserService: qmeUserService,
                qmePageSession: qmePageSession,
                userPassword: '',
                userPasswordConfirm: '',
                userId: '',
                userName: '',
                userEmail: '',
                userFirstName: '',
                userLastName: '',
                userRole: undefined,
                reviewerRole: undefined,
                moderatorRole: undefined,

        });
        }));


        it('Should have a QMe User Management controller and display valid not authorized message for count user ', function() {
            expect(ctrl).toBeDefined();
            expect(ctrl.users).not.toBeDefined();
            expect(ctrl.usercount).toBe(0);
            expect(ctrl.currentpage).toBe(0);
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
            httpBackend.expectGET(userCountEndPoint).respond(403,{});
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(200,[]);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.listUsers();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....User not authorized for function, please contact system administrator.');
        });


        it('Should have a QMe User Management controller and display valid server message for count user ', function() {
            expect(ctrl).toBeDefined();
            expect(ctrl.users).not.toBeDefined();
            expect(ctrl.usercount).toBe(0);
            expect(ctrl.currentpage).toBe(0);
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
            httpBackend.expectGET(userCountEndPoint).respond(500,{});
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(200,[]);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.listUsers();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error from service for getting user count, please retry in some time.');
        });


        it('Should have a QMe User Management controller and display valid not authorized message for list user ', function() {
            expect(ctrl).toBeDefined();
            expect(ctrl.users).not.toBeDefined();
            expect(ctrl.usercount).toBe(0);
            expect(ctrl.currentpage).toBe(0);
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
            httpBackend.expectGET(userCountEndPoint).respond(200,3);
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(403,[]);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.listUsers();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....User not authorized for function, please contact system administrator.');
        });


        it('Should have a QMe User Management controller and display valid server message for list user ', function() {
            expect(ctrl).toBeDefined();
            expect(ctrl.users).not.toBeDefined();
            expect(ctrl.usercount).toBe(0);
            expect(ctrl.currentpage).toBe(0);
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
            httpBackend.expectGET(userCountEndPoint).respond(200,3);
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(500,[]);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.listUsers();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error from service getting user lists, please retry in some time.');
        });


        it('Should have a QMe User Management controller and return set users on init', function() {
            expect(ctrl).toBeDefined();
            expect(ctrl.users).not.toBeDefined();
            expect(ctrl.usercount).toBe(0);
            expect(ctrl.currentpage).toBe(0);
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
            expect(ctrl.totalRecords()).toBe(0);
            expect(ctrl.recordsLoaded()).toBe(false);
            expect(ctrl.isSortAsc("USERNAME")).toBe(true);
            expect(ctrl.isSortDesc("USERNAME")).toBe(false);

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

            var res = {"content": 3};
            httpBackend.expectGET(userCountEndPoint).respond(200,res);
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.listUsers();
            httpBackend.flush();

            expect(ctrl.users).toBeDefined();
            expect(ctrl.users.length).toBe(3);
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
            expect(ctrl.usercount).toBe(3);
            expect(ctrl.currentpage).toBe(0);
            expect(ctrl.totalRecords()).toBe(3);
            expect(ctrl.recordsLoaded()).toBe(true);
        });

        it('Should return valid not authorized message on page users ', function() {
            expect(ctrl).toBeDefined();
            expect(ctrl.users).not.toBeDefined();
            expect(ctrl.usercount).toBe(0);
            expect(ctrl.currentpage).toBe(0);
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(403,[]);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.pageUsers(0);
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....User not authorized for function, please contact system administrator.');
        });


        it('Should return valid server error message on page users ', function() {
            expect(ctrl).toBeDefined();
            expect(ctrl.users).not.toBeDefined();
            expect(ctrl.usercount).toBe(0);
            expect(ctrl.currentpage).toBe(0);
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(500,[]);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.pageUsers(0);
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error from service getting user lists, please retry in some time.');
        });


        it('Should return valid user lists on page users ', function() {
            expect(ctrl).toBeDefined();
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
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.pageUsers(0);
            httpBackend.flush();
            expect(ctrl.users).toBeDefined();
            expect(ctrl.users.length).toBe(3);
        });


        it('Should return valid user lists on page users with Sorting Fields  ', function() {
            expect(ctrl).toBeDefined();
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
            stateParams.sortasc = true;
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
            expect(ctrl.isSortAsc("USERNAME")).toBe(true);
            expect(ctrl.isSortDesc("USERNAME")).toBe(false);
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.setSortField("USERNAME");
            httpBackend.flush();
            expect(ctrl.users).toBeDefined();
            expect(ctrl.users.length).toBe(3);

            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=false&sortfields=USERNAME").respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.sortDesc("USERNAME");
            httpBackend.flush();
            expect(ctrl.users).toBeDefined();
            expect(ctrl.users.length).toBe(3);

            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.sortAsc("USERNAME");
            httpBackend.flush();
            expect(ctrl.users).toBeDefined();
            expect(ctrl.users.length).toBe(3);
        });

        it('Should route to update user ', function() {
            expect(ctrl).toBeDefined();
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
            stateParams.sortasc = true;
            expect(ctrl.sortasc).toBe(true);
            expect(ctrl.sortfields).toBe("USERNAME");
            expect(ctrl.isSortAsc("USERNAME")).toBe(true);
            expect(ctrl.isSortDesc("USERNAME")).toBe(false);
            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.setSortField("USERNAME");
            httpBackend.flush();
            expect(ctrl.users).toBeDefined();
            expect(ctrl.users.length).toBe(3);

            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=false&sortfields=USERNAME").respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.sortDesc("USERNAME");
            httpBackend.flush();
            expect(ctrl.users).toBeDefined();
            expect(ctrl.users.length).toBe(3);

            httpBackend.expectGET(userPagedEndPoint+"?page="+0+"&pagesize="+qmeContants.rowsperpage+"&sorttype=true&sortfields=USERNAME").respond(200,userList);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.sortAsc("USERNAME");
            httpBackend.flush();
            expect(ctrl.users).toBeDefined();
            expect(ctrl.users.length).toBe(3);

            var qmeuser = {
                "userID": 3,
                "userName": "testuser3",
                "userPassword": null,
                "userFirstName": "Test3",
                "userLastName": "User3",
                "userEmail": "test3.user@gmail.com",
                "userRegisteredDate": "2015-28-05 13:35:29",
                "userLastLoginDate": "2015-28-05 13:35:29",
                "userRoles": ['USER']
            };
            ctrl.updateUser(qmeuser);
        });

        it('Should validate password fields ', function() {
            ctrl.userPassword = 'test';
            ctrl.userPasswordConfirm = 'test';
            ctrl.validatePasswordFields();
            expect(scope.flash).not.toBeDefined();

            ctrl.userPassword = 'test';
            ctrl.userPasswordConfirm = 'testtest';
            ctrl.validatePasswordFields();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Password do not match, please confirm password');
        });

        it('Should handle valid errors for submit update user request', function() {
            ctrl.userId = '1';
            ctrl.userName = 'test';
            ctrl.userEmail = 'email';
            ctrl.userFirstName = 'firstname';
            ctrl.userLastName = 'lastname';

            var updateUser = {
                "userId": '1',
                "userName": 'test',
                "userEmail": 'email',
                "userFirstName":  'firstname',
                "userLastName": 'lastname',
                "userRoles": ['USER']
            };
            httpBackend.expectPUT(userApiEndPoint+"/1",updateUser).respond(404,updateUser);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdateUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request for user update, user not found.');

            httpBackend.expectPUT(userApiEndPoint+"/1",updateUser).respond(403,updateUser);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdateUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....User not authorized to update user update.');

            httpBackend.expectPUT(userApiEndPoint+"/1",updateUser).respond(400,updateUser);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdateUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request for user update.');

            httpBackend.expectPUT(userApiEndPoint+"/1",updateUser).respond(500,updateUser);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdateUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Error updating user, please retry in some time.');
        });

        it('Should submit update user request', function() {
            ctrl.userId = '1';
            ctrl.userName = 'test';
            ctrl.userEmail = 'email';
            ctrl.userFirstName = 'firstname';
            ctrl.userLastName = 'lastname';

            var updateUser = {
                "userId": '1',
                "userName": 'test',
                "userEmail": 'email',
                "userFirstName":  'firstname',
                "userLastName": 'lastname',
                "userRoles": ['USER']
            };
            httpBackend.expectPUT(userApiEndPoint+"/1",updateUser).respond(200,updateUser);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdateUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('success');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('User profile update successful.');


            updateUser = {
                "userId": '1',
                "userName": 'test',
                "userEmail": 'email',
                "userFirstName":  'firstname',
                "userLastName": 'lastname',
                "userRoles": ['USER','REVIEWER','MODERATOR']
            };

            ctrl.userRole  = true;
            ctrl.reviewerRole = true;
            ctrl.moderatorRole = true;
            httpBackend.expectPUT(userApiEndPoint+"/1",updateUser).respond(200,updateUser);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitUpdateUser();
            httpBackend.flush();
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('User profile update successful.');
        });

        it('Should handle error for submit add user request', function() {
            ctrl.userName = 'test';
            ctrl.userPassword = 'test';
            ctrl.userEmail = 'email';
            ctrl.userFirstName = 'firstname';
            ctrl.userLastName = 'lastname';
            var newUser = {
                "userName": 'test',
                "userPassword": 'test',
                "userFirstName":  'firstname',
                "userLastName": 'lastname',
                "userEmail": 'email'
            };
            httpBackend.expectPOST(userApiEndPoint+"/register",newUser).respond(400,newUser);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitAddUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Invalid request for user registration, please make sure all required fields are valid.');

            httpBackend.expectPOST(userApiEndPoint+"/register",newUser).respond(409,newUser);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitAddUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops...User with same email address already exists please enter valid unique email address.');

            httpBackend.expectPOST(userApiEndPoint+"/register",newUser).respond(500,newUser);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitAddUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('error');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('Oops.....Server Error updating new user, please retry in some time.');
        });

        it('Should submit add user request', function() {
            ctrl.userName = 'test';
            ctrl.userPassword = 'test';
            ctrl.userEmail = 'email';
            ctrl.userFirstName = 'firstname';
            ctrl.userLastName = 'lastname';
            var newUser = {
                "userName": 'test',
                "userPassword": 'test',
                "userFirstName":  'firstname',
                "userLastName": 'lastname',
                "userEmail": 'email'
            };
            httpBackend.expectPOST(userApiEndPoint+"/register",newUser).respond(200,newUser);
            httpBackend.whenGET(/js\//).respond(200,{});
            ctrl.submitAddUser();
            httpBackend.flush();
            expect(scope.flash).toBeDefined();
            expect(scope.flash.type).toBeDefined();
            expect(scope.flash.type).toBe('success');
            expect(scope.flash.message).toBeDefined();
            expect(scope.flash.message).toBe('User registration completed successfully.');

        });

    });

})();