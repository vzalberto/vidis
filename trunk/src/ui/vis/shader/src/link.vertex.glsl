// uniform qualified variables are changed at most once per primitive

// attribute qualified variables ar typically changed per vertex
attribute vec3 packet1;
attribute vec3 packet2;
attribute vec3 packet3;
attribute vec3 packet4;

// varying qualified variables communicate from the vertex shader to
// the framgment shader

void main() {
	vec3 newVertex = vec3(gl_Vertex);

	if ( length(newVertex - packet1) <= 0.5 ) {
		vec3 add = newVertex - packet1;
		newVertex = newVertex + 0.2 * normalize( add );;
	}
	
	if ( length(newVertex - packet2) <= 0.5 ) {
		vec3 add = newVertex - packet2;
		newVertex = newVertex + 0.2 * normalize( add );;
	}
	if ( length(newVertex - packet3) <= 0.5 ) {
		vec3 add = newVertex - packet3;
		newVertex = newVertex + 0.2 * normalize( add );;
	}
	if ( length(newVertex - packet4) <= 0.5 ) {
		vec3 add = newVertex - packet4;
		newVertex = newVertex + 0.2 * normalize( add );;
	}
	gl_Position = gl_ModelViewProjectionMatrix * vec4(newVertex, gl_Vertex.w);
	gl_FrontColor = gl_Color;
}