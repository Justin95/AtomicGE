#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoord;
layout (location = 2) in vec3 color;

out vec4 inColor;
out vec2 texCoord;

uniform mat4 model;
uniform mat4 view;
uniform mat4 proj;
uniform vec3 lightDirection;

void main(){
	inColor = vec4(color,1.0);
	texCoord = textureCoord;
	gl_Position = model * vec4(position,1.0);
}