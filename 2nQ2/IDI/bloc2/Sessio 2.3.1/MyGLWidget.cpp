#include "MyGLWidget.h"

#include <iostream>

MyGLWidget::MyGLWidget (QWidget* parent) : QOpenGLWidget(parent), program(NULL)
{
  setFocusPolicy(Qt::StrongFocus);  // per rebre events de teclat
  scale = 1.0f;
}

MyGLWidget::~MyGLWidget ()
{
  if (program != NULL)
    delete program;
}

void MyGLWidget::initializeGL ()
{
  // Cal inicialitzar l'ús de les funcions d'OpenGL
  initializeOpenGLFunctions();
  glClearColor(0.5, 0.7, 1.0, 1.0); // defineix color de fons (d'esborrat)
  carregaShaders();
  creaBuffers();
  glEnable(GL_DEPTH_TEST);

  calculaCapsaModel();
  calculaCapsaEscena();
  calculCentreEscena();
  calculRadiEscena();
  ini_camera();
}

void MyGLWidget::paintGL ()
{
// Aquest codi és necessari únicament per a MACs amb pantalla retina.
#ifdef __APPLE__
  GLint vp[4];
  glGetIntegerv (GL_VIEWPORT, vp);
  ample = vp[2];
  alt = vp[3];
#endif

// En cas de voler canviar els paràmetres del viewport, descomenteu la crida següent i
// useu els paràmetres que considereu (els que hi ha són els de per defecte)
//  glViewport (0, 0, ample, alt);

  // Esborrem el frame-buffer
  glClear (GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);


  // Carreguem la transformació de model
  modelTransform ();
  projectTransform();
  viewTransform();
  // Activem el VAO per a pintar la caseta
  glBindVertexArray (VAO_HomerProves);
  // pintem
  glDrawArrays(GL_TRIANGLES, 0, HomerProves.faces().size()*3);

  terraTransform();
  glBindVertexArray (VAO_Terra);
  glDrawArrays(GL_TRIANGLES, 0, 6);
  glBindVertexArray (0);
}

void MyGLWidget::ini_camera ()
{
  // distancia pot ser valor qualsevol que peremti veure tota lescena
  distancia = radi * 2.0;
  Znear = distancia - radi;
  Zfar = distancia + radi;
  raw = 1.0f;
  FOVini = 2.0 * asin(radi / distancia);
  FOV = FOVini;

  OBS = glm::vec3(0,2.0,distancia);
  VRP = glm::vec3(centreEscena);
  UP = glm::vec3(0,1,0);

  projectTransform();
  viewTransform();
}

void MyGLWidget::modelTransform ()
{
  // Matriu de transformació de model
  glm::mat4 transform (1.0f);
  transform = glm::rotate(transform, rot, glm::vec3(0,1,0));
  transform = glm::scale(transform, glm::vec3(scale));
  transform = glm::translate(transform, -centreBasePatricio);
  glUniformMatrix4fv(transLoc, 1, GL_FALSE, &transform[0][0]);

}

void MyGLWidget::terraTransform ()
{
  // Matriu de transformació de model
  glm::mat4 transform (1.0f);
  transform = glm::scale(transform, glm::vec3(scale));
  glUniformMatrix4fv(transLoc, 1, GL_FALSE, &transform[0][0]);

}

void MyGLWidget::viewTransform ()
{
	//glm::lookAt(OBS, VRP, UP);
  glm::mat4 View(1.0);
	View = glm::translate(View, glm::vec3(0., 0., -distancia));
	View = glm::rotate(View, girTheta, glm::vec3(1., 0., 0.));
	View = glm::rotate(View, -girPsi, glm::vec3(0., 1., 0.));
	View = glm::translate(View, -centreEscena);
	glUniformMatrix4fv(viewLoc, 1, GL_FALSE, &View[0][0]);

}

void MyGLWidget::projectTransform ()
{

  if(orto){
    glm::mat4 Proj = glm::ortho(-2.5f,2.5f,-2.5f,2.5f,Znear,Zfar);
    glUniformMatrix4fv(projLoc, 1, GL_FALSE, &Proj[0][0]);
  }

  else{
  	glm::mat4 Proj = glm::perspective (FOV, raw, Znear, Zfar);
  	glUniformMatrix4fv(projLoc, 1, GL_FALSE, &Proj[0][0]);
  }
}

