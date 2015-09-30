(function () {

    ngQMe

        .service('qmeUserResource',function($http,$resource,$cookies,QME_CONSTANTS){
            var userAPI                     = QME_CONSTANTS.qmeservice+"/user";
            var userAuthEndPoint            = userAPI+"/searchemail/";
            var userStageEndpoint           = userAPI+"/stage";
            var userConfirmEndpoint         = userAPI+"/confirm";
            var userRegisterEndpoint        = userAPI+"/register";
            var userForgotPaswordEndpoint   = userAPI+"/reset/forgotpassword/";
            var userResetPaswordEndpoint    = userAPI+"/reset/resetpassword/";
            var userLogoutEndpoint          = QME_CONSTANTS.qmeservice+"/logout";

            this.userAuthResource = function(authToken,userEmail){
                $http.defaults.headers.common.Authorization = authToken;
                return $resource(userAuthEndPoint+userEmail);
            };
            this.userStageResource = function(){
                $http.defaults.headers.common.Authorization = undefined;
                return $resource(userStageEndpoint);
            };
            this.userConfirmResource = function(){
                $http.defaults.headers.common.Authorization = undefined;
                return $resource(userConfirmEndpoint);
            };
            this.userRegisterResource = function(authToken){
                $http.defaults.headers.common.Authorization = authToken;
                $http.defaults.headers.common['XSRF-TOKEN'] = $cookies.get("XSRF-TOKEN");
                console.log('$cookies.get("XSRF-TOKEN")',$cookies.get("XSRF-TOKEN"));

                $http.defaults.headers.common['Malcolm'] = "test";
                return $resource(userRegisterEndpoint);
            };
            this.userForgotPasswordResource = function(userEmail){
                $http.defaults.headers.common.Authorization = undefined;
                return $resource(userForgotPaswordEndpoint+userEmail,null,{'reset':{method:'PUT'}});
            };
            this.userResetPasswordResource = function(userEmail){
                $http.defaults.headers.common.Authorization = undefined;
                return $resource(userResetPaswordEndpoint+userEmail,{},{'resetpassword':{method:'PUT'}});
            };
            this.logoutResource = function(){
                $http.defaults.headers.common.Authorization = undefined;
                return $resource(userLogoutEndpoint);
            }
        })

})();