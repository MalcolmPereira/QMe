part of qme;

void qmeRouteInitializer(Router router, RouteViewFactory views) {
    views.configure({
        'welcome' : ngRoute(path: '/welcome', viewHtml: '<qme-main></qme-main>', defaultRoute:true),  
        'register': ngRoute(path: '/register', viewHtml: '<qme-register></qme-register>'),
        'resetPassword': ngRoute(path: '/resetPassword', viewHtml: '<qme-forgotpassword></qme-forgotpassword>')
    });
}