library qme;
import 'package:angular/angular.dart';
import 'package:crypto/crypto.dart';
import 'dart:html';
import 'dart:convert';


part 'component/qme_header.dart';
part 'component/qme_error.dart';
part 'component/qme_main.dart';
part 'component/qme_register.dart';
part 'component/qme_forgotpassword.dart';

part 'model/qme_user.dart';
part 'model/qme_error_holder.dart';

part 'routing/qme_router.dart';

class QMeAppModule extends Module {
  QMeAppModule() {
    
    bind(QMeHeaderComponent);
    bind(QMeErrorComponent);
    bind(QMeMainComponent);
    bind(QMeRegisterComponent);
    bind(QMeForgotPasswordComponent);
    
    bind(RouteInitializerFn, toValue: qmeRouteInitializer);
    bind(NgRoutingUsePushState, toValue: new NgRoutingUsePushState.value(false));
  }
}
