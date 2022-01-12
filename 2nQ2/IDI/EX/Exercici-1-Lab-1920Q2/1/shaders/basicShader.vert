#version 330 core

in vec3 vertex;
in vec4 color;
out vec4 fcolor;


void main()  {
    gl_Position = vec4 (vertex, 1.0);
    fcolor = color;
}
