// uniform qualified variables are changed at most once per primitive

// attribute qualified variables ar typically changed per vertex

// varying qualified variables communicate from the vertex shader to
// the framgment shader
varying float dist;


void main() {
	vec4 mirrored_Vertex = vec4(gl_Vertex.x, gl_Vertex.y, gl_Vertex.z * (-1.0), gl_Vertex.w);
	dist = abs(gl_Vertex.z);
	gl_Position = gl_ModelViewProjectionMatrix * mirrored_Vertex;
}