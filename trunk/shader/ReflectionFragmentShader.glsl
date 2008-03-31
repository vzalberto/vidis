// uniform qualified variables are changed at most once per primitive
// by the application, and vec3 declares a vector of three
// floating-point numbers


// Temperature contains the now interpolated per-fargment
// value of temperature set by the vertex shader
varying float dist;

void main() {
	float alpha = 1.0/dist;
	gl_FragColor = vec4(gl_FrontMaterial.diffuse.xyz, alpha);
}