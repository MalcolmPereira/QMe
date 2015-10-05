(function () {

    ngQMe

        .service('qmeUserResource',function($resource,$http,QME_CONSTANTS){
            var userAPI                     = QME_CONSTANTS.qmeservice+"/user";
            var userAuthEndPoint            = QME_CONSTANTS.qmeservice+"/login/";
            var userSearchEndPoint          = userAPI+"/searchemail/";
            var userStageEndpoint           = userAPI+"/stage";
            var userConfirmEndpoint         = userAPI+"/confirm";
            var userRegisterEndpoint        = userAPI+"/register";
            var userForgotPaswordEndpoint   = userAPI+"/reset/forgotpassword/";
            var userResetPaswordEndpoint    = userAPI+"/reset/resetpassword/";
            var userLogoutEndpoint          = QME_CONSTANTS.qmeservice+"/logout";

            this.userResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(userAPI);
            };
            this.userAuthResource = function(){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = undefined;
                return $resource(userAuthEndPoint);
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
            this.logoutResource = function(authToken){
                $http.defaults.headers.common[QME_CONSTANTS.qme_auth_header] = authToken;
                return $resource(userLogoutEndpoint);
            }
        })



})();