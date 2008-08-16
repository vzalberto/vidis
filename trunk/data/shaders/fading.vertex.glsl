uniform vec3 BackgroundColor;

varying float vis;

void fading_color(vec4 curr_Vertex) {
	float offset = -0.6;
	vis = -1;
	if (0 > curr_Vertex.y + offset) {
		vis = abs(curr_Vertex.y + offset);
	}
	
}