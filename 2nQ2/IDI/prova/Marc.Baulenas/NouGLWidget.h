#include "MyGLWidget.h"

class NouGLWidget : public MyGLWidget
{
 Q_OBJECT

 public:
  NouGLWidget (QWidget *parent=NULL);
  ~NouGLWidget ();

  float poszL = 1;
  float rotL = 0;
  bool orto = false;
  
 public slots:

 signals:

 protected:
  void paintGL ();
  void resizeGL (int w, int h);
  void iniCamera ();
  void iniEscena ();
  void viewTransform ();
  void projectTransform ();
  void modelTransformLego ();
  void modelTransformCub ();

  // keyPressEvent - Es cridat quan es prem una tecla
  void keyPressEvent (QKeyEvent *event);

 private:

};
