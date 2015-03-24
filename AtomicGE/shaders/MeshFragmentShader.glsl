#version 330

in vec4 inColor;
in vec2 texCoord;
flat in float tex;
in vec3 normalVector;
in vec3 lightVector;

out vec4 outColor;

uniform sampler2D texture1;
uniform sampler2D texture2;
uniform sampler2D texture3;
uniform sampler2D texture4;

void main(){
	float light = dot(normalVector, lightVector);
	     if(tex == 1){	outColor = inColor * texture(texture1,texCoord) * light;}
	else if(tex == 2){	outColor = inColor * texture(texture2,texCoord) * light;}
	else if(tex == 3){	outColor = inColor * texture(texture3,texCoord) * light;}
	else if(tex == 4){	outColor = inColor * texture(texture4,texCoord) * light;}
	else			 {	outColor = inColor * texture(texture1,texCoord);}
}

