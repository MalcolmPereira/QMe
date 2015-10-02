(function () {

    ngQMe

        .service('qmeUserResource',function($resource,QME_CONSTANTS){
            var userAPI                     = QME_CONSTANTS.qmeservice+"/user";
            var userAuthEndPoint            = QME_CONSTANTS.qmeservice+"/login/";
            var userSearchEndPoint          = userAPI+"/searchemail/";
            var userStageEndpoint           = userAPI+"/stage";
            var userConfirmEndpoint         = userAPI+"/confirm";
            var userRegisterEndpoint        = userAPI+"/register";
            var userForgotPaswordEndpoint   = userAPI+"/reset/forgotpassword/";
            var userResetPaswordEndpoint    = userAPI+"/reset/resetpassword/";
            var userLogoutEndpoint          = QME_CONSTANTS.qmeservice+"/logout";

            this.userAuthResource = function(){
                return $resource(userAuthEndPoint);
            };
            this.userGetUserResource = function(userEmail){
                return $resource(userSearchEndPoint);
            };
            this.userStageResource = function(){
                return $resource(userStageEndpoint);
            };
            this.userConfirmResource = function(){
                return $resource(userConfirmEndpoint);
            };
            this.userRegisterResource = function(){
                return $resource(userRegisterEndpoint);
            };
            this.userForgotPasswordResource = function(userEmail){
                return $resource(userForgotPaswordEndpoint+userEmail,null,{'reset':{method:'PUT'}});
            };
            this.userResetPasswordResource = function(userEmail){
                return $resource(userResetPaswordEndpoint+userEmail,{},{'resetpassword':{method:'PUT'}});
            };
            this.logoutResource = function(){
                return $resource(userLogoutEndpoint);
            }
        })

})();