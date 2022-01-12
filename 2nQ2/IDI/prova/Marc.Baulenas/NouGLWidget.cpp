#include "NouGLWidget.h"

#include <iostream>

NouGLWidget::NouGLWidget(QWidget *parent) : MyGLWidget(parent) {
  grabKeyboard();
}

NouGLWidget::~NouGLWidget ()
{
}

void NouGLWidget::paintGL() {
  MyGLWidget::paintGL();
}

void NouGLWidget::resizeGL(int w, int h) {

  MyGLWidget::resizeGL(w, h);
}

void NouGLWidget::iniCamera ()
{
  //MyGLWidget::iniCamera();

  glm::vec3 posFocus = glm::vec3(4,2.5,1);
  glUniform3fv(posFocusLoc, 1, &posFocus[0]);

  glm::vec3 colFocus = glm::vec3(0.8, 0.8, 0.8);
  glUniform3fv(colFocusLoc, 1, &colFocus[0]);

  rav = 1.0;
  MyGLWidget::angleY = 0.0;
  MyGLWidget::angleX = M_PI/6.0;

  projectTransform ();
  viewTransform ();
}

void NouGLWidget::iniEscena ()
{
  MyGLWidget::iniEscena();
  glm::vec3 emin = glm::vec3 (0.0,0.0,0.0);
  glm::vec3 emax = glm::vec3 (8.0,3.0,6.0);
  centreEsc = (emax+emin)/glm::vec3(2.0);
  radiEsc = distance(emin,emax)/2;


}

void NouGLWidget::modelTransformLego ()
{
  //MyGLWidget::modelTransformLego();
  glm::mat4 legoTG = glm::mat4(1.0f);
  legoTG = glm::translate(legoTG, glm::vec3(4,0,poszL));
  legoTG = glm::scale(legoTG, glm::vec3(1.5*escalaLego, 1.5*escalaLego,1.5*escalaLego));
  legoTG = glm::rotate(legoTG, rotL, glm::vec3(0, 1, 0));
  legoTG = glm::translate(legoTG, -centreBaseLego);
  glUniformMatrix4fv(transLoc, 1, GL_FALSE, &legoTG[0][0]);
}

void NouGLWidget::modelTransformCub ()
{
  //MyGLWidget::modelTransformCub();
  glm::mat4 cubTG(1.f);
  cubTG = glm::translate(cubTG, glm::vec3(4,0,5.5));
  cubTG = glm::scale(cubTG, glm::vec3(6,3,0.5));
  glUniformMatrix4fv (transLoc, 1, GL_FALSE, &cubTG[0][0]);
}

void NouGLWidget::viewTransform() {
  //MyGLWidget::viewTransform();
if(!orto){
    View = glm::translate(glm::mat4(1.f), glm::vec3(0, 0, -2*radiEsc));
    View = glm::rotate(View, angleX, glm::vec3(1, 0, 0));
    View = glm::rotate(View, -angleY, glm::vec3(0, 1, 0));
    View = glm::translate(View, -centreEsc);
}
else{
    View = glm::translate(glm::mat4(1.f), glm::vec3(0, 0, -2*radiEsc));
    //View = glm::rotate(View, -float(M_PI)/2, glm::vec3(0, 1, 0));
    View = glm::rotate(View, angleX, glm::vec3(1, 0, 0));
    View = glm::rotate(View, -float(M_PI)/2, glm::vec3(0, 1, 0));
    View = glm::translate(View, -centreEsc);
}

  glUniformMatrix4fv (viewLoc, 1, GL_FALSE, &View[0][0]);
}

void NouGLWidget::projectTransform() {
  //MyGLWidget::projectTransform();

  glm::mat4 Proj;

  float fov = float(M_PI/3.0);
  float zn = radiEsc;
  float zf = 3*radiEsc;

  if(orto) Proj = glm::ortho(-radiEsc, radiEsc, -radiEsc, radiEsc, zn, zf);
  else Proj = glm::perspective(fov, rav, zn, zf);

  glUniformMatrix4fv (projLoc, 1, GL_FALSE, &Proj[0][0]);
}

void NouGLWidget::keyPressEvent(QKeyEvent* event)
{
  makeCurrent();
  switch (event->key()) {
    case Qt::Key_W: {
      if(poszL<4.5)poszL +=0.5;

      if(poszL==4.5){
        glm::vec3 colFocus = glm::vec3(0, 1, 1);
        glUniform3fv(colFocusLoc, 1, &colFocus[0]);
      }
      else{
        glm::vec3 colFocus = glm::vec3(0.8, 0.8, 0.8);
        glUniform3fv(colFocusLoc, 1, &colFocus[0]);
      }
      break;
    }
    case Qt::Key_S: {
      if(poszL>0.0)poszL -=0.5;
      if(poszL!=4.5){
        glm::vec3 colFocus = glm::vec3(0.8, 0.8, 0.8);
        glUniform3fv(colFocusLoc, 1, &colFocus[0]);
      }
      break;
    }
    case Qt::Key_R: {
      rotL+=float(M_PI);
      break;
    }
    case Qt::Key_C: {
      orto = !orto;
      projectTransform();
      viewTransform();
      break;
    }
    default: {
      MyGLWidget::keyPressEvent(event);
      break;
    }
  }
  update();
}
