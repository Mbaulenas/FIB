#version 330 core

out vec4 FragColor;
in vec4 fcolor;

void main() {

  FragColor = fcolor;

  if (int(gl_FragCoord.x) % 10 < 5) {
      if(FragColor==vec4(1.0,0.0,0.0,1.0))discard;
  }


}
