uniform float alpha;
uniform vec3 Color;

void main() {
	float x = alpha;
	gl_FrontColor = vec4(Color, x);
	gl_Position = ftransform();
}