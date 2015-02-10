#version 330

in vec4 inColor;
in vec2 texCoord;

out vec4 outColor;

uniform sampler2D texture1;

void main(){
	outColor = inColor * texture(texture1,texCoord);
}