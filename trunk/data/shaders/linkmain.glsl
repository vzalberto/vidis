// prototype
void hemisphere_lighting(vec4 curr_Vertex, vec3 curr_Normal);
void link_Vertex();
void link_Normal();

vec4 vert;
vec3 norm;

void main() {
	link_Vertex();
	vec4 curr_Vertex = vert;
	link_Normal();
	vec3 curr_Normal = norm;
	
	hemisphere_lighting(curr_Vertex, curr_Normal);
	gl_Position = gl_ModelViewProjectionMatrix * curr_Vertex;
}