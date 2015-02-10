#version 330

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoord;
layout (location = 2) in vec4 color;
layout (location = 3) in float textureID;
layout (location = 4) in vec3 normal;

out vec4 inColor;
out vec2 texCoord;
flat out float tex;
out vec3 normalVector;
out vec3 lightVector;

uniform mat4 model;
uniform mat4 view;
uniform mat4 proj;
uniform vec3 lightDirection;

void main(){
	lightVector  = lightDirection;
	normalVector = normal;
	inColor = color;
	tex = textureID;
	texCoord = textureCoord;
	gl_Position = proj * view * model * vec4(position,1.0);
}