void MyGLWidget::resizeGL (int w, int h)
{
  if(orto){

  }
  else{

  	float rav = float(w) / float(h);
  	raw = rav;
  	if (rav < 1.0) FOV = 2.0*atan(tan(FOVini/2.0)/rav);
  	projectTransform ();
  	glViewport(0, 0, w, h);
}
}

void MyGLWidget::keyPressEvent(QKeyEvent* event)
{
  makeCurrent();
  switch (event->key()) {
    case Qt::Key_S: { // escalar a més gran
      scale += 0.05;
      break;
    }
    case Qt::Key_D: { // escalar a més petit
      scale -= 0.05;
      break;
    }
    case Qt::Key_R: { // escalar a més petit
      rot+=float(M_PI/4);
      break;
    }
    case Qt::Key_O: { // escalar a més petit
      orto = !orto;
      break;
    }
    case Qt::Key_Z: {
		FOV -= 0.1;
		projectTransform();
		break;
	}
	case Qt::Key_X: {
		FOV += 0.1;
		projectTransform();
		break;
	}
    default: event->ignore(); break;
  }
  update();
}

void MyGLWidget::mouseMoveEvent(QMouseEvent *e) {
	makeCurrent();

	if(e->x() > x_ant) girPsi -= 0.03;
	else if(e->x() < x_ant) girPsi += 0.03;

	if(e->y() > y_ant) girTheta -= 0.03;
	else if(e->y() < y_ant) girTheta += 0.03;

	x_ant = e->x();
	y_ant = e->y();
	viewTransform();
	update();
}


void MyGLWidget::creaBuffers ()
{
  // Dades de dahoma
  // Dos VBOs, un amb posició i l'altre amb color
  HomerProves.load("Patricio.obj");

  // Creació del Vertex Array Object per pintar
  glGenVertexArrays(1, &VAO_HomerProves);
  glBindVertexArray(VAO_HomerProves);

  GLuint VBO_HomerProvesPos;
  glGenBuffers(1, &VBO_HomerProvesPos);
  glBindBuffer(GL_ARRAY_BUFFER, VBO_HomerProvesPos);
  glBufferData(GL_ARRAY_BUFFER, sizeof(GLfloat)*HomerProves.faces().size()*3*3, HomerProves.VBO_vertices(), GL_STATIC_DRAW);


  // Activem l'atribut vertexLoc
  glVertexAttribPointer(vertexLoc, 3, GL_FLOAT, GL_FALSE, 0, 0);
  glEnableVertexAttribArray(vertexLoc);


  GLuint VBO_HomerProvesCol;
 glGenBuffers(1, &VBO_HomerProvesCol);
 glBindBuffer(GL_ARRAY_BUFFER, VBO_HomerProvesCol);
 glBufferData(GL_ARRAY_BUFFER,sizeof(GLfloat)*HomerProves.faces().size()*3*3, HomerProves.VBO_matdiff(), GL_STATIC_DRAW);

 // Activem l'atribut colorLoc
 glVertexAttribPointer(colorLoc, 3, GL_FLOAT, GL_FALSE, 0, 0);
 glEnableVertexAttribArray(colorLoc);

 //Terra
  glm::vec3 posicio[6] = {
	glm::vec3(2.5, 0.0, 2.5),
	glm::vec3(2.5, 0.0, -2.5),
	glm::vec3(-2.5, 0.0, -2.5),

	glm::vec3(2.5, 0.0, 2.5),
	glm::vec3(-2.5, 0.0, -2.5),
	glm::vec3(-2.5, 0.0, 2.5)
  };

  glm::vec3 color[6] = {
	glm::vec3(1,0,0),
	glm::vec3(0,0,1),
	glm::vec3(0,1,0),

	glm::vec3(1,0,0),
	glm::vec3(0,1,0),
	glm::vec3(0,0,1),
  };

  glGenVertexArrays(1, &VAO_Terra);
  glBindVertexArray(VAO_Terra);

  GLuint VBO_TerraPos;
  glGenBuffers(1, &VBO_TerraPos);
  glBindBuffer(GL_ARRAY_BUFFER, VBO_TerraPos);
  glBufferData(GL_ARRAY_BUFFER, sizeof(posicio), posicio, GL_STATIC_DRAW);

  // Activem l'atribut vertexLoc
  glVertexAttribPointer(vertexLoc, 3, GL_FLOAT, GL_FALSE, 0, 0);
  glEnableVertexAttribArray(vertexLoc);

  GLuint VBO_TerraCol;
  glGenBuffers(1, &VBO_TerraCol);
  glBindBuffer(GL_ARRAY_BUFFER, VBO_TerraCol);
  glBufferData(GL_ARRAY_BUFFER, sizeof(color), color, GL_STATIC_DRAW);

  // Activem l'atribut colorLoc
  glVertexAttribPointer(colorLoc, 3, GL_FLOAT, GL_FALSE, 0, 0);
  glEnableVertexAttribArray(colorLoc);

 glBindVertexArray (0);
}

