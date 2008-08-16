// Link Vertex Shader
// used for the link animation

// uniform qualified variables are changed at most once per primitive

// attribute qualified variables are typically changed per vertex
attribute float delta;
attribute vec3 centerpoint;

//globals (return workaround)
vec4 vert;
vec3 norm;

void link_Vertex() {
	vec3 n = normalize(gl_Vertex.xyz-centerpoint);
	vert = vec4(gl_Vertex.xyz + n * delta, gl_Vertex.w);
}

void link_Normal() {
	norm = gl_Normal;
}
