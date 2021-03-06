(function () {

    ngQMe

        .service('qmeUserResource',function($resource,$http,QME_CONSTANTS){
            var userAuthEndPoint            = QME_CONSTANTS.qmeservice+"/login/";
            var userAPI                     = QME_CONSTANTS.qmeservice+"/user";
            var userCountEndPoint           = userAPI+"/count";
            var userPagedEndPoint           = userAPI+"/paged";
            var userSearchEndPoint          = userAPI+"/searchemail/";
            var userStageEndpoint           = userAPI+"/stage";
            var userConfirmEndpoint         = userAPI+"/confirm";
            var userRegisterEndpoint        = userAPI+"/register";
            var userForgotPaswordEndpoint   = userAPI+"/reset/forgotpassword/";
            var userResetPaswordEndpoint    = userAPI+"/reset/resetpassword/";
            var userLogoutEndpoint          = QME_CONSTANTS.qmeservice+"/logout";

            this.userAuthResource = function(){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = undefined;
                return $resource(userAuthEndPoint);
            };
            this.userResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(userAPI);
            };
            this.userCountResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $http.get(userCountEndPoint);
            };
            this.userPagedResource = function(authToken,pageIndex,maxRows,sorttype,sortfields){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                if(sortfields){
                    return $resource(userPagedEndPoint+"?page="+pageIndex+"&pagesize="+maxRows+"&sorttype="+sorttype+"&sortfields="+sortfields);
                }else{
                    return $resource(userPagedEndPoint+"?page="+pageIndex+"&pagesize="+maxRows);
                }
            };
            this.userGetUserResource = function(userEmail){
                return $resource(userSearchEndPoint+userEmail);
            };
            this.userStageResource = function(){
                return $resource(userStageEndpoint);
            };
            this.userConfirmResource = function(){
                return $resource(userConfirmEndpoint);
            };
            this.userRegisterResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(userRegisterEndpoint);
            };
            this.userForgotPasswordResource = function(userEmail){
                return $resource(userForgotPaswordEndpoint+userEmail,null,{'reset':{method:'PUT'}});
            };
            this.userResetPasswordResource = function(userEmail){
                return $resource(userResetPaswordEndpoint+userEmail,{},{'resetpassword':{method:'PUT'}});
            };
            this.userUpdateResource = function(authToken, userId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(userAPI+"/"+userId,{},{'updateUser':{method:'PUT'}});
            };
            this.userDeleteResource = function(authToken, userId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(userAPI+"/"+userId,{},{'deleteUser':{method:'DELETE'}});
            };
            this.logoutResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(userLogoutEndpoint);
            };
        })

        .service('qmeCategoryResource',function($resource,$http,QME_CONSTANTS){
            var categoryAPI               = QME_CONSTANTS.qmeservice+"/category";
            var categoryCountEndPoint     = categoryAPI+"/count";
            var categoryByParentEndPoint  = categoryAPI+"/parent";
            var categorySearchEndPoint    = categoryAPI+"/search/";
            var categoryWithQuestionsEndPoint = categoryAPI+"/questions";

            this.categoryResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(categoryAPI);
            };

            this.categoryWithQuestionsResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(categoryWithQuestionsEndPoint);
            };

            this.categoryUpdateResource = function(authToken, categoryId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(categoryAPI+"/"+categoryId,{},{'updateCategory':{method:'PUT'}});
            };
            this.categoryDeleteResource = function(authToken, categoryId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(categoryAPI+"/"+categoryId,{},{'deleteCategory':{method:'DELETE'}});
            };
            this.categoryCountResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $http.get(categoryCountEndPoint);
            };
            this.categoryByParentResource = function(authToken, parentId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(categoryByParentEndPoint+"/"+parentId);

            };
            this.categorySearchResource = function(authToken, searchStr){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(categorySearchEndPoint+"/"+searchStr);
            };

        })

        .service('qmeQuestionResource',function($resource,$http,QME_CONSTANTS){

            var questionAPI                = QME_CONSTANTS.qmeservice+"/question";
            var questionCountEndPoint      = questionAPI+"/count";
            var questionPagedEndPoint      = questionAPI+"/paged";
            var questionByCategoryEndPoint = questionAPI+"/category/";

            this.questionResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(questionAPI);
            };

            this.questionCountResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $http.get(questionCountEndPoint);
            };

            this.questionByCategoryCountResource = function(authToken,categoryId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $http.get(questionByCategoryEndPoint+categoryId+"/count");
            };

            this.questionPagedResource = function(authToken,pageIndex,maxRows,sorttype,sortfields){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                if(sortfields){
                    return $resource(questionPagedEndPoint+"?page="+pageIndex+"&pagesize="+maxRows+"&sorttype="+sorttype+"&sortfields="+sortfields);
                }else{
                    return $resource(questionPagedEndPoint+"?page="+pageIndex+"&pagesize="+maxRows);
                }
            };

            this.questionByCategoryIDPagedResource = function(authToken,categoryId,pageIndex,maxRows,sorttype,sortfields){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                if(sortfields){
                    return $resource(questionByCategoryEndPoint+categoryId+"/paged?page="+pageIndex+"&pagesize="+maxRows+"&sorttype="+sorttype+"&sortfields="+sortfields);
                }else{
                    return $resource(questionByCategoryEndPoint+categoryId+"/paged?page="+pageIndex+"&pagesize="+maxRows);
                }
            };

            this.questionByIdResource = function(authToken, questionId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(questionAPI+"/"+questionId);
            };

            this.questionUpdateResource = function(authToken, questionId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(questionAPI+"/"+questionId,{},{'updateQuestion':{method:'PUT'}});
            };

            this.questionDeleteResource = function(authToken, questionId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(questionAPI+"/"+questionId,{},{'deleteQuestion':{method:'DELETE'}});
            };
        })


        .service('qmeQuizResource',function($resource,$http,QME_CONSTANTS){
            var quizAPI                   = QME_CONSTANTS.qmeservice+"/quiz";
            var quizCountEndPoint         = quizAPI+"/count";
            var quizPagedEndPoint         = quizAPI+"/paged";

            this.quizResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(quizAPI);
            };
            this.quizCountResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $http.get(quizCountEndPoint);
            };
            this.quizPagedResource = function(authToken,pageIndex,maxRows,sorttype,sortfields){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                if(sortfields){
                    return $resource(quizPagedEndPoint+"?page="+pageIndex+"&pagesize="+maxRows+"&sorttype="+sorttype+"&sortfields="+sortfields);
                }else{
                    return $resource(quizPagedEndPoint+"?page="+pageIndex+"&pagesize="+maxRows);
                }
            };
            this.quizByIdResource = function(authToken, quizId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(quizAPI+"/"+quizId);
            };
            this.quizUpdateResource = function(authToken, quizId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(quizAPI+"/"+quizId,{},{'updateQuiz':{method:'PUT'}});
            };
            this.quizDeleteResource = function(authToken, quizId){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(quizAPI+"/"+quizId,{},{'deleteQuiz':{method:'DELETE'}});
            };

        });
})();