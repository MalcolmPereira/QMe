part of qme;

class QmeErrorHolder {
  bool  isError;
  String errorMessage;
  
  static final QmeErrorHolder _instance = new QmeErrorHolder._internal();
  
  static QmeErrorHolder get instance => _instance;
  
  void setError(errorMessageVal){
    instance.errorMessage = errorMessageVal;
    instance.isError = true;
  }
  void removeError(){
      instance.errorMessage = "";
      instance.isError = false;
  }
  QmeErrorHolder._internal();
}