void MyGLWidget::carregaShaders()
{
  // Creem els shaders per al fragment shader i el vertex shader
  QOpenGLShader fs (QOpenGLShader::Fragment, this);
  QOpenGLShader vs (QOpenGLShader::Vertex, this);
  // Carreguem el codi dels fitxers i els compilem
  fs.compileSourceFile("shaders/basicShader.frag");
  vs.compileSourceFile("shaders/basicShader.vert");
  // Creem el program
  program = new QOpenGLShaderProgram(this);
  // Li afegim els shaders corresponents
  program->addShader(&fs);
  program->addShader(&vs);
  // Linkem el program
  program->link();
  // Indiquem que aquest és el program que volem usar
  program->bind();

  // Obtenim identificador per a l'atribut “vertex” del vertex shader
  vertexLoc = glGetAttribLocation (program->programId(), "vertex");
  // Obtenim identificador per a l'atribut “color” del vertex shader
  colorLoc = glGetAttribLocation (program->programId(), "color");
  // Uniform locations
  transLoc = glGetUniformLocation(program->programId(), "TG");
  projLoc = glGetUniformLocation(program->programId(), "proj");
  viewLoc = glGetUniformLocation(program->programId(), "view");
}

void MyGLWidget::calculaCapsaModel ()
{
  // Càlcul capsa contenidora i valors transformacions inicials
  float minx, miny, minz, maxx, maxy, maxz;
  minx = maxx = HomerProves.vertices()[0];
  miny = maxy = HomerProves.vertices()[1];
  minz = maxz = HomerProves.vertices()[2];
  for (unsigned int i = 3; i < HomerProves.vertices().size(); i+=3)
  {
    if (HomerProves.vertices()[i+0] < minx)
      minx = HomerProves.vertices()[i+0];
    if (HomerProves.vertices()[i+0] > maxx)
      maxx = HomerProves.vertices()[i+0];
    if (HomerProves.vertices()[i+1] < miny)
      miny = HomerProves.vertices()[i+1];
    if (HomerProves.vertices()[i+1] > maxy)
      maxy = HomerProves.vertices()[i+1];
    if (HomerProves.vertices()[i+2] < minz)
      minz = HomerProves.vertices()[i+2];
    if (HomerProves.vertices()[i+2] > maxz)
      maxz = HomerProves.vertices()[i+2];
  }

  scale = 4.0/(maxy-miny);
  centreBasePatricio= glm::vec3((minx+maxx)/2, miny, (minz+maxz)/2);
      escenaMaxima.y = 4.0;
}

void MyGLWidget::calculaCapsaEscena ()
{
  escenaMaxima.x = 2.5;
  escenaMaxima.z = 2.5;

  escenaMinima = glm::vec3(-2.5, 0.0, -2.5);
}

void MyGLWidget::calculRadiEscena ()
{
  radi = distance(escenaMinima, escenaMaxima)/2;

  /*
  std::cout << "Radi: " << radi << std::endl;
  */
}

void MyGLWidget::calculCentreEscena ()
{
  centreEscena = (escenaMaxima + escenaMinima);
  centreEscena = centreEscena / glm::vec3(2.0);
  /*
  std::cout << "Centre Escena: " << centreEscena[0] << ","
								 << centreEscena[1] << ","
								 << centreEscena[2] << ")" << std::endl;
  */
}
