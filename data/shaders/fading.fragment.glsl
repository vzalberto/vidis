uniform vec3 BackgroundColor;
uniform bool mirror;

varying float vis;

void main(){
if (mirror) {
	if (vis < 0) 
		gl_FragColor = gl_Color;
	else
		if (vis>0.75)
			gl_FragColor = vec4(BackgroundColor, 1.0);
	//		gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);
		else
			gl_FragColor = vec4(mix(BackgroundColor, gl_Color.xyz, 1.0-(vis*1.333)), gl_Color.w);
	//		gl_FragColor = vec4(1.0-vis, 1.0-vis, 1.0-vis, 1.0);
}
else 
	gl_FragColor = gl_Color;
}