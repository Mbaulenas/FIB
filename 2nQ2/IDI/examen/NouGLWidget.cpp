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
  // Activem el VAO per a pintar el Patricio
  glBindVertexArray (VAO_Pat);
  // pintem el Patricio
  modelTransformPatricio2();
  glDrawArrays(GL_TRIANGLES, 0, patModel.faces().size()*3);

  //--------------------------------------------------------
  glBindVertexArray(0);
}

void NouGLWidget::iniCamera ()
{
  //MyGLWidget::iniCamera();
  MyGLWidget::angleY = float(0.0);
  MyGLWidget::angleX = float(M_PI)/6.0;
  projectTransform ();
  viewTransform ();
}

void NouGLWidget::iniEscena ()
{
  MyGLWidget::iniEscena();
  glm::vec3 emin = glm::vec3 (0.0,0.0,0.0);
  glm::vec3 emax = glm::vec3 (8.0,4.0,8.0);
  centreEsc = (emax+emin)/glm::vec3(2.0);
  radiEsc = distance(emin,emax)/2;
  basicLlumShader::Phong();
}

void NouGLWidget::modelTransformPatricio ()
{
  //MyGLWidget::modelTransformPatricio();
  //------------------------------------

  glm::mat4 patTG = glm::mat4(1.0f);
  patTG = glm::translate(patTG, glm::vec3(2,0,1));
  patTG = glm::rotate(patTG, float(M_PI)/2, glm::vec3(0.0, 1.0, 0.0));
  patTG = glm::scale(patTG, glm::vec3(escalaPat*2, escalaPat*2, escalaPat*2));
  patTG = glm::translate(patTG, -centreBasePat);
  glUniformMatrix4fv(MyGLWidget::transLoc, 1, GL_FALSE, &patTG[0][0]);

}

void NouGLWidget::modelTransformPatricio2(){
  glm::mat4 patTG = glm::mat4(1.0f);
  patTG = glm::translate(patTG, glm::vec3(4,0,6));
  patTG = glm::rotate(patTG, float(M_PI), glm::vec3(0.0, 1.0, 0.0));
  patTG = glm::scale(patTG, glm::vec3(escalaPat*3, escalaPat*3, escalaPat*3));
  patTG = glm::translate(patTG, -centreBasePat);
  glUniformMatrix4fv(MyGLWidget::transLoc, 1, GL_FALSE, &patTG[0][0]);
}

void NouGLWidget::viewTransform() {
  //MyGLWidget::viewTransform();

  View = glm::translate(glm::mat4(1.f), glm::vec3(0, 0, -2*radiEsc));
  View = glm::rotate(View, angleX, glm::vec3(1, 0, 0));
  View = glm::rotate(View, -angleY, glm::vec3(0, 1, 0));
  View = glm::translate(View, -centreEsc);

  glUniformMatrix4fv (viewLoc, 1, GL_FALSE, &View[0][0]);
}

void NouGLWidget::projectTransform() {
  //MyGLWidget::projectTransform();

  glm::mat4 Proj;  // Matriu de projecció

  float fov = float(M_PI/3.0);
  float zn = 6;
  float zf = 18;

  Proj = glm::perspective(fov, rav, zn, zf);

  glUniformMatrix4fv (projLoc, 1, GL_FALSE, &Proj[0][0]);
}

void NouGLWidget::keyPressEvent(QKeyEvent* event)
{
  makeCurrent();
  switch (event->key()) {
    case Qt::Key_S: {

      break;
    }
    case Qt::Key_W: {

      break;
    }
    case Qt::Key_A: {

      break;
    }
    case Qt::Key_D: {

      break;
    }
    case Qt::Key_C: {

      break;
    }
    default: {
      MyGLWidget::keyPressEvent(event);
      break;
    }
  }
  update();
}

glm::vec3 Lambert (vec3 NormSCO, vec3 L)
{
    // S'assumeix que els vectors que es reben com a paràmetres estan normalitzats

    // Inicialitzem color a component ambient
    vec3 colRes = llumAmbient * matamb;

    // Afegim component difusa, si n'hi ha
    if (dot (L, NormSCO) > 0)
      colRes = colRes + colFocus * matdiff * dot (L, NormSCO);
    return (colRes);
}

glm::vec3 Phong (vec3 NormSCO, vec3 L, vec4 vertSCO)
{
    // Els vectors estan normalitzats

    // Inicialitzem color a Lambert
    vec3 colRes = Lambert (NormSCO, L);

    // Calculem R i V
    if (dot(NormSCO,L) < 0)
      return colRes;  // no hi ha component especular

    vec3 R = reflect(-L, NormSCO); // equival a: normalize (2.0*dot(NormSCO,L)*NormSCO - L);
    vec3 V = normalize(-vertSCO.xyz);

    if ((dot(R, V) < 0) || (matshin == 0))
      return colRes;  // no hi ha component especular

    // Afegim la component especular
    float shine = pow(max(0.0, dot(R, V)), matshin);
    return (colRes + matspec * colFocus * shine);
}
