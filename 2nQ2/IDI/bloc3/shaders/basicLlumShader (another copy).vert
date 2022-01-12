#version 330 core

in vec3 vertex;
in vec3 normal;

in vec3 matamb;
in vec3 matdiff;
in vec3 matspec;
in float matshin;

uniform mat4 proj;
uniform mat4 view;
uniform mat4 TG;
uniform vec3 posFocus;


// Valors per als components que necessitem dels focus de llum
//vec3 colFocus = vec3(0.8, 0.8, 0.8);
vec3 llumAmbient = vec3(0.2, 0.2, 0.2);
//vec3 posFocus = vec3(1, 0, 1);  // en SCA

out vec3 fcolor;
out vec3 vertSCO;
out vec3 L;
out vec3 NM;
out vec3 llum;
out vec3 mata;
out vec3 matdif;
out float matshi;
out vec3 matspe;



void main()
{
	// Passar posicio del vertex a SCO
	vertSCO = (view * TG * vec4(vertex, 1.0)).xyz;

	// Pasar posicio del focus de llum en SCO
	vec3 posF = (vec4(posFocus, 1.0)).xyz;

	// Direccio llum
	L = posF - vertSCO;

	// Passar vector normal a SCO
	mat3 NormalMatrix = (inverse(transpose(mat3(view * TG))));
	NM = NormalMatrix * normal;

	// Nomes la Normal i la L son "vectors", els unics que es normalitzen
	L = normalize(L);
	NM = normalize(NM);


  //passar coses necessaries per calcular (vaya marranada jefe jejejeje)
  llum = llumAmbient;
  mata=matamb;
  matdif = matdiff;
  matshi = matshin;
  matspe = matspec;



    gl_Position = proj * view * TG * vec4 (vertex, 1.0);
}
