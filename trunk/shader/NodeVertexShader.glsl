// uniform qualified variables are changed at most once per primitive


// attribute qualified variables ar typically changed per vertex


// varying qualified variables communicate from the vertex shader to
// the framgment shader
varying vec3 normal;
varying vec4 pos;


void main() {
	normal = gl_Normal;
	pos = gl_Vertex;
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}