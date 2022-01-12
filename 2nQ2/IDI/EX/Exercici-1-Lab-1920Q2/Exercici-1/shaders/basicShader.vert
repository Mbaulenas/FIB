#version 330 core

in vec3 vertex;
in vec4 color;
out vec4 fcolor;

uniform mat4 TG;
uniform float val;
uniform float control;

void main()  {
    gl_Position = TG * vec4 (vertex * val, 1.0);

    if(control==0) fcolor = color;

    else fcolor = vec4(0.);
}
