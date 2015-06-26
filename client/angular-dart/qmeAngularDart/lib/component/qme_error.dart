part of qme;

@Component(
    selector: 'qme-error',
    templateUrl: 'component/qme_error.html',
    useShadowDom: false)
class QMeErrorComponent {
    @NgOneWay("qmeErrorHolder")
    QmeErrorHolder qmeErrorHolder = QmeErrorHolder.instance;
}