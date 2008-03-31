// uniform qualified variables are changed at most once per primitive

// attribute qualified variables ar typically changed per vertex
attribute float radius;
attribute vec3 centerpoint;

// varying qualified variables communicate from the vertex shader to
// the framgment shader
varying vec3 normal;
varying vec4 pos;


void main() {
	vec4 cp = vec4(centerpoint, 1);
	vec4 dir = cp - gl_Vertex;
	normal = dir.xyz;
	pos =  cp + radius * dir;
	gl_Position = gl_ModelViewProjectionMatrix * pos;
}