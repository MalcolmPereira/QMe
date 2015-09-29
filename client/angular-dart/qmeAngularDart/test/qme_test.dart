library qme_test;

import 'package:guinness/guinness_html.dart';
import 'dart:async';
import 'dart:html' as html;

import 'package:di/di.dart';
import 'package:angular/angular.dart';
import 'package:angular/mock/module.dart';
import 'package:angular/mock/test_injection.dart';

import 'package:qme/qme.dart';


part 'component/qme_header_test.dart';

main(){

  guinnessEnableHtmlMatchers();

  testHeaderComponent();

  guinness.initSpecs();
}

setUpAngular({List templates, List injectables}) {

  beforeEach(setUpInjector);

  afterEach(tearDownInjector);

  beforeEach(
      module((Module m) => injectables.forEach(m.bind))
  );

  beforeEach(loadTemplates(templates));
}

loadTemplates(List<String> templates){

  return () {

    updateCache(template, response) => inject((TemplateCache cache) => cache.put(template, response));


    final futures = templates.map((template) => html.HttpRequest.request('packages/qme/$template', method: "GET").
    then((_) => updateCache("packages/qme/$template", new HttpResponse(200, _.response))));

    return Future.wait(futures);
  };
}

compileComponent(String html, Map scopeData, callback){

  return async(inject((TestBed tb, Scope scope) {

    scopeData.forEach((k, v) => scope.context[k] = v);

    final el = tb.compile(html, scope: scope);

    microLeap();

    tb.rootScope.apply();

    callback(el, tb);
  }));
}